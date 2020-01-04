import { Component, OnInit } from '@angular/core';
import { DegreeService } from '@ikubinfo/core/services/degree.service';
import { LoggerService } from '@ikubinfo/core/utilities/logger.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Degree } from '@ikubinfo/core/models/degree';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'ikubinfo-degree',
  templateUrl: './degree.component.html',
  styleUrls: ['./degree.component.css']
})
export class DegreeComponent implements OnInit {

  degree: Degree;
  degreeForm: FormGroup;
  
  constructor(private degreeService: DegreeService, private active: ActivatedRoute,
    private logger: LoggerService, private fb:FormBuilder, private router: Router) { }

  ngOnInit() {
    this.loadData();
    this.initializeForm();
  }

  loadData(): void {
    const id = this.active.snapshot.paramMap.get('id');
    if (id) {
      this.degreeService.getById(Number(id)).subscribe(data => {
          this.degree = data;
          this.degreeForm.get('title').setValue(this.degree.title);
          this.degreeForm.get('syllabus').setValue(this.degree.syllabus);
        },
        err => {
          this.logger.error('Error', 'An error accured');
        });
    }
  }

  reset(): void {
    this.fillForm(this.degree);
  }

  initializeForm(): void {
    this.degreeForm = this.fb.group({
      title: ['', Validators.required],
      syllabus: ['', Validators.required]
    });
  }

  fillForm(data: Degree = {}): void {
    this.degreeForm.get('title').setValue(data.title);
    this.degreeForm.get('syllabus').setValue(data.syllabus);
  }

  getData(): Degree {
    return {
      title: this.degreeForm.get('title').value,
      syllabus: this.degreeForm.get('syllabus').value
    }

  }

  submit(): void {
    if (this.degree) {
      this.degreeService.edit(Number(this.degree.id), this.getData()).subscribe(res => {
        this.logger.success('Success', 'Degree was successfully added.');
        this.router.navigate(['feut/degrees']);

      },
      err => {
        this.logger.error('Error', 'Student already exists.');
      });
    }
    else {
      this.degreeService.save(this.getData()).subscribe(res => {
        this.logger.info('Success', 'Degree was added successfully.');
        this.router.navigate(['feut/degrees']);
      },
      err => {
        this.logger.error('Error', 'Degree already exists.');
      });

    }

  }

}
