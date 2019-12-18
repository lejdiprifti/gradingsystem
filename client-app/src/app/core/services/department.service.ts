import { Injectable } from '@angular/core';
import { ApiService } from '../utilities/api.service';
import { Observable } from 'rxjs';
import { Department } from '../models/department';
import { Teacher } from '../models/teacher';

@Injectable({
  providedIn: 'root'
})
export class DepartmentService {
  url = 'departments'
constructor(private apiService: ApiService) { }

  public getAll(): Observable<Array<Department>>{
    return this.apiService.get(this.url);
  }

  public getById(id: number): Observable<Department>{
    return this.apiService.get(this.url + '/' + id);
  }

  public getTeachersByDepartment(id: number): Observable<Array<Teacher>>{
    return this.apiService.get(this.url + '/' +id+'/teachers');
  }
  public add(department: Department): Observable<void>{
    return this.apiService.post(this.url, department);
  }

  public edit(id: number, department: Department): Observable<void>{
    return this.apiService.put(this.url + '/'+id, department);
  }

  public delete(id: number): Observable<void>{
    return this.apiService.delete(this.url + '/' + id);
  }
}
