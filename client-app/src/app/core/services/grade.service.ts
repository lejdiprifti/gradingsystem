import { Injectable } from '@angular/core';
import { ApiService } from '../utilities/api.service';
import { Observable } from 'rxjs';
import { Grade } from '../models/grade';

@Injectable({
  providedIn: 'root'
})
export class GradeService {
  url = 'grades'
constructor(private apiService: ApiService) { }

  public getGradesByGroupAndCourse(groupId: number, courseId: number): Observable<Array<Grade>>{
    return this.apiService.get('group/'+groupId+'/courses/'+courseId+'/grades');
  }

  public getById(gradeId: number): Observable<Grade>{
    return this.apiService.get(this.url + '/'+gradeId);
  }

  public edit(grade: Grade, id: number): Observable<void> {
    return this.apiService.put(this.url + '/' + id, grade);
  }
}
