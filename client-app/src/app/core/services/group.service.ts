import { Injectable } from '@angular/core';
import { ApiService } from '../utilities/api.service';
import { Observable } from 'rxjs';
import { Group } from '../models/group';

@Injectable({
  providedIn: 'root'
})
export class GroupService {
  url = 'group'
constructor(private apiService: ApiService) { }

  public getAll(): Observable<Array<Group>>{
    return this.apiService.get(this.url);
  }

  public getById(id: number): Observable<Group> {
    return this.apiService.get(this.url + '/' + id);
  }

  public save(group: Group): Observable<void> { 
    return this.apiService.post(this.url, group);
  }

  public edit(id:number, group: Group): Observable<void>{
    return this.apiService.put(this.url + '/' + id, group);
  }

  public delete(id: number){
    return this.apiService.delete(this.url + '/' +id);
  }
}
