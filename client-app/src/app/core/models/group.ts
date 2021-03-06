import { Degree } from './degree';
import { Student } from './student';

export interface Group {
    id?: number,
    number?: number,
    email?: string,
    degree?: Degree,
    studentList?: Array<Student>
}