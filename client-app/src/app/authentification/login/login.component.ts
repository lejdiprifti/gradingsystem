
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { AuthService } from '@ikubinfo/core/services/auth.service';
import { RoleEnum } from '@ikubinfo/core/models/role.enum';
import { LoggerService } from '@ikubinfo/core/utilities/logger.service';
import { SlackService } from '@ikubinfo/core/services/slack.service';
import { Slack } from '@ikubinfo/core/models/slack';

@Component({
  selector: 'ikubinfo-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  slack: Slack;
  constructor(
    private router: Router,
    private fb: FormBuilder,
    private authService: AuthService,
    private logger: LoggerService,
    private slackService: SlackService
  ) { }

  ngOnInit() {
    this.slack = {};
    this.loginForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
    this.checkCode();
  }

  login(): void {
    this.authService.login(this.loginForm.value).subscribe(
    (res: any)=>{
     this.authService.setData(res);
     if (this.authService.user.role.id === RoleEnum.ADMIN){
       this.router.navigate(['feut/dashboard']);
     } else if (this.authService.user.role.id === RoleEnum.TEACHER) {
       this.router.navigate(['feut/teacher/dashboard']);
     } else {
       this.router.navigate(['feut/student/dashboard'])
     }
     this.logger.success('Success', 'You logged in successfully!');
    },
    err => {
     this.logger.error('Error', 'Invalid username or password');
   });
   }

   checkCode(): void {
     if (this.router.url.includes('code')){
       this.slackService.getSlackUrl(this.router.url.slice(12,102)).then(res => {
          this.slack.channelName = res.incoming_webhook.channel;
          this.slack.channelId = res.incoming_webhook.channel_id;
          this.slack.url = res.incoming_webhook.url;
          this.slack.studentId = this.authService.user.id;
          this.slack.botToken = res.bot.bot_access_token;
          this.slackService.saveUrl(this.slack).subscribe(res => {
            this.logger.success('Success', 'Slack configured in channel '+this.slack.channelName);
          }, err=>{
            this.logger.error('Error', 'Something bad happened.');
          })
       });
     }
   }
}