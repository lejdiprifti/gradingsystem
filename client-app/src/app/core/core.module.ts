
import { NgModule, Optional, SkipSelf } from '@angular/core';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { ConfirmationService, MessageService } from 'primeng/primeng';


import { AuthService } from '@ikubinfo/core/services/auth.service';
import { LoggerService } from '@ikubinfo/core/utilities/logger.service';
import { ApiService } from '@ikubinfo/core/utilities/api.service';
import { AuthGuard } from '@ikubinfo/core/guards/auth-guard';
import { TokenInterceptor } from '@ikubinfo/core/interceptors/token-interceptor';
import { StudentService } from './services/student.service';
import { GroupService } from './services/group.service';
import { DegreeService } from './services/degree.service';


@NgModule({
  imports: [
    HttpClientModule
  ],
  declarations: [],
  providers: [AuthService, LoggerService, ApiService, 
    AuthGuard,StudentService,GroupService,DegreeService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    }, ConfirmationService,
    MessageService]

})
export class CoreModule {
  /*Prevent reimport of the core module */
  constructor(@Optional() @SkipSelf() parentModule: CoreModule) {
    if (parentModule) {
      throw new Error(
        'CoreModule is already loaded. Import it in the AppModule only');
    }
  }
}
