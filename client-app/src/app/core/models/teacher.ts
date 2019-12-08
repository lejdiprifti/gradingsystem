import { User } from './user';
import { Lectures } from './lectures';
import { Department } from './department';

export interface Teacher extends User {
    department?: Department,
    lecturesList?: Array<Lectures>
}