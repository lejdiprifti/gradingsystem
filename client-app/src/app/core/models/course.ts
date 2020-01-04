import { Department } from './department';
import { Degree } from './degree';
import { Lectures } from './lectures';

export interface Course {
    id?: number,
    name?: string,
    departmentId?: number,
    degreeId?: number,
    department?: Department,
    degree?: Degree,
    syllabus?: string,
    lectureList?: Array<Lectures>
}