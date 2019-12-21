import { Group } from './group';
import { Course } from './course';

export interface Degree {
    id?: number,
    title?: string,
    syllabus?: string,
    groupList?: Array<Group>,
    courseList?: Array<Course>,
    numberOfGroups?: number
}