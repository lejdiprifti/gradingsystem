import { CommonsModule } from '@ikubinfo/commons/commons.module';

import { NgModule } from '@angular/core';

import { SuggestionRoutingModule } from '@ikubinfo/suggestion/suggestion-routing.module';
import { LayoutModule } from '@ikubinfo/layout/layout.module';
import { FormsModule } from '@angular/forms';
import { StudentDashboardComponent } from './student/dashboard/dashboard.component';
import { AdminStudentsComponent } from './admin/AdminStudents/AdminStudents.component';
import { StudentComponent } from './admin/student/student.component';
import { DegreesComponent } from './admin/degrees/degrees.component';
import { GroupsComponent } from './admin/groups/groups.component';
import { DegreeComponent } from './admin/degree/degree.component';
import { GroupComponent } from './admin/group/group.component';
import { TeachersComponent } from './admin/teachers/teachers.component';
import { DepartmentsComponent } from './admin/departments/departments.component';
import { DepartmentComponent } from './admin/department/department.component';
import { TeacherComponent } from './admin/teacher/teacher.component';
import { CoursesComponent } from './admin/courses/courses.component';
import { CourseComponent } from './admin/course/course.component';
import { LecturesComponent } from './admin/lectures/lectures.component';
import { LectureComponent } from './admin/lecture/lecture.component';
import { ChartModule } from 'primeng/components/chart/chart';
import { AdminDashboardComponent } from './admin/adminDashboard/adminDashboard.component';

@NgModule({
    imports: [CommonsModule, SuggestionRoutingModule, LayoutModule, FormsModule, ChartModule],
    exports: [],
    declarations: [AdminDashboardComponent,
        StudentDashboardComponent, AdminStudentsComponent,StudentComponent,LecturesComponent, 
        DegreesComponent,DepartmentComponent,LectureComponent,TeacherComponent,CoursesComponent, CourseComponent,
         GroupsComponent,DegreeComponent, GroupComponent, TeachersComponent,DepartmentsComponent],
    providers: []
})
export class SuggestionModule { }
