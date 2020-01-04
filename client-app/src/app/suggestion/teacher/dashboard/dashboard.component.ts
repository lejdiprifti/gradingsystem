import { Component, OnInit } from '@angular/core';
import { TeacherService } from '@ikubinfo/core/services/teacher.service';
import { Lectures } from '@ikubinfo/core/models/lectures';
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
        this.loadAverage(el.course.id, el.group.id, el);
      })
    }, err=> {
      this.logger.error('Error', 'Lectures could not be found.');
    })
  }

  loadAverage(courseId: number, groupId: number, lecture: Lectures): void {
    this.teacherService.getAverageByCourseGroupAndTeacher(courseId, groupId, this.authService.user.id).subscribe(res =>{
      this.averages.push(res[0] || 0);
      let label = lecture.group.degree.title + ' ' + lecture.group.number + '\n' + lecture.course.name;
      this.labels.push(label);
    }, err=>{
      this.logger.error('Error', 'Average could not be calculated.');
    })
  }
  

}
