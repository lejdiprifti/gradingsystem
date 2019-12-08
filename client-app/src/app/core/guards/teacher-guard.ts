import { Injectable } from '@angular/core'

import { CanActivate, CanActivateChild, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router'

import { AuthService } from '../services/auth.service'
import { RoleEnum } from '../models/role.enum'

@Injectable({ providedIn: 'root' })
export class TeacherGuard implements CanActivate, CanActivateChild {

    constructor(private authService: AuthService) { }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot):boolean {
        return this.authService.role === RoleEnum.TEACHER
    }

    canActivateChild(childRoute: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
        return this.authService.role === RoleEnum.TEACHER
    }
}