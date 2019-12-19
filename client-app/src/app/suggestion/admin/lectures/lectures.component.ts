import { Component, OnInit } from '@angular/core';
import { Lectures } from '@ikubinfo/core/models/lectures';
import { Course } from '@ikubinfo/core/models/course';
import { Teacher } from '@ikubinfo/core/models/teacher';
import { ActivatedRoute } from '@angular/router';
import { CourseService } from '@ikubinfo/core/services/course.service';
import { TeacherService } from '@ikubinfo/core/services/teacher.service';
import { DegreeService } from '@ikubinfo/core/services/degree.service';
import { LoggerService } from '@ikubinfo/core/utilities/logger.service';
import { DepartmentService } from '@ikubinfo/core/services/department.service';
import { Group } from '@ikubinfo/core/models/group';
import { GroupService } from '@ikubinfo/core/services/group.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { LecturesService } from '@ikubinfo/core/services/lectures.service';
import { ConfirmationService } from 'primeng/primeng';

@Component({
  selector: 'ikubinfo-lectures',
  templateUrl: './lectures.component.html',
  styleUrls: ['./lectures.component.css']
})
export class LecturesComponent implements OnInit {

  lectures: Array<Lectures>;
  courses: Array<Course>;
  teachers: Array<Teacher>;
  lecture: Lectures;
  group: Group;
  lecturesForm: FormGroup;
  constructor(private active: ActivatedRoute, private courseService: CourseService
    ,private departmentService: DepartmentService, private degreeService: DegreeService,
    private logger: LoggerService,private lecturesService: LecturesService,
    private confirmationService: ConfirmationService,
    private groupService: GroupService,private fb:FormBuilder) { }

  ngOnInit() {
    this.initializeForm();
    this.loadGroup();
    this.loadCourses();
  }

  loadData(): void {
    this.courses.forEach(element => {
      this.lecturesService.getByGroupAndCourse(this.group.id, element.id).subscribe(res => {
        this.lecture = res;
        if (this.lecture){
        this.lecturesForm.get('teacher').setValue(this.lecture.teacher.id);
        }
      })
    })
  }

  loadGroup(): void {
    const groupId = this.active.snapshot.paramMap.get('groupId');
    this.groupService.getById(Number(groupId)).subscribe(res => {
      this.group = res;
    }, err => {
      this.logger.error('Error', 'Something bad happened.');
    })
  }

  initializeForm(): void {
      this.lecturesForm = this.fb.group({
        teacher: ['', Validators.required]
      });
  }


  fillForm(data: Lectures = {}): void {
    this.lecturesForm.get('teacher').setValue(data.teacher.id);
  }

  getData(course: Course): Lectures {
    return {
      courseId: course.id,
      groupId: this.group.id,
      teacherId: this.lecturesForm.get('teacher').value
    }
  }

  loadCourses(): void {
    const degreeId = this.active.snapshot.paramMap.get('degreeId');
    this.degreeService.getCoursesByDegree(Number(degreeId)).subscribe(res => {
      this.courses = res;
    }, err => {
      this.logger.error('Error', 'Something bad happened.');
    });
  }

  loadTeachers(departmentId: number): void {
      this.departmentService.getTeachersByDepartment(departmentId).subscribe(res => {
        this.teachers = res;
      }, err=>{
        this.logger.error('Error', 'Something bad happened.');
      })
  }

  submit(course: Course): void{
    this.confirmationService.confirm({
      message: 'Are you sure you want to save this record?',
      header: 'Save Confirmation',
      icon: 'pi pi-info-circle',
      accept: () =>{
        return this.lecturesService.save(this.getData(course)).subscribe(res => {
          this.logger.success('Success', 'Record was saved successfully.');
        }, err =>{
          this.logger.error('Error', 'Something bad happened.');
        })
      }
    })
  }
}
