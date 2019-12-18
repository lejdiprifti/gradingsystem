import { Component, OnInit } from '@angular/core';
import { DepartmentService } from '@ikubinfo/core/services/department.service';
import { Department } from '@ikubinfo/core/models/department';
import { LoggerService } from '@ikubinfo/core/utilities/logger.service';
import { Router } from '@angular/router';

@Component({
  selector: 'ikubinfo-departments',
  templateUrl: './departments.component.html',
  styleUrls: ['./departments.component.css']
})
export class DepartmentsComponent implements OnInit {

  departments: Array<Department>;
  constructor(private departmentService: DepartmentService,
    private logger: LoggerService, private router: Router) { }

  ngOnInit() {
    this.loadDepartments();
  }
  
  loadDepartments(): void {
    this.departmentService.getAll().subscribe(res => {
      this.departments = res;
    }, err=> {
      this.logger.error('Error', 'Something bad happened.');
    })
  }

  addDepartment(): void{
    this.router.navigate(['feut/department']);
  }

  openTeachers(id: number): void {
    this.router.navigate(['feut/departments/'+id+'/teachers']);
  }

  openCourses(id: number): void {
    this.router.navigate(['feut/departments/' +id+'/courses']);
  }
}
