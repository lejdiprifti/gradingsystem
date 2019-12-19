import { Injectable } from '@angular/core';
import { ApiService } from '../utilities/api.service';
import { Observable } from 'rxjs';
import { Degree } from '../models/degree';
import { Group } from '../models/group';
import { Course } from '../models/course';

@Injectable({
  providedIn: 'root'
})
export class DegreeService {
  url = 'degree'
constructor(private apiService: ApiService) { }

  public getAll(): Observable<Array<Degree>>{
    return this.apiService.get(this.url);
  }

  public getById(id:number): Observable<Degree>{
    return this.apiService.get(this.url + '/' + id);
  }

  public getGroups(id:number): Observable<Array<Group>>{
    return this.apiService.get(this.url + '/' +id+'/groups');
  }

  public getCoursesByDegree(id: number): Observable<Array<Course>>{
    return this.apiService.get(this.url + '/' +id+'/courses');
  }

  public save(degree: Degree): Observable<void>{
    return this.apiService.post(this.url, degree);
  }

  public addGroup(group: Group, id:number): Observable<void>{
    return this.apiService.post(this.url + '/' + id +'/groups', group);
  }

  public edit(id:number, degree:Degree): Observable<void>{
    return this.apiService.put(this.url + '/' + id, degree);
  }

  public delete(id: number): Observable<void>{
    return this.apiService.delete(this.url + '/' +id);
  }
}
