import { Component, OnInit } from '@angular/core';
import { GradeService } from '@ikubinfo/core/services/grade.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { LoggerService } from '@ikubinfo/core/utilities/logger.service';
import { Grade } from '@ikubinfo/core/models/grade';
import { AuthService } from '@ikubinfo/core/services/auth.service';
import { ConfirmationService } from 'primeng/primeng';

@Component({
  selector: 'ikubinfo-teacherGrade',
  templateUrl: './teacherGrade.component.html',
  styleUrls: ['./teacherGrade.component.css']
})
export class TeacherGradeComponent implements OnInit {

  grade: Grade;
  gradeForm: FormGroup;
  grades: Array<number>;
  constructor(private gradeService: GradeService, private authService: AuthService,
    private fb: FormBuilder, private router: Router,private confirmationService: ConfirmationService,
    private logger: LoggerService, private active: ActivatedRoute) { }

  ngOnInit() {
    this.initializeForm();
    this.loadData();
    this.grades = [
      4,
      5,
      6,
      7,
      8,
      9,
      10
    ];
  }

  public loadData(): void {
    const gradeId = this.active.snapshot.paramMap.get('gradeId');
    this.gradeService.getById(Number(gradeId)).subscribe(res =>{
      this.grade = res;
      this.gradeForm.get('code').setValue(this.grade.code);
      this.gradeForm.get('comment').setValue(this.grade.comment);
      this.gradeForm.get('student').setValue(this.grade.student.firstName,this.grade.student.lastName);
      this.gradeForm.get('grade').setValue(this.grade.grade);
    }, err=>{
      this.logger.error('Error', 'Something bad happened.');
    })
  }

  public reset(): void {
    this.fillForm(this.grade);
  }

  public initializeForm(): void {
    this.gradeForm = this.fb.group({
      student: [''],
      comment: ['', Validators.required],
      grade: ['', Validators.required],
      code: ['', Validators.required]
    })
  }

  public fillForm(data: Grade = {}): void {
    this.gradeForm.get('student').setValue(data.student.firstName,data.student.lastName);
    this.gradeForm.get('comment').setValue(data.comment);
    this.gradeForm.get('code').setValue(data.code);
    this.gradeForm.get('grade').setValue(data.grade);
  }

  public getData(): Grade{
    return {
      comment: this.gradeForm.get('comment').value,
      code: this.gradeForm.get('code').value,
      grade: this.gradeForm.get('grade').value,
      courseId: Number(this.active.snapshot.paramMap.get('courseId')),
      teacherId: this.authService.user.id
    }
  }

  submit(): void {
    this.confirmationService.confirm({
      message: 'Are you sure you want to save this record?',
      icon: 'pi pi-info-circle',
      header: 'Save Confirmation',
      accept: () => {
        this.gradeService.edit(this.getData(), this.grade.id).subscribe(res => {
          this.router.navigate(['feut/teacher/groups/'+this.grade.student.groupId+'/courses/'+this.grade.courseId+'/grades']);
          this.logger.success('Success', 'Grade was saved successfully!');
        }, err=>{
          this.logger.error('Error', 'Something bad happened.');
        })
      }
    })
  }
}

