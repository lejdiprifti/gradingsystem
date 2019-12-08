import { Role } from '@ikubinfo/core/models/role';
import { Group } from './group';


export interface User {
    id?: number,
    firstName?: string,
    fatherName?: string,
    lastName?: string,
    birthdate?: Date,
    role?: Role,
    username?: string,
    password?: string,
    personalNumber?: string,
    email?: string
}