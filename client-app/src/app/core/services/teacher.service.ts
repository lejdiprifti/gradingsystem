import { Injectable } from '@angular/core';
import { ApiService } from '../utilities/api.service';
import { Observable } from 'rxjs';
import { Teacher } from '../models/teacher';
import { Degree } from '../models/degree';
import { Course } from '../models/course';
import { Group } from '../models/group';
import { Lectures } from '../models/lectures';

@Injectable({
  providedIn: 'root'
})
export class TeacherService {

  url = 'teachers'
constructor(private apiService: ApiService) { }

  public getAll(): Observable<Array<Teacher>>{
    return this.apiService.get(this.url);
  }

  public getById(id: number): Observable<Teacher>{
    return this.apiService.get(this.url + '/' + id);
  }

  public getDegreesByTeacher(id: number): Observable<Array<Degree>>{
    return this.apiService.get(this.url+'/'+id+'/degrees');
  }

  public getCoursesByTeacherAndDegree(teacherId: number, degreeId: number): Observable<Array<Course>>{
    return this.apiService.get(this.url+'/'+teacherId+'/degrees/'+degreeId+'/courses');
  }

  public getGroupsByTeacherAndDegree(teacherId: number, degreeId: number): Observable<Array<Group>>{
    return this.apiService.get(this.url+'/'+teacherId+'/degrees/'+degreeId+'/groups');
  }
  public add(teacher: Teacher): Observable<void>{
    return this.apiService.post(this.url, teacher);
  }

  public edit(id: number, teacher: Teacher): Observable<void>{
    return this.apiService.put(this.url + '/'+id,teacher);
  }

  public delete(id: number): Observable<void>{
    return this.apiService.delete(this.url+'/'+id);
  }

  public getAverageByCourseGroupAndTeacher(courseId:number, groupId: number, teacherId: number): Observable<number>{
    return this.apiService.get(this.url+'/'+teacherId+'/courses/'+courseId+'/groups/'+groupId);
  }

  public getLectures(teacherId: number): Observable<Array<Lectures>>{
    return this.apiService.get(this.url+'/'+teacherId+'/lectures');
  }
}
