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
    icon: 'fa fa-th-list',
    label: 'My degrees',
    allowedRoles: [RoleEnum.TEACHER]
},
{
    url: '/feut/student/dashboard',
    icon: 'fa fa-dashboard',
    label: 'Dashboard',
    allowedRoles: [RoleEnum.STUDENT]
},
{
    url: '/feut/student/grades',
    icon: 'fa fa-fw fa-edit',
    label: 'My Grades',
    allowedRoles: [RoleEnum.STUDENT]
},
{
    url: '/feut/students',
    icon: 'fa fa-user',
    label: 'Students',
    allowedRoles: [RoleEnum.ADMIN]
},
{
    url: '/feut/student/courses',
    icon: 'fa fa-th-list',
    label: 'My Courses',
    allowedRoles: [RoleEnum.STUDENT]
},
{
    url: '/feut/degrees',
    icon: 'fas fa-book',
    label: 'Degrees',
    allowedRoles: [RoleEnum.ADMIN]
},
{ url: '/feut/teachers',
icon: 'fa fa-th-list',
label: 'Teachers',
allowedRoles: [RoleEnum.ADMIN]
},
{
    url: '/feut/departments',
    icon: 'fa-table',
    label: 'Departments',
    allowedRoles: [RoleEnum.ADMIN]
},{
    url: '/feut/student/slacks',
    icon: 'fas fa-paper-plane',
    label: 'Slack',
    allowedRoles: [RoleEnum.STUDENT]
}]