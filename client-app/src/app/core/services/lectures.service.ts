import { Injectable } from '@angular/core';
import { ApiService } from '../utilities/api.service';
import { Observable } from 'rxjs';
import { Lectures } from '../models/lectures';
import { group } from '@angular/animations';

@Injectable({
  providedIn: 'root'
})
export class LecturesService {

  url = 'lectures'
constructor(private apiService: ApiService) { }

  public getByGroupAndCourse(groupId: number, courseId: number): Observable<Lectures>{
    return this.apiService.get(this.url + '/group/'+groupId+'/course/'+courseId);
  }

  public save(lecture: Lectures): Observable<void>{
    return this.apiService.post(this.url, lecture);
  }
}
