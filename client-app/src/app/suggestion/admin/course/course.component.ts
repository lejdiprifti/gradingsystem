import { Component, OnInit } from '@angular/core';
import { CourseService } from '@ikubinfo/core/services/course.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { DegreeService } from '@ikubinfo/core/services/degree.service';
import { Course } from '@ikubinfo/core/models/course';
import { LoggerService } from '@ikubinfo/core/utilities/logger.service';
import { Degree } from '@ikubinfo/core/models/degree';
import { DepartmentService } from '@ikubinfo/core/services/department.service';
import { Department } from '@ikubinfo/core/models/department';

@Component({
  selector: 'ikubinfo-course',
  templateUrl: './course.component.html',
  styleUrls: ['./course.component.css']
})
export class CourseComponent implements OnInit {

  courseForm: FormGroup;
  course: Course;
  departments: Array<Department>;
  degree: Degree;
  constructor(private courseService: CourseService, private fb: FormBuilder, private active: ActivatedRoute,
    private degreeService: DegreeService,private departmentService: DepartmentService,
    private logger: LoggerService, private router: Router) { }

  ngOnInit() {
    this.initializeForm();
    this.loadData();
  }

  loadData(): void {
    this.getDepartments();
    const courseId = this.active.snapshot.paramMap.get('courseId');
    const degreeId = this.active.snapshot.paramMap.get('degreeId');
    if (courseId) {
      this.courseService.getById(Number(courseId)).subscribe(data => {
          this.course = data;
          this.courseForm.get('name').setValue(this.course.name);
          this.courseForm.get('syllabus').setValue(this.course.syllabus);
          this.courseForm.get('degree').setValue(this.course.degree.id);
          this.courseForm.get('department').setValue(this.course.department.id);
        },
        err => {
          this.logger.error('Error', 'An error accured');
        });
    } else {
      this.degreeService.getById(Number(degreeId)).subscribe(res => {
        this.degree = res;
      },err=>{
        this.logger.error('Error', 'Something bad happened.');
      })
    }
  }

  reset(): void {
    this.fillForm(this.course);
  }

  initializeForm(): void {
    this.courseForm = this.fb.group({
      name: ['', Validators.required],
      syllabus: ['', Validators.required],
      degree: [''],
      department: ['', Validators.required]
    });
  }

  fillForm(data: Course = {}): void {
    this.courseForm.get('title').setValue(data.name);
    this.courseForm.get('syllabus').setValue(data.syllabus);
    this.courseForm.get('degree').setValue(data.degree.id);
    this.courseForm.get('department').setValue(data.department.id);
  }

  getData(): Course {
    return {
      name: this.courseForm.get('name').value,
      syllabus: this.courseForm.get('syllabus').value,
      degreeId: Number(this.active.snapshot.paramMap.get('degreeId')),
      departmentId: this.courseForm.get('department').value
    }

  }

  submit(): void {
    if (this.course) {
      this.courseService.edit(Number(this.course.id), this.getData()).subscribe(res => {
        this.logger.success('Success', 'Degree was successfully added.');
        this.router.navigate(['feut/degrees']);

      },
      err => {
        this.logger.error('Error', 'Student already exists.');
      });
    }
    else {
      this.courseService.add(this.getData()).subscribe(res => {
        this.logger.info('Success', 'Degree was added successfully.');
        this.router.navigate(['feut/degrees']);
      },
      err => {
        this.logger.error('Error', 'Degree already exists.');
      });

    }
  }

  getDepartments(): void{
    this.departmentService.getAll().subscribe(res => {
      this.departments = res;
    }, 
    err=>{
      this.logger.error('Error', 'Something bad happened.');
    })
  }
}
