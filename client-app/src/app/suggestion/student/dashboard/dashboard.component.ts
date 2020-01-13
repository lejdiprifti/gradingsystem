import { Component, OnInit } from '@angular/core';
import { StudentService } from '@ikubinfo/core/services/student.service';
import { Student } from '@ikubinfo/core/models/student';
import { AuthService } from '@ikubinfo/core/services/auth.service';
import { LoggerService } from '@ikubinfo/core/utilities/logger.service';
import { Grade } from '@ikubinfo/core/models/grade';

@Component({
  selector: 'ikubinfo-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class StudentDashboardComponent implements OnInit {

  student: Student;
  grades: Array<Grade>;
  data: any;
  labels: String[];
  finals: number[];
  constructor(private authService: AuthService,
    private studentService: StudentService,
    private logger: LoggerService) { }

  ngOnInit() {
    this.labels = [];
    this.finals = [];
    this.loadStudent();
    this.loadGrades();
    this.data = {
      labels: this.labels,
      datasets: [
        {
            label: 'Points',
            backgroundColor: 'rgba(255,99,132,0.2)',
            borderColor: 'rgba(255,99,132,1)',
            pointBackgroundColor: 'rgba(255,99,132,1)',
            pointBorderColor: '#fff',
            pointHoverBackgroundColor: '#fff',
            pointHoverBorderColor: 'rgba(255,99,132,1)',
            data: this.finals
        }
      ]
    }
  }

  loadGrades(){
    this.studentService.getGrades(this.authService.user.id).subscribe(res =>{
      this.grades = res;
      this.grades.forEach(el => {
        this.labels.push(el.course.name);
        this.finals.push(el.grade);
      })
    }, err=>{
      this.logger.error('Error','Something bad happened.');
    })
  }
  loadStudent(){
    this.studentService.getById(this.authService.user.id).subscribe(res=>{
      this.student = res;
    }, err=>{
      this.logger.error('Error','Something bad happened.');
    })
  }
}
