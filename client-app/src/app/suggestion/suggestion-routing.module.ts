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

const suggestionRoutes: Routes = [
    {
        path: '',
        component: FullComponent,
        children: [
            { path: 'dashboard', component: StudentDashboardComponent },
            { path: 'posts', component: StudentDashboardComponent, canActivate: [AdminGuard] },
            { path: 'post/:id', component: StudentDashboardComponent, canActivate: [AdminGuard] },
            {path: 'students', component: AdminStudentsComponent, canActivate: [AdminGuard]},
            {path: 'student/:id', component: StudentComponent, canActivate: [AdminGuard]},
            {path: 'student', component: StudentComponent, canActivate: [AdminGuard]},
            {path: 'degrees', component: DegreesComponent, canActivate: [AdminGuard]},
            {path: 'degree/:id', component:DegreeComponent, canActivate: [AdminGuard]},
            {path: 'degree', component: DegreeComponent, canActivate: [AdminGuard]},
            {path: 'degree/:id'  + '/groups', component: GroupsComponent, canActivate: [AdminGuard]},
            {path: 'degree/:id' + '/group', component: GroupComponent, canActivate: [AdminGuard]},
            {path: 'degree/:id' + '/group/:groupId', component: GroupComponent, canActivate: [AdminGuard]},
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
