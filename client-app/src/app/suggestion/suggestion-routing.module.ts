import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AdminGuard } from '@ikubinfo/core/guards/admin-guard';
import { FullComponent } from '@ikubinfo/layout/full/full.component';
import { DashboardComponent } from './student/dashboard/dashboard.component';

const suggestionRoutes: Routes = [
    {
        path: '',
        component: FullComponent,
        children: [
            { path: 'dashboard', component: DashboardComponent },
            { path: 'posts', component: DashboardComponent, canActivate: [AdminGuard] },
            { path: 'post/:id', component: DashboardComponent, canActivate: [AdminGuard] },
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
