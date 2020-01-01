import { Injectable } from '@angular/core';
import { ApiService } from '../utilities/api.service';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Slack } from '../models/slack';

@Injectable({
  providedIn: 'root'
})
export class SlackService {
  private client_secret = 'ab5b0b541d54860c1d757cc6489858bd';
  private client_id = '837287798948.876662464226'
  private url = 'slack'
constructor(private apiService: ApiService) { }

public async getSlackUrl(code: string): Promise<any> {
  let body = new URLSearchParams();
  body.set('client_secret', this.client_secret);
  body.set('client_id', this.client_id);
  body.set('code', code);
let response = await fetch('https://slack.com/api/oauth.access', {
  method: 'POST',
  headers: {
    'Content-type': 'application/x-www-form-urlencoded'
  },
  body: body.toString()
})
return response.json();
}

public getByStudent(studentId: number): Observable<Array<Slack>>{
  return this.apiService.get(this.url + '/' +studentId);
}
public saveUrl(slack: Slack): Observable<void>{
  return this.apiService.post(this.url, slack);
}

public delete(id: number): Observable<void>{
  return this.apiService.delete(this.url+'/'+id);
}

public activate(id: number): Observable<void>{
  return this.apiService.put(this.url+'/'+id);
}
}

