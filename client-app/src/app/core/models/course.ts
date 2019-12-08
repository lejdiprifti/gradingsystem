import { Department } from './department';
import { Degree } from './degree';
import { Lectures } from './lectures';

export interface Course {
    id?: number,
    department?: Department,
    degree?: Degree,
    syllabus?: string,
    lectureList?: Array<Lectures>
}