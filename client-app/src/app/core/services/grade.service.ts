import { Injectable } from '@angular/core';
import { Group } from '../models/group';
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
}
