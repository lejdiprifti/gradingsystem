import { Component, OnInit } from '@angular/core';
import { TeacherService } from '@ikubinfo/core/services/teacher.service';
import { Course } from '@ikubinfo/core/models/course';
import { AuthService } from '@ikubinfo/core/services/auth.service';
import { ActivatedRoute, Router } from '@angular/router';
import { LoggerService } from '@ikubinfo/core/utilities/logger.service';

@Component({
  selector: 'ikubinfo-teacherCourses',
  templateUrl: './teacherCourses.component.html',
  styleUrls: ['./teacherCourses.component.css']
})
export class TeacherCoursesComponent implements OnInit {
  courses: Array<Course>;
  constructor(private teacherService: TeacherService,private active: ActivatedRoute,
     private authService: AuthService, private logger: LoggerService, 
     private router: Router) { }

  ngOnInit() {
    this.getCourses();
  }

  getCourses(): void {
    const degreeId = this.active.snapshot.paramMap.get('degreeId');
    this.teacherService.getCoursesByTeacherAndDegree(this.authService.user.id, Number(degreeId)).subscribe(res => {
      this.courses = res;
    }, err=> {
      this.logger.error('Error', 'Something bad happened.');
    })
  }

  openGroups(id: number){
    const degreeId = this.active.snapshot.paramMap.get('degreeId');
    this.router.navigate(['feut/teacher/degrees/'+degreeId+'/courses/'+id+'/groups']);
  }
}
