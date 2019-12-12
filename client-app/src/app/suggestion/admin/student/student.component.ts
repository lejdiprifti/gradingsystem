import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { StudentService } from '@ikubinfo/core/services/student.service';
import { ActivatedRoute, Router } from '@angular/router';
import { LoggerService } from '@ikubinfo/core/utilities/logger.service';
import { Student } from '@ikubinfo/core/models/student';
import { Group } from '@ikubinfo/core/models/group';
import { SelectItem } from 'primeng/primeng';
import { GroupService } from '@ikubinfo/core/services/group.service';

@Component({
  selector: 'ikubinfo-student',
  templateUrl: './student.component.html',
  styleUrls: ['./student.component.css']
})
export class StudentComponent implements OnInit {

  groups: Array<Group>;
  studentForm: FormGroup;
  student: Student;
  constructor(private studentService: StudentService ,private fb: FormBuilder,private groupService: GroupService,
    private active: ActivatedRoute, private logger: LoggerService, private router: Router) { }

  ngOnInit() {
    this.initializeForm();
    this.loadData();
    this.groups=[];
  }

  loadData(): void {
    this.getGroups();
    const id = this.active.snapshot.paramMap.get('id');
    if (id) {
      this.studentService.getById(Number(id)).subscribe(data => {
          this.student = data;
          this.studentForm.get('firstName').setValue(this.student.firstName);
          this.studentForm.get('lastName').setValue(this.student.lastName);
          this.studentForm.get('fatherName').setValue(this.student.fatherName);
          this.studentForm.get('personalNumber').setValue(this.student.personalNumber);
          this.studentForm.get('group').setValue(this.student.group.id);
          this.studentForm.get('username').setValue(this.student.username);
          this.studentForm.get('password').setValue(this.student.password);
          this.studentForm.get('email').setValue(this.student.email);
        },
        err => {
          this.logger.error('Error', 'An error accured');
        });
    }
  }

  reset(): void {
    this.fillForm(this.student);
  }

  initializeForm(): void {
    this.studentForm = this.fb.group({
      firstName: ['', Validators.required],
      fatherName: ['', Validators.required],
      lastName: ['', Validators.required],
      personalNumber: ['' , Validators.required],
      group: ['', Validators.required],
      email: ['', Validators.required],
      username: ['', Validators.required],
      password: ['', Validators.required],
      birthdate: ['', Validators.required]
    });
  }

  fillForm(data: Student = {}): void {
    this.studentForm.get('firstName').setValue(data.firstName);
    this.studentForm.get('fatherName').setValue(data.fatherName);
    this.studentForm.get('lastName').setValue(data.lastName);
    this.studentForm.get('email').setValue(data.email);
    this.studentForm.get('personalNumber').setValue(data.personalNumber);
    this.studentForm.get('username').setValue(data.username);
    this.studentForm.get('group').setValue(data.group.id);
    this.studentForm.get('birthdate').setValue(data.birthdate);
    this.studentForm.get('password').setValue(data.password);
  }

  getData(): Student {
    return {
      firstName: this.studentForm.get('firstName').value,
      fatherName: this.studentForm.get('fatherName').value,
      lastName: this.studentForm.get('lastName').value,
      email: this.studentForm.get('email').value,
      personalNumber: this.studentForm.get('personalNumber').value,
      group: this.studentForm.get('group').value,
      username: this.studentForm.get('username').value,
      password: this.studentForm.get('password').value,
      birthdate: this.studentForm.get('birthdate').value,
    }

  }

  submit(): void {
    if (this.student) {
      this.studentService.edit(Number(this.student.id), this.getData()).subscribe(res => {
        this.logger.info('Success', 'Student was successfully added.');
        this.router.navigate(['feut/students']);

      },
      err => {
        this.logger.error('Error', 'Student already exists.');
      });
    }
    else {
      this.studentService.save(this.getData()).subscribe(res => {
        this.logger.info('Success', 'Student was added successfully.');
        this.router.navigate(['feut/students']);
      },
      err => {
        this.logger.error('Error', 'Student already exists.');
      });

    }

  }

  getGroups() : Object{
    return this.groupService.getAll().subscribe(res=>{
      this.groups=res;
    },
    err=>{
      this.logger.error("Error","Something bad happened.");
    })
  }

}
