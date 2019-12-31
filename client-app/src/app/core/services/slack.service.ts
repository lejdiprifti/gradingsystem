import { Injectable } from '@angular/core';
import { ApiService } from '../utilities/api.service';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class SlackService {
  private client_secret = 'ab5b0b541d54860c1d757cc6489858bd';
  private client_id = '837287798948.876662464226'
constructor() { }

public async getAccessToken(code: string) {
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
response.json().then(res => {
  console.log(res.incoming_webhook.url);
});
}
}

