import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { Lectures } from '@ikubinfo/core/models/lectures';
import { LecturesService } from '@ikubinfo/core/services/lectures.service';
import { Router, ActivatedRoute } from '@angular/router';
import { LoggerService } from '@ikubinfo/core/utilities/logger.service';
import { ConfirmationService } from 'primeng/primeng';
import { Teacher } from '@ikubinfo/core/models/teacher';
import { TeacherService } from '@ikubinfo/core/services/teacher.service';

@Component({
  selector: 'ikubinfo-lecture',
  templateUrl: './lecture.component.html',
  styleUrls: ['./lecture.component.css']
})
export class LectureComponent implements OnInit {

  lectureForm: FormGroup;
  lecture: Lectures;
  teachers: Array<Teacher>;
  constructor(private lectureService: LecturesService, 
    private router: Router, private active: ActivatedRoute,
    private fb: FormBuilder, private logger:LoggerService,
    private confirmationService: ConfirmationService, private teacherService: TeacherService) { }

  ngOnInit() {
    this.initializeForm();
    this.loadData();
    this.getTeachers();
  }

  public loadData(): void {
    const lectureId = this.active.snapshot.paramMap.get('lectureId');
    if (lectureId) {
      this.lectureService.getById(Number(lectureId)).subscribe(res => {
        this.lecture = res;
        this.lectureForm.get('teacher').setValue(this.lecture.teacher.id || null);
      }, err=>{
        this.logger.error('Error', 'Lecture could not be found.');
      })
    }
  }

  public reset(): void {
    this.fillForm(this.lecture);
  }

  public initializeForm(): void {
    this.lectureForm = this.fb.group ({
      teacher: ['']
    })
  }

  public fillForm(data: Lectures = {}): void {
    this.lectureForm.get('teacher').setValue(data.teacher.id);
  }

  public getData(): Lectures{
    return {
      id: Number(this.active.snapshot.paramMap.get('lectureId')),
      groupId: this.lecture.group.id,
      courseId: this.lecture.course.id,
      teacherId: this.lectureForm.get('teacher').value
    }
  }

  submit(): void {
    if (this.lecture){
    this.confirmationService.confirm({
      message: 'Are you sure that you want to save the changes?',
      header: 'Save Confirmation',
      icon: 'pi pi-info-circle',
      accept: () => {
        this.lectureService.save(this.getData()).subscribe(res => {
          this.router.navigate(['feut/degree/' + this.active.snapshot.paramMap.get('degreeId') + '/group/'+this.active.snapshot.paramMap.get('groupId')+'/lectures']);
          this.logger.success('Success', 'Data saved succesfully.');
        },err=>{
          this.logger.error('Error', 'Data could not be saved.');
        })
      }
    })
    }
  }

  getTeachers(): void {
    this.teacherService.getAll().subscribe(res => {
      this.teachers = res;
    }, err=>{
      this.logger.error('Error', 'Something bad happened.');
    })
  }
}
