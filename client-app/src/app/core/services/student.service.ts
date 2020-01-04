import { Injectable } from '@angular/core';
import { ApiService } from '../utilities/api.service';
import { FormBuilder } from '@angular/forms';
import { getAllLifecycleHooks } from '@angular/compiler/src/lifecycle_reflector';
import { Student } from '../models/student';
import { Observable } from 'rxjs';
import { Grade } from '../models/grade';
import { Course } from '../models/course';

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  private url = 'student'

constructor(private apiService: ApiService, private fb: FormBuilder) { }

getAll(): Observable<Array<Student>>  {
  return this.apiService.get(this.url);
}

getById(id: number): Observable<Student>{
  return this.apiService.get(this.url + '/' + id);
}

save(student: Student): Observable<void>{
  return this.apiService.post(this.url, student);
}

edit(id: number, student: Student): Observable<void>{
  return this.apiService.put(this.url + '/' + id, student);
}

delete(id: number): Observable<void>{
  return this.apiService.delete(this.url + '/' + id);
}

getGrades(id: number): Observable<Array<Grade>>{
  return this.apiService.get(this.url+'/'+id+'/grades');
}

getCourses(id: number): Observable<Array<Course>>{
  return this.apiService.get(this.url+'/'+id+'/courses');
}
}


