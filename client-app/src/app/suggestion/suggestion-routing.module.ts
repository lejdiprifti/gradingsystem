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

const suggestionRoutes: Routes = [
    {
        path: '',
        component: FullComponent,
        children: [
            { path: 'dashboard', component: StudentDashboardComponent },
            {path: 'departments', component: DepartmentsComponent, canActivate:[AdminGuard]},
            {path: 'departments/:id' + '/teachers', component: TeachersComponent, canActivate:[AdminGuard]},
            {path: 'department/:id', component: DepartmentComponent, canActivate: [AdminGuard]},
            {path: 'department', component: DepartmentComponent, canActivate: [AdminGuard]},
            {path: 'students', component: AdminStudentsComponent, canActivate: [AdminGuard]},
            {path: 'student/:id', component: StudentComponent, canActivate: [AdminGuard]},
            {path: 'student', component: StudentComponent, canActivate: [AdminGuard]},
            {path: 'degrees', component: DegreesComponent, canActivate: [AdminGuard]},
            {path: 'degree/:id', component:DegreeComponent, canActivate: [AdminGuard]},
            {path: 'degree', component: DegreeComponent, canActivate: [AdminGuard]},
            {path: 'degree/:id'  + '/groups', component: GroupsComponent, canActivate: [AdminGuard]},
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
