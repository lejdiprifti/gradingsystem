import { User } from './user';
import { Group } from './group';
import { Grade } from './grade';

export interface Student extends User {
    group?: Group;
    gradeList?: Array<Grade>;
    groupId?: number;
}