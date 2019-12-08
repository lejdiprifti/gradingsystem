import { CommonsModule } from '@ikubinfo/commons/commons.module';

import { NgModule } from '@angular/core';

import { SuggestionRoutingModule } from '@ikubinfo/suggestion/suggestion-routing.module';
import { LayoutModule } from '@ikubinfo/layout/layout.module';
import { FormsModule } from '@angular/forms';
import { StudentDashboardComponent } from './student/dashboard/dashboard.component';

@NgModule({
    imports: [CommonsModule, SuggestionRoutingModule, LayoutModule, FormsModule],
    exports: [],
    declarations: [StudentDashboardComponent],
    providers: []
})
export class SuggestionModule { }
