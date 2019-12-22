import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AdminGuard } from '@ikubinfo/core/guards/admin-guard';
import { FullComponent } from '@ikubinfo/layout/full/full.component';
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
import { AdminDashboardComponent } from './admin/adminDashboard/adminDashboard.component';
import { DashboardComponent } from './teacher/dashboard/dashboard.component';
import { TeacherGuard } from '@ikubinfo/core/guards/teacher-guard';
import { TeacherDegreesComponent } from './teacher/dashboard/teacherDegrees/teacherDegrees.component';
import { TeacherCoursesComponent } from './teacher/dashboard/teacherCourses/teacherCourses.component';
import { TeacherGroupsComponent } from './teacher/dashboard/teacherGroups/teacherGroups.component';
import { TeacherGradesComponent } from './teacher/dashboard/teacherGrades/teacherGrades.component';

const suggestionRoutes: Routes = [
    {
        path: '',
        component: FullComponent,
        children: [
            { path: 'dashboard', component: AdminDashboardComponent, canActivate: [AdminGuard] },
            {path: 'teacher/dashboard', component: DashboardComponent, canActivate: [TeacherGuard]},
            {path: 'teacher/degrees', component: TeacherDegreesComponent, canActivate: [TeacherGuard]},
            {path: 'teacher/degrees/:degreeId/courses', component: TeacherCoursesComponent, canActivate: [TeacherGuard]},
            {path: 'teacher/degrees/:degreeId/courses/:courseId/groups', component: TeacherGroupsComponent, canActivate: [TeacherGuard]},
            {path: 'teacher/groups/:groupId/courses/:courseId/grades', component: TeacherGradesComponent, canActivate: [TeacherGuard]},
            {path: 'departments', component: DepartmentsComponent, canActivate:[AdminGuard]},
            {path: 'departments/:id' + '/teachers', component: TeachersComponent, canActivate:[AdminGuard]},
            {path: 'department/:id', component: DepartmentComponent, canActivate: [AdminGuard]},
            {path: 'department', component: DepartmentComponent, canActivate: [AdminGuard]},
            {path: 'courses', component: CoursesComponent, canActivate: [AdminGuard]},
            {path: 'courses/:courseId', component: CourseComponent, canActivate: [AdminGuard]},
            {path: 'degree/:degreeId' + '/course', component: CourseComponent, canActivate: [AdminGuard]},
            {path: 'departments/:departmentId' + '/courses', component:CoursesComponent, canActivate: [AdminGuard]},
            {path: 'degree/:degreeId' + '/courses', component: CoursesComponent, canActivate: [AdminGuard]},
            {path: 'students', component: AdminStudentsComponent, canActivate: [AdminGuard]},
            {path: 'student/:id', component: StudentComponent, canActivate: [AdminGuard]},
            {path: 'student', component: StudentComponent, canActivate: [AdminGuard]},
            {path: 'degrees', component: DegreesComponent, canActivate: [AdminGuard]},
            {path: 'degree/:id', component:DegreeComponent, canActivate: [AdminGuard]},
            {path: 'degree', component: DegreeComponent, canActivate: [AdminGuard]},
            {path: 'degree/:id'  + '/groups', component: GroupsComponent, canActivate: [AdminGuard]},
            {path: 'degree/:degreeId' + '/group/:groupId'+'/lectures', component: LecturesComponent, canActivate:[AdminGuard]},
            {path: 'degree/:degreeId' + '/group/:groupId' + '/lecture/:lectureId',component: LectureComponent, canActivate:[AdminGuard]},
            {path: 'degree/:id' + '/group', component: GroupComponent, canActivate: [AdminGuard]},
            {path: 'degree/:id' + '/group/:groupId', component: GroupComponent, canActivate: [AdminGuard]},
            {path: 'teachers', component: TeachersComponent, canActivate:[AdminGuard]},
            {path: 'teacher', component: TeacherComponent, canActivate: [AdminGuard]},
            
            { path: '', redirectTo: 'dashboard', pathMatch: 'full' }
        ]
    }
];

@NgModule({
    imports: [
        RouterModule.forChild(suggestionRoutes)
    ],
    exports: [
        RouterModule
    ]
})
export class SuggestionRoutingModule { }
