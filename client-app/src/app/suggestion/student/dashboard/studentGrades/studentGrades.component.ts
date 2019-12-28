import { Component, OnInit } from '@angular/core';
import { StudentService } from '@ikubinfo/core/services/student.service';
import { Grade } from '@ikubinfo/core/models/grade';
import { MenuItem } from 'primeng/primeng';
import { AuthService } from '@ikubinfo/core/services/auth.service';
import { LoggerService } from '@ikubinfo/core/utilities/logger.service';

@Component({
  selector: 'ikubinfo-studentGrades',
  templateUrl: './studentGrades.component.html',
  styleUrls: ['./studentGrades.component.css']
})
export class StudentGradesComponent implements OnInit {

  grades: Array<Grade>;
  cols: any;
  items: MenuItem[];
  constructor(private studentService: StudentService, private authService: AuthService,
    private logger: LoggerService) { }

  ngOnInit() {
    this.cols = [
      {field: 'course', header: 'Course'},
      {field: 'comment', header: 'Comment'},
      {field: 'code', header: 'Code'},
      {field: 'grade', header: 'Grade'},
      {field: 'teacher', header: 'Teacher'}
    ];
    this.items =[];
    this.loadGrades();
  }

  loadGrades(): void {
    this.studentService.getGrades(this.authService.user.id).subscribe(res => {
      this.grades= res;
    }, err=>{
      this.logger.error('Error', 'Something bad happened.');
    })
  }

}
