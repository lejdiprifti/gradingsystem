import { Course } from './course';
import { Teacher } from './teacher';

export interface Department {
    id?: number,
    name?: string,
    description?: string,
    courseList?: Array<Course>,
    teacherList?: Array<Teacher>
}