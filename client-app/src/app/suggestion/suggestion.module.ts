import { CommonsModule } from '@ikubinfo/commons/commons.module';

import { NgModule } from '@angular/core';

import { SuggestionRoutingModule } from '@ikubinfo/suggestion/suggestion-routing.module';
import { LayoutModule } from '@ikubinfo/layout/layout.module';
import { FormsModule } from '@angular/forms';
import { DashboardComponent } from './student/dashboard/dashboard.component';

@NgModule({
    imports: [CommonsModule, SuggestionRoutingModule, LayoutModule, FormsModule],
    exports: [],
    declarations: [DashboardComponent],
    providers: []
})
export class SuggestionModule { }
