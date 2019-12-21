import { Component, OnInit } from '@angular/core';
import { Lectures } from '@ikubinfo/core/models/lectures';
import { Course } from '@ikubinfo/core/models/course';
import { Teacher } from '@ikubinfo/core/models/teacher';
import { ActivatedRoute, Router } from '@angular/router';
import { CourseService } from '@ikubinfo/core/services/course.service';
import { TeacherService } from '@ikubinfo/core/services/teacher.service';
import { DegreeService } from '@ikubinfo/core/services/degree.service';
import { LoggerService } from '@ikubinfo/core/utilities/logger.service';
import { DepartmentService } from '@ikubinfo/core/services/department.service';
import { Group } from '@ikubinfo/core/models/group';
import { GroupService } from '@ikubinfo/core/services/group.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { LecturesService } from '@ikubinfo/core/services/lectures.service';
import { ConfirmationService, MenuItem } from 'primeng/primeng';


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
  selectedLecture:  Lectures;
  items: MenuItem[];
  cols: any[];
  constructor(private active: ActivatedRoute,
    private teacherService: TeacherService, private degreeService: DegreeService,
    private logger: LoggerService,private lecturesService: LecturesService,private router: Router,
    private groupService: GroupService,private fb:FormBuilder) { }

  ngOnInit() {
    this.lectures=[];
    this.lecture = {};
    this.loadGroup();
    this.loadCourses();
    this.loadTeacher();
    this.items = [
      { label: 'Edit', icon: 'pi pi-pencil', command: (event) => this.editLecture(this.selectedLecture)}
    ];

    this.cols = [
      { field: 'course', header: 'Course' },
      { field: 'teacher', header: 'Teacher' }
    ];
  }

  loadData(course: Course): void {
      this.lecturesService.getByGroupAndCourse(this.group.id, course.id).subscribe(res => {
        this.lecture = res;
        this.lectures.push(this.lecture);
      }, err=> {
        this.logger.error('Error', 'Something bad happened.');
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


  loadCourses(): void {
    const degreeId = this.active.snapshot.paramMap.get('degreeId');
    this.degreeService.getCoursesByDegree(Number(degreeId)).subscribe(res => {
      this.courses = res;
      this.courses.forEach(element => {
        this.loadData(element);
      })
    }, err => {
      this.logger.error('Error', 'Something bad happened.');
    });
  }

  loadTeacher(): void {
      this.teacherService.getAll().subscribe(res => {
        this.teachers = res;
      }, err=>{
        this.logger.error('Error', 'Something bad happened.');
      })
  }

  editLecture(lecture: Lectures): void {
    this.router.navigate(['feut/degree/'+this.active.snapshot.paramMap.get('degreeId')+'/group/'+this.active.snapshot.paramMap.get('groupId')+'/lecture/'+lecture.id]);
  }
}
