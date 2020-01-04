import { Component, OnInit } from '@angular/core';
import { Student } from '@ikubinfo/core/models/student';
import { MenuItem, ConfirmationService } from 'primeng/primeng';
import { StudentService } from '@ikubinfo/core/services/student.service';
import { LoggerService } from '@ikubinfo/core/utilities/logger.service';
import { Router } from '@angular/router';

@Component({
  selector: 'ikubinfo-AdminStudents',
  templateUrl: './AdminStudents.component.html',
  styleUrls: ['./AdminStudents.component.css']
})
export class AdminStudentsComponent implements OnInit {

  students: Array<Student>;
  items: MenuItem[];
  cols: any[];
  selectedStudent: Student;
  constructor(private logger: LoggerService,private router: Router,
    private studentService: StudentService, private confirmationService: ConfirmationService) { }

  ngOnInit() {
    this.loadStudents();
    this.items = [
      { label: 'Edit', icon: 'pi pi-pencil', command: (event) =>  this.editStudent(this.selectedStudent)},
      { label: 'Delete', icon: 'pi pi-times', command: (event) =>  this.deleteStudent(this.selectedStudent)}
    ];

    this.cols = [
      { field: 'id', header: 'ID' },
      { field: 'firstName', header: 'First name' },
      {field: 'fatherName', header: 'Father name'},
      {field: 'lastName', header: 'Last name'},
      {field: 'personalNumber', header: 'Personal No.'},
      {field: 'username', header: 'Username'},
      {field: 'password', header: 'Password'},
      {field: 'group', header: 'Group' }
    ];
  }

  loadStudents() {
    this.studentService.getAll().subscribe(res => {
      this.students = res;
    }, 
    err => {
      this.logger.error('Error','Something bad happened!');
    })
  }

  deleteStudent(student: Student): void{
    this.confirmationService.confirm({
      message: 'Are you sure that you want to delete this student?',
      header: 'Delete Confirmation',
      icon: 'pi pi-info-circle',
      accept: () => {
        return this.studentService.delete(student.id).subscribe(res => {
          this.logger.info('Confirmed', 'Record deleted');
          this.loadStudents();
        },
        err => {
          this.logger.error('Error', 'An error accured');
        });
      }
    });
  }

  editStudent(student: Student): void{
    this.router.navigate(['feut/student/' + student.id]);
  }

  addStudent(): void{
    this.router.navigate(['feut/student']);
  }

}
