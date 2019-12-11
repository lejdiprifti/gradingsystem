import { Component, OnInit } from '@angular/core';
import { DegreeService } from '@ikubinfo/core/services/degree.service';
import { Degree } from '@ikubinfo/core/models/degree';
import { LoggerService } from '@ikubinfo/core/utilities/logger.service';
import { Router } from '@angular/router';

@Component({
  selector: 'ikubinfo-degrees',
  templateUrl: './degrees.component.html',
  styleUrls: ['./degrees.component.css']
})
export class DegreesComponent implements OnInit {

  degrees: Array<Degree>;

  constructor(private degreeService: DegreeService, private logger: LoggerService, private router: Router) {
    this.degrees = [];
   }

  ngOnInit() {
    this.loadDegrees();
  }

  loadDegrees(): void{
    this.degreeService.getAll().subscribe(res=>{
      this.degrees = res;
    },
    err=>{
      this.logger.error('Error', 'Something bad happened.');
    });
  }

  openGroups(id: number): void {
    this.router.navigate(['feut/degree/' + id +'/groups']);
  }

  addDegree(): void{
    this.router.navigate(['feut/degree']);
  }
}
