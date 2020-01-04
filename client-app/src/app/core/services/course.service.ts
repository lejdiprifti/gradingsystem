import { Injectable } from '@angular/core';
import { ApiService } from '../utilities/api.service';
import { Observable } from 'rxjs';
import { Course } from '../models/course';

@Injectable({
  providedIn: 'root'
})
export class CourseService {
  url = 'courses'
constructor(private apiService: ApiService) { }

  public getAll(): Observable<Array<Course>>{
    return this.apiService.get(this.url);
  }

  public getById(id: number): Observable<Course>{
    return this.apiService.get(this.url + '/' + id);
  }

  public add(course: Course): Observable<void>{
    return this.apiService.post(this.url, course);
  }

  public edit(id: number, course: Course): Observable<void>{
    return this.apiService.put(this.url + '/' +id, course);
  }

  public delete(id: number): Observable<void>{
    return this.apiService.delete(this.url+'/'+id);
  }
}
