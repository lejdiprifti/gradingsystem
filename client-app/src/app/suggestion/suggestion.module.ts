import { CommonsModule } from '@ikubinfo/commons/commons.module';

import { NgModule } from '@angular/core';

import { SuggestionRoutingModule } from '@ikubinfo/suggestion/suggestion-routing.module';
import { LayoutModule } from '@ikubinfo/layout/layout.module';
import { FormsModule } from '@angular/forms';
import { StudentDashboardComponent } from './student/dashboard/dashboard.component';
import { AdminStudentsComponent } from './admin/AdminStudents/AdminStudents.component';
import { StudentComponent } from './admin/student/student.component';
import { DegreesComponent } from './admin/degrees/degrees.component';
import { GroupsComponent } from './admin/groups/groups.component';
import { DegreeComponent } from './admin/degree/degree.component';
import { GroupComponent } from './admin/group/group.component';
import { TeachersComponent } from './admin/teachers/teachers.component';
import { DepartmentService } from '@ikubinfo/core/services/department.service';
import { DepartmentsComponent } from './admin/departments/departments.component';
import { DepartmentComponent } from './admin/department/department.component';
import { TeacherComponent } from './admin/teacher/teacher.component';

@NgModule({
    imports: [CommonsModule, SuggestionRoutingModule, LayoutModule, FormsModule],
    exports: [],
    declarations: [StudentDashboardComponent, AdminStudentsComponent,StudentComponent, 
        DegreesComponent,DepartmentComponent,TeacherComponent,
         GroupsComponent,DegreeComponent, GroupComponent, TeachersComponent,DepartmentsComponent],
    providers: []
})
export class SuggestionModule { }
