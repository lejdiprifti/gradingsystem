import { Component, OnInit } from '@angular/core';
import { GroupService } from '@ikubinfo/core/services/group.service';
import { Group } from '@ikubinfo/core/models/group';
import { DegreeService } from '@ikubinfo/core/services/degree.service';
import { ActivatedRoute, Router } from '@angular/router';
import { LoggerService } from '@ikubinfo/core/utilities/logger.service';
import { MenuItem } from 'primeng/primeng';

@Component({
  selector: 'ikubinfo-groups',
  templateUrl: './groups.component.html',
  styleUrls: ['./groups.component.css']
})
export class GroupsComponent implements OnInit {

  groups: Array<Group>;
  items: MenuItem[];
  cols: any[];
  selectedGroup: Group;
  constructor(private degreeService: DegreeService, private activated: ActivatedRoute,
    private logger: LoggerService, private router: Router) { }

  ngOnInit() {
    this.loadGroups();
    this.items = [
      { label: 'Edit', icon: 'pi pi-pencil', command: (event) =>  this.editGroup(this.selectedGroup)},
      { label: 'Delete', icon: 'pi pi-times', command: (event) =>  this.deleteGroup(this.selectedGroup)}
    ];

    this.cols = [
      {field: 'id', header: 'ID' },
      {field: 'number', header: 'Number' },
      {field: 'email', header: 'E-mail' },
      {field: 'degree', header: 'Degree'},
      {field: 'studentList', header: 'Students'}
    ];
  }

  loadGroups(): void{
    const id = this.activated.snapshot.paramMap.get('id');
    if (id) {
    this.degreeService.getGroups(Number(id)).subscribe(res=>{
      this.groups = res;
    }, err => {
      this.logger.error('Error','Something bad happened.');
    });
  }
}

  editGroup(group: Group): void{
    const id = this.activated.snapshot.paramMap.get('id');
    this.router.navigate(['feut/degree/' + id +'/group/' + group.id]);
  }

  deleteGroup(group: Group): void{

  }

  addGroup(): void {
    const id = this.activated.snapshot.paramMap.get('id');
    this.router.navigate(['feut/degree/' + id +'/group']);
  }

}
