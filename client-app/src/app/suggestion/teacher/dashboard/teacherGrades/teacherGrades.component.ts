import { Component, OnInit } from '@angular/core';
import { GradeService } from '@ikubinfo/core/services/grade.service';
import { Grade } from '@ikubinfo/core/models/grade';
import { LoggerService } from '@ikubinfo/core/utilities/logger.service';
import { ActivatedRoute, Router } from '@angular/router';
import { MenuItem } from 'primeng/primeng';

@Component({
  selector: 'ikubinfo-teacherGrades',
  templateUrl: './teacherGrades.component.html',
  styleUrls: ['./teacherGrades.component.css']
})
export class TeacherGradesComponent implements OnInit {

  grades: Array<Grade>;
  items: MenuItem[];
  cols: any;
  selectedGrade: Grade;
  constructor(private gradeService: GradeService, private logger: LoggerService,
    private active: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    this.getGrades();
    this.items = [
      { label: 'Edit', icon: 'pi pi-pencil', command: (event) => this.editGrade(this.selectedGrade)}
    ];

    this.cols = [
      {field: 'student', header: 'Student'},
      {field: 'code', header: 'Exam Code'},
      {field: 'comment', header: 'Comment'},
      {field: 'grade', header: 'Grade' }
    ];
  }

  getGrades(): void {
    const groupId = this.active.snapshot.paramMap.get('groupId');
    const courseId = this.active.snapshot.paramMap.get('courseId');
    this.gradeService.getGradesByGroupAndCourse(Number(groupId), Number(courseId)).subscribe(res => {
      this.grades = res;
    }, err=>{
      this.logger.error('Error', 'Something bad happened.');
    })
  }

  editGrade(grade: Grade): void {
    const groupId = this.active.snapshot.paramMap.get('groupId');
    const courseId = this.active.snapshot.paramMap.get('courseId');
    this.router.navigate(['feut/teacher/groups/'+groupId+'/courses/'+courseId+'/grades/'+grade.id]);
  }
}
