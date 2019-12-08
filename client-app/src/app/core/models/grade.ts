import { Student } from './student';
import { Teacher } from './teacher';
import { Course } from './course';

export interface Grade {
    id?: number,
    student?: Student,
    teacher?: Teacher,
    course?: Course,
    comment?: string,
    code?: string,
    createdTime?: Date
}