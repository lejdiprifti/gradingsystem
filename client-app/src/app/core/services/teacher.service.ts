import { Injectable } from '@angular/core';
import { ApiService } from '../utilities/api.service';
import { Observable } from 'rxjs';
import { Teacher } from '../models/teacher';
import { Degree } from '../models/degree';

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
  public add(teacher: Teacher): Observable<void>{
    return this.apiService.post(this.url, teacher);
  }

  public edit(id: number, teacher: Teacher): Observable<void>{
    return this.apiService.put(this.url + '/'+id,teacher);
  }

  public delete(id: number): Observable<void>{
    return this.apiService.delete(this.url+'/'+id);
  }
}
