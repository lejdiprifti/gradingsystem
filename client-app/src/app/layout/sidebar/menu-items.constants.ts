import { RoleEnum } from './../../core/models/role.enum';
import { MenuItem } from './menu-item'

export const menuItems: Array<MenuItem> = [{
    url: '/feut/dashboard',
    icon: 'fa-dashboard',
    label: 'Dashboard',
    allowedRoles: [RoleEnum.ADMIN]
},{
    url: '/feut/teacher/dashboard',
    icon: 'fa-dashboard',
    label: 'Dashboard',
    allowedRoles: [RoleEnum.TEACHER]
},{
    url: '/feut/teacher/degrees',
    icon: 'fa-dashboard',
    label: 'My degrees',
    allowedRoles: [RoleEnum.TEACHER]
},
{
    url: '/feut/student/dashboard',
    icon: 'fa-dashboard',
    label: 'Dashboard',
    allowedRoles: [RoleEnum.STUDENT]
},
{
    url: '/feut/student/grades',
    icon: 'fa-dashboard',
    label: 'My Grades',
    allowedRoles: [RoleEnum.STUDENT]
},
{
    url: '/feut/students',
    icon: 'fa-dashboard',
    label: 'Students',
    allowedRoles: [RoleEnum.ADMIN]
},
{
    url: '/feut/student/courses',
    icon: 'fa-table',
    label: 'My Courses',
    allowedRoles: [RoleEnum.STUDENT]
},
{
    url: '/feut/degrees',
    icon: 'fa-table',
    label: 'Degrees',
    allowedRoles: [RoleEnum.ADMIN]
},
{ url: '/feut/teachers',
icon: 'fa-table',
label: 'Teachers',
allowedRoles: [RoleEnum.ADMIN]
},
{
    url: '/feut/departments',
    icon: 'fa-table',
    label: 'Departments',
    allowedRoles: [RoleEnum.ADMIN]
}]