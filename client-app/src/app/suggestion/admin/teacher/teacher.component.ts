import { Component, OnInit } from '@angular/core';
import { TeacherService } from '@ikubinfo/core/services/teacher.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Teacher } from '@ikubinfo/core/models/teacher';
import { Department } from '@ikubinfo/core/models/department';
import { ActivatedRoute, Router } from '@angular/router';
import { LoggerService } from '@ikubinfo/core/utilities/logger.service';
import { ConfirmationService } from 'primeng/primeng';
import { DepartmentService } from '@ikubinfo/core/services/department.service';

@Component({
  selector: 'ikubinfo-teacher',
  templateUrl: './teacher.component.html',
  styleUrls: ['./teacher.component.css']
})
export class TeacherComponent implements OnInit {

  departments: Array<Department>;
  teacherForm: FormGroup;
  teacher: Teacher;
  constructor(private teacherService: TeacherService, private fb: FormBuilder,
    private active: ActivatedRoute, private logger: LoggerService, 
    private confirmationService: ConfirmationService,
    private router: Router, private departmentService: DepartmentService) { }

  ngOnInit() {
    this.loadData();
    this.initializeForm();
  }

  loadData(): void {
    this.getDepartments();
    const id = this.active.snapshot.paramMap.get('id');
    if (id) {
      this.teacherService.getById(Number(id)).subscribe(res => {
        this.teacher = res;
        this.teacherForm.get('firstName').setValue(this.teacher.firstName);
        this.teacherForm.get('fatherName').setValue(this.teacher.fatherName);
        this.teacherForm.get('lastName').setValue(this.teacher.lastName);
        this.teacherForm.get('personalNumber').setValue(this.teacher.personalNumber);
        this.teacherForm.get('department').setValue(this.teacher.department.name);
        this.teacherForm.get('username').setValue(this.teacher.username);
        this.teacherForm.get('password').setValue(this.teacher.password);
        this.teacherForm.get('email').setValue(this.teacher.email);
      },
      err=>{
        this.logger.error('Error', 'Something bad happened.');
      })
    }
  }
  reset(): void{
    this.fillForm(this.teacher);
  }

  initializeForm(): void{
    this.teacherForm = this.fb.group({
      firstName: ['', Validators.required],
      fatherName: ['', Validators.required],
      lastName: ['', Validators.required],
      personalNumber: ['' , Validators.required],
      department: ['', Validators.required],
      email: ['', Validators.required],
      username: ['', Validators.required],
      password: ['', Validators.required],
      birthdate: ['', Validators.required]
    });
  }

  fillForm(data: Teacher = {}): void {
    this.teacherForm.get('firstName').setValue(data.firstName);
    this.teacherForm.get('lastName').setValue(data.lastName);
    this.teacherForm.get('fatherName').setValue(data.fatherName);
    this.teacherForm.get('email').setValue(data.email);
    this.teacherForm.get('personalNumber').setValue(data.personalNumber);
    this.teacherForm.get('username').setValue(data.username);
    this.teacherForm.get('birthdate').setValue(data.birthdate);
    this.teacherForm.get('password').setValue(data.password);
    this.teacherForm.get('department').setValue(data.department.id);
  }

  getData(): Teacher {
    return {
      firstName: this.teacherForm.get('firstName').value,
      fatherName: this.teacherForm.get('fatherName').value,
      lastName: this.teacherForm.get('lastName').value,
      email: this.teacherForm.get('email').value,
      personalNumber: this.teacherForm.get('personalNumber').value,
      departmentId: this.teacherForm.get('department').value,
      username: this.teacherForm.get('username').value,
      password: this.teacherForm.get('password').value,
      birthdate: this.teacherForm.get('birthdate').value
    }
  }

  submit(): void {
    if (this.teacher){
      this.confirmationService.confirm({
        message: 'Are you sure you want to save the changes?',
        header: 'Save Confirmation',
        icon: 'pi pi-info-circle',
        accept: () => {
          return this.teacherService.edit(Number(this.teacher.id), this.getData()).subscribe(res => {
            this.logger.success('Success', 'Data successfully saved!');
          this.router.navigate(['feut/teachers']);
          }, err=>{             
            this.logger.error('Error', 'Something bad happened.');
          })
        }
      })
    } else {
      this.confirmationService.confirm({
        message: 'Are you sure you want to save the changes?',
        header: 'Save Confirmation',
        icon: 'pi pi-info-circle',
        accept: () => {
          return this.teacherService.add(this.getData()).subscribe(res=>{
            this.logger.success('Success', 'Data successfully saved.');
            this.router.navigate(['feut/teachers']);
          }, err=> {
            this.logger.error('Error', 'Something bad happened.');
          })
      }
    });
  }
}

getDepartments(): void {
  this.departmentService.getAll().subscribe(res => {
    this.departments = res;
  }, err=>{
    this.logger.error('Error', 'Something bad happened.');
  })
}
}
