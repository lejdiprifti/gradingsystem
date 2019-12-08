import { Group } from './group';
import { Teacher } from './teacher';
import { Course } from './course';

export interface Lectures {
    id?: number,
    group?: Group,
    teacher?: Teacher,
    course?: Course
}