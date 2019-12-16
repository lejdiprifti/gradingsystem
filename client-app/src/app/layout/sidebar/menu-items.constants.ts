import { RoleEnum } from './../../core/models/role.enum';
import { MenuItem } from './menu-item'

export const menuItems: Array<MenuItem> = [{
    url: '/suggestion/dashboard',
    icon: 'fa-dashboard',
    label: 'Dashboard',
    allowedRoles: [RoleEnum.ADMIN, RoleEnum.STUDENT]
}, {
    url: '/feut/students',
    icon: 'fa-dashboard',
    label: 'Students',
    allowedRoles: [RoleEnum.ADMIN]
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
    url: '/suggestion/posts',
    icon: 'fa-table',
    label: 'Manage posts',
    allowedRoles: [RoleEnum.ADMIN]

}]