import { Component, OnInit } from '@angular/core';
import { DegreeService } from '@ikubinfo/core/services/degree.service';
import { LoggerService } from '@ikubinfo/core/utilities/logger.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Group } from '@ikubinfo/core/models/group';
import { GroupService } from '@ikubinfo/core/services/group.service';
import { ConfirmationService } from 'primeng/primeng';

@Component({
  selector: 'ikubinfo-group',
  templateUrl: './group.component.html',
  styleUrls: ['./group.component.css']
})
export class GroupComponent implements OnInit {

  groupForm: FormGroup;
  group: Group;

  constructor(private degreeService: DegreeService, private active: ActivatedRoute,
    private router: Router,private groupService: GroupService,
    private logger: LoggerService, private fb: FormBuilder,
    private confirmationService: ConfirmationService) { }

  ngOnInit() {
    this.initializeForm();
    this.loadData();
  }

  public loadData(): void {
    const id = this.active.snapshot.paramMap.get('groupId');
    if (id){ 
      this.groupService.getById(Number(id)).subscribe(res=>{
        this.group = res;
        this.groupForm.get('number').setValue(this.group.number);
        this.groupForm.get('email').setValue(this.group.email);
      },
      err => {
        this.logger.error('Error', 'Group not found.');
      })
    }
  }

  public reset(): void {
    this.fillForm(this.group);
  }

  public initializeForm(): void{
    this.groupForm = this.fb.group({
      number: ['', Validators.required],
      email: ['', Validators.required]
    });
  }

  public fillForm(data: Group = {}): void {
    this.groupForm.get('number').setValue(data.number);
    this.groupForm.get('email').setValue(data.email);
  }

  public getData(): Group{
    return {
      number: this.groupForm.get('number').value,
      email: this.groupForm.get('email').value
    }
  }

  submit(): void {
    if (!this.group) {
      this.confirmationService.confirm({
        message: 'Are you sure that you want to add this group?',
        header: 'Adding Confirmation',
        icon: 'pi pi-info-circle',
        accept: () => {
          const degreeId = this.active.snapshot.paramMap.get('id');
          this.degreeService.addGroup(this.getData(), Number(degreeId)).subscribe(res => {
            this.logger.success('Success', 'Group was added successfully!');
            this.router.navigate(['feut/degree/'+ degreeId +'/groups']);
          }, err => {
            this.logger.error('Error', 'Something bad happened.');
          })
        }
        });
    } else {
      this.confirmationService.confirm({
        message: 'Are you sure that you want to add this group?',
        header: 'Adding Confirmation',
        icon: 'pi pi-info-circle',
        accept: () => {
          this.groupService.edit(Number(this.group.id), this.getData()).subscribe(res => {
              this.logger.success('Success', 'Group was updated.');
            this.router.navigate(['feut/degree/'+ this.group.degree.id +'/groups']);
          }, err=>{
            this.logger.error('Error', 'Something bad happened.');
          })
    }
  });
}
  }
}
