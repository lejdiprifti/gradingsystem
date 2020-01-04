import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { LoginComponent } from '@ikubinfo/authentification/login/login.component';
import { SimpleComponent } from '@ikubinfo/layout/simple/simple.component';

const authentificationRoutes: Routes = [
    {
        path: '',
        component: SimpleComponent,
        children: [
            {
                path: 'login',
                component: LoginComponent
            },
            { path: '', redirectTo: '/login', pathMatch: 'full' },
        ]
    }
];

@NgModule({
    imports: [
        RouterModule.forChild(authentificationRoutes)
    ],
    exports: [
        RouterModule
    ]
})
export class AuthentificationRoutingModule { }
