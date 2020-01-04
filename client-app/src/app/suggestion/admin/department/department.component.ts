import { Component, OnInit } from '@angular/core';
import { DepartmentService } from '@ikubinfo/core/services/department.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Department } from '@ikubinfo/core/models/department';
import { LoggerService } from '@ikubinfo/core/utilities/logger.service';

@Component({
  selector: 'ikubinfo-department',
  templateUrl: './department.component.html',
  styleUrls: ['./department.component.css']
})
export class DepartmentComponent implements OnInit {

  department: Department;
  departmentForm: FormGroup;
  constructor(private departmentService: DepartmentService, private active: ActivatedRoute,
    private router: Router, private logger: LoggerService,
    private fb: FormBuilder) { }

  ngOnInit() {
    this.loadData();
    this.initializeForm();
  }

  loadData(): void {
    const id = this.active.snapshot.paramMap.get('id');
    if (id) {
      this.departmentService.getById(Number(id)).subscribe(res => {
        this.department = res;
        this.departmentForm.get('name').setValue(this.department.name);
        this.departmentForm.get('description').setValue(this.department.description);
      }, err=>{
        this.logger.error('Error', 'Something bad happened.');
      })
    }
  }

  reset(): void {
    this.fillForm(this.department);
  }

  initializeForm(): void {
    this.departmentForm = this.fb.group({
      name: ['', Validators.required],
      description: ['', Validators.required]
    });
  }

  fillForm(data: Department = {}): void {
    this.departmentForm.get('name').setValue(data.name);
    this.departmentForm.get('description').setValue(data.description);
  }

  getData(): Department {
    return {
      name: this.departmentForm.get('name').value,
      description: this.departmentForm.get('description').value
    }
  }

  submit(): void {
    if (this.department){
      this.departmentService.edit(Number(this.department.id), this.getData()).subscribe(res => {
        this.logger.success('Success', 'Department was successfully updated');
        this.router.navigate(['feut/departments']);
      }, 
      err => {
        this.logger.error('Error', 'Department already exists.');
      });
    } else {
      this.departmentService.add(this.getData()).subscribe(res => {
        this.logger.success('Success', 'Department was successfully added.');
        this.router.navigate(['feut/departments']);
      }, err =>{
        this.logger.error('Error', 'Department already exists.');
      });
    }
  }

}

