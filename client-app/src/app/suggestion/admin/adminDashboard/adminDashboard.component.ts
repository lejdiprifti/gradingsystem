import { Component, OnInit } from '@angular/core';
import { DegreeService } from '@ikubinfo/core/services/degree.service';
import { Degree } from '@ikubinfo/core/models/degree';
import { element } from 'protractor';

@Component({
  selector: 'ikubinfo-adminDashboard',
  templateUrl: './adminDashboard.component.html',
  styleUrls: ['./adminDashboard.component.css']
})
export class AdminDashboardComponent implements OnInit {
   data:any;
   degrees: Array<Degree>;
   numberOfGroups: number[];
   degreeNames: string[];
  constructor(private degreeService: DegreeService) { }

  ngOnInit() {
    this.degreeNames= [];
    this.numberOfGroups = [];
    this.getDegrees();
  }
  
  getDegrees(): void {
    this.degreeService.getAll().subscribe(res => {
      this.degrees = res;
      this.degrees.forEach(element => {
        this.degreeNames.push(element.title);
        this.numberOfGroups.push(element.numberOfGroups);
      })
      this.data = {
        datasets: [{
          data: this.numberOfGroups,
          backgroundColor: [
              "#FF6384",
              "#4BC0C0",
              "#FFCE56",
              "#E7E9ED",
              "#36A2EB"
          ],
          label: 'My dataset'
      }],
      labels: this.degreeNames
  
    } 
    },
    err=> {

    });
  }

}
