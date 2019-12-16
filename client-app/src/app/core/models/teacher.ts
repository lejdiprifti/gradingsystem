import { User } from './user';
import { Lectures } from './lectures';
import { Department } from './department';

export interface Teacher extends User {
    departmentId?: number,
    department?: Department,
    lecturesList?: Array<Lectures>
}