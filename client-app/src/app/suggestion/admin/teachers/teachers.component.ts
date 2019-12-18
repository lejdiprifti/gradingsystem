import { Component, OnInit } from '@angular/core';
import { TeacherService } from '@ikubinfo/core/services/teacher.service';
import { LoggerService } from '@ikubinfo/core/utilities/logger.service';
import { ConfirmationService, MenuItem } from 'primeng/primeng';
import { Teacher } from '@ikubinfo/core/models/teacher';
import { Router, ActivatedRoute } from '@angular/router';
import { DepartmentService } from '@ikubinfo/core/services/department.service';

@Component({
  selector: 'ikubinfo-teachers',
  templateUrl: './teachers.component.html',
  styleUrls: ['./teachers.component.css']
})
export class TeachersComponent implements OnInit {
  teachers: Array<Teacher>;
  selectedTeacher: Teacher;
  items: MenuItem[];
  cols: any[];
  constructor(private teacherService: TeacherService, private logger: LoggerService,
    private confirmationService: ConfirmationService,private active: ActivatedRoute,
    private router: Router, private departmentService: DepartmentService) { }

  ngOnInit() {
    this.loadData();
    this.items = [
      { label: 'Edit', icon: 'pi pi-pencil', command: (event) =>  this.editTeacher(this.selectedTeacher)},
      { label: 'Delete', icon: 'pi pi-times', command: (event) =>  this.deleteTeacher(this.selectedTeacher)}
    ];

    this.cols = [
      { field: 'id', header: 'ID' },
      { field: 'firstName', header: 'First name' },
      {field: 'fatherName', header: 'Father name'},
      {field: 'lastName', header: 'Last name'},
      {field: 'personalNumber', header: 'Personal No.'},
      {field: 'username', header: 'Username'},
      {field: 'password', header: 'Password'},
      {field: 'department', header: 'Department' }
    ];
  }

  loadData(): void{
    const id = this.active.snapshot.paramMap.get('id');
    if (id){
      this.departmentService.getTeachersByDepartment(Number(id)).subscribe(res => {
        this.teachers = res;
      }, err=>{
        this.logger.error('Error', 'Something bad happened.');
      })
    } else {
    this.teacherService.getAll().subscribe(res=>{
      this.teachers = res;
    },
    err=>{
      this.logger.error('Error', 'Something bad happened.');
    })
  }
  }

  deleteTeacher(teacher: Teacher): void{
    this.confirmationService.confirm({
      message: 'Are you sure that you want to delete this teacher?',
      header: 'Delete Confirmation',
      icon: 'pi pi-info-circle',
      accept: () => {
        return this.teacherService.delete(teacher.id).subscribe(res=>{
          this.logger.info('Info', 'Teacher was deleted successfully!');
          this.loadData();
        },err=>{
          this.logger.error('Error', 'Something bad happened.');
        })
      }
    });
  }

  editTeacher(teacher: Teacher): void {
    this.router.navigate(['feut/teacher/'+ teacher.id]);
  }

  addTeacher(): void {
    this.router.navigate(['feut/teacher']);
  }
}
