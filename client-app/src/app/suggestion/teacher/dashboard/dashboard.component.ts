import { Component, OnInit } from '@angular/core';
import { TeacherService } from '@ikubinfo/core/services/teacher.service';
import { Lectures } from '@ikubinfo/core/models/lectures';
import { Group } from '@ikubinfo/core/models/group';
import { Course } from '@ikubinfo/core/models/course';
import { AuthService } from '@ikubinfo/core/services/auth.service';
import { LoggerService } from '@ikubinfo/core/utilities/logger.service';

@Component({
  selector: 'ikubinfo-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  lectures: Array<Lectures>;
  averages: Array<number>;
  labels: Array<string>;
  data:any;
  constructor(private teacherService: TeacherService,
    private authService: AuthService,
    private logger: LoggerService) { }

  ngOnInit() {
    this.lectures = [];
    this.labels = [];
    this.averages = [];
    this.loadLectures();
    this.data = {
      labels: this.labels,
      datasets: [
          {
              label: 'Average of students',
              backgroundColor: '#42A5F5',
              borderColor: '#1E88E5',
              data: this.averages
          }
      ]
  }
  }

  loadLectures(): void {
    this.teacherService.getLectures(this.authService.user.id).subscribe(res =>{
      this.lectures = res;
      this.lectures.forEach(el => {
        let label = el.group.degree.title + ' ' + el.group.number + '\n' + el.course.name;
        this.labels.push(label);
        console.log(label);
        this.loadAverage(el.course.id, el.group.id);
      })
    }, err=> {
      this.logger.error('Error', 'Lectures could not be found.');
    })
  }

  loadAverage(courseId: number, groupId: number): void {
    this.teacherService.getAverageByCourseGroupAndTeacher(courseId, groupId, this.authService.user.id).subscribe(res =>{
      this.averages.push(res);
    }, err=>{
      this.logger.error('Error', 'Average could not be calculated.');
    })
  }
  

}
