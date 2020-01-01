import { Component, OnInit } from '@angular/core';
import { LoggerService } from '@ikubinfo/core/utilities/logger.service';
import { SlackService } from '@ikubinfo/core/services/slack.service';
import { Slack } from '@ikubinfo/core/models/slack';
import { MenuItem } from 'primeng/primeng';
import { AuthService } from '@ikubinfo/core/services/auth.service';

@Component({
  selector: 'ikubinfo-slack',
  templateUrl: './slack.component.html',
  styleUrls: ['./slack.component.css']
})
export class SlackComponent implements OnInit {

  slacks: Array<Slack>;
  selectedSlack: Slack;
  items: MenuItem[];
  cols: any;
  constructor(private authService: AuthService,
    private slackService: SlackService,private logger: LoggerService) { }

  ngOnInit() {
    this.items = [
      { label: 'Activate', icon: 'pi pi-times', command: (event) =>  this.activate(this.selectedSlack)},
      { label: 'Deactivate', icon: 'pi pi-times', command: (event) =>  this.deactivate(this.selectedSlack)}];
    this.cols = [
      {field: 'channelName', header: 'Channel Name'},
      {field: 'channelId', header: 'Channel Id'},
      {field: 'active', header: 'Active'}];
      this.loadSlacks();
    }

  loadSlacks(): void{
    this.slackService.getByStudent(this.authService.user.id).subscribe(res => {
      this.slacks = res;
    }, err => {
      this.logger.error('Error','Something bad happened.');
    })
  }

  activate(slack: Slack): void {
    this.slackService.activate(slack.id).subscribe(res => {
      this.loadSlacks();
      this.logger.success('Success', slack.channelName+' activated successfully!');
    }, err=> {
      this.logger.error('Error', 'Something bad happened.');
    })
  }

  deactivate(slack: Slack): void {
    this.slackService.delete(slack.id).subscribe(res => {
      this.loadSlacks();
      this.logger.success('Success', slack.channelName+' deactivated successfully!');
    }, err=>{
      this.logger.error('Error', 'Something bad happpened.');
    })
  }

}
