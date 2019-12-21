import { Component, OnInit } from '@angular/core';
import { TeacherService } from '@ikubinfo/core/services/teacher.service';
import { AuthService } from '@ikubinfo/core/services/auth.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Group } from '@ikubinfo/core/models/group';
import { LoggerService } from '@ikubinfo/core/utilities/logger.service';

@Component({
  selector: 'ikubinfo-teacherGroups',
  templateUrl: './teacherGroups.component.html',
  styleUrls: ['./teacherGroups.component.css']
})
export class TeacherGroupsComponent implements OnInit {

  groups: Array<Group>;
  constructor(private teacherSerivce: TeacherService, private authService: AuthService,
    private router: Router,private active: ActivatedRoute,
    private logger: LoggerService) { }

  ngOnInit() {
    this.getGroups();
  }

  getGroups(): void {
    console.log('Executing');
    const degreeId = this.active.snapshot.paramMap.get('degreeId');
    this.teacherSerivce.getGroupsByTeacherAndDegree(this.authService.user.id,Number(degreeId)).subscribe(res => {
      this.groups = res;
    },
    err =>{
      this.logger.error('Error', 'Something bad happened.');
    })
  }

}
