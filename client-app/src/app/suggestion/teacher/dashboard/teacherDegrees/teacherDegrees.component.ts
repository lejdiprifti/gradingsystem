import { Component, OnInit } from '@angular/core';
import { TeacherService } from '@ikubinfo/core/services/teacher.service';
import { Degree } from '@ikubinfo/core/models/degree';
import { AuthService } from '@ikubinfo/core/services/auth.service';
import { LoggerService } from '@ikubinfo/core/utilities/logger.service';
import { Router } from '@angular/router';

@Component({
  selector: 'ikubinfo-teacherDegrees',
  templateUrl: './teacherDegrees.component.html',
  styleUrls: ['./teacherDegrees.component.css']
})
export class TeacherDegreesComponent implements OnInit {

  degrees: Array<Degree>;
  constructor(private teacherService: TeacherService,private logger: LoggerService, 
    private authService: AuthService, private router: Router) { }

  ngOnInit() {
    this.getDegrees();
  }

  getDegrees(): void {
    this.teacherService.getDegreesByTeacher(this.authService.user.id).subscribe(res => {
      this.degrees = res;
    }, err=>{
      this.logger.error('Error', 'Something bad happened.');
    })
  }

  openCourses(degreeId: number): void {
    this.router.navigate(['feut/teacher/degrees/'+degreeId+'/courses']);
  }
}
