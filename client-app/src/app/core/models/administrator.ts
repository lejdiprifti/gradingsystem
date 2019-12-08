import { User } from './user';

export interface Administrator extends User{
    lastLogin?: Date
}