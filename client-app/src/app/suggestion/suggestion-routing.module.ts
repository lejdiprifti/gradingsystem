import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AdminGuard } from '@ikubinfo/core/guards/admin-guard';
import { FullComponent } from '@ikubinfo/layout/full/full.component';
import { StudentDashboardComponent } from './student/dashboard/dashboard.component';
import { AdminStudentsComponent } from './admin/AdminStudents/AdminStudents.component';
import { StudentComponent } from './admin/student/student.component';

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
