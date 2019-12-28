import { Component, OnInit } from '@angular/core';
import { CourseService } from '@ikubinfo/core/services/course.service';
import { LoggerService } from '@ikubinfo/core/utilities/logger.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Course } from '@ikubinfo/core/models/course';
import { DepartmentService } from '@ikubinfo/core/services/department.service';
import { DegreeService } from '@ikubinfo/core/services/degree.service';
import { ConfirmationService } from 'primeng/components/common/confirmationservice';
import { AuthService } from '@ikubinfo/core/services/auth.service';
import { RoleEnum } from '@ikubinfo/core/models/role.enum';
import { StudentService } from '@ikubinfo/core/services/student.service';

@Component({
  selector: 'ikubinfo-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.css']
})
export class CoursesComponent implements OnInit {

  courses: Array<Course>;
  check: boolean;
  role: number;
  constructor(private courseService: CourseService, private logger: LoggerService,
    private active: ActivatedRoute, private departmentService: DepartmentService,
    private degreeService: DegreeService, private router: Router, 
    private confirmationService: ConfirmationService,
    private authService: AuthService, private studentService: StudentService) { }

  ngOnInit() {
    this.loadCourses();
  }

  loadCourses(): void {
    this.role = this.authService.user.role.id
    if (this.authService.user.role == RoleEnum.ADMIN){
    const degreeId = this.active.snapshot.paramMap.get('degreeId');
    const departmentId = this.active.snapshot.paramMap.get('departmentId');
    this.check = (departmentId==null);
    if (departmentId) {
      this.departmentService.getCoursesByDepartment(Number(departmentId)).subscribe(res => {
        this.courses = res;
      }, err => {
        this.logger.error('Error', 'Something bad happened.');
      });
    } else if (degreeId){
      this.degreeService.getCoursesByDegree(Number(degreeId)).subscribe(res => {
        this.courses = res;
      }, err=>{
        this.logger.error('Error', 'Something bad happened.');    
      });
    } else {
      this.courseService.getAll().subscribe(res => {
        this.courses = res;
      }, err => {
        this.logger.error('Error', 'Something bad happened.');
      })
    } }
    else {
      this.studentService.getCourses(this.authService.user.id).subscribe(res =>{
        this.courses = res;
      }, err=>{
        this.logger.error('Error', 'Something bad happened.');
      })
    }
  }

  editCourse(id: number): void {
    this.router.navigate(['feut/courses/'+ id])
  }

  deleteCourse(id: number): void {
    this.confirmationService.confirm({
      message: 'Are you sure you want to delete this course?',
      header: 'Delete Confirmation',
      icon: 'pi pi-info-circle',
      accept: () => {
        this.courseService.delete(id).subscribe(res => {
          this.loadCourses();
          this.logger.info('Info', 'Course was deleted successfully!');
        }, err=>{
          this.logger.error('Error', 'Something bad happened.');
        })
      }
    })
  }
  addCourse(): void {
    const id = this.active.snapshot.paramMap.get('degreeId');
    this.router.navigate(['feut/degree/'+id+'/course']);
  }

}
