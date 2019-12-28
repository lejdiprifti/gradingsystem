
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { AuthService } from '@ikubinfo/core/services/auth.service';
import { RoleEnum } from '@ikubinfo/core/models/role.enum';
import { LoggerService } from '@ikubinfo/core/utilities/logger.service';

@Component({
  selector: 'ikubinfo-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  constructor(
    private router: Router,
    private fb: FormBuilder,
    private authService: AuthService,
    private logger: LoggerService
  ) { }

  ngOnInit() {
    this.loginForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
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
}