import { BrowserModule } from '@angular/platform-browser';
import { NgModule }      from '@angular/core';

import { AppRoutingModule }                                          from './app-routing.module';
import { AppComponent }                                              from './app.component';
import { FormsModule, ReactiveFormsModule }                          from "@angular/forms";
import { HTTP_INTERCEPTORS, HttpClientModule, HttpClientXsrfModule } from "@angular/common/http";
import { MenuComponent }                                             from './menu/menu.component';
import { AlertComponent }                                            from './alert/alert.component';
import { ErrorInterceptor }                                          from "./interceptor/error.interceptor";
import { HomeComponent }                                             from './home/home.component';
import { AuthenticationInterceptor }                                 from "./interceptor/authentication.interceptor";
import { PatientAddComponent }                                       from './patient/patient-add/patient-add.component';
import { PatientListComponent }                                      from './patient/patient-list/patient-list.component';
import { BrowserAnimationsModule }                                   from '@angular/platform-browser/animations';
import {
  MatButtonModule,
  MatCardModule,
  MatFormFieldModule,
  MatIconModule,
  MatInputModule,
  MatListModule,
  MatPaginatorModule,
  MatProgressBarModule,
  MatSelectModule,
  MatSortModule,
  MatTableModule,
  MatToolbarModule
}                                            from "@angular/material";
import { DocumentationAddComponent }         from './documentation/documentation-add/documentation-add.component';
import { DocumentationPatientListComponent } from './documentation/documentation-patient-list/documentation-patient-list.component';
import { BsDatepickerModule }                from 'ngx-bootstrap/datepicker';
import { defineLocale }                      from 'ngx-bootstrap/chronos';
import { plLocale }                          from 'ngx-bootstrap/locale';
import { FooterComponent }                   from './footer/footer.component';
import { NgbDropdownModule }                 from "@ng-bootstrap/ng-bootstrap";
import { HttpXsrfInterceptor }               from './interceptor/HttpXsrfInterceptor';
import { LoginComponent }                    from './auth/login/login.component';
import { RegisterComponent }                 from './auth/register/register.component';
import { ConfirmEmailComponent }             from './auth/confirm-email/confirm-email.component';
import { ChangePasswordComponent }           from './auth/change-password/change-password.component';

defineLocale('pl', plLocale);

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    MenuComponent,
    RegisterComponent,
    AlertComponent,
    HomeComponent,
    PatientAddComponent,
    PatientListComponent,
    DocumentationAddComponent,
    DocumentationPatientListComponent,
    FooterComponent,
    ConfirmEmailComponent,
    ChangePasswordComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    HttpClientXsrfModule.withOptions({
      cookieName: 'XSRF-TOKEN',
      headerName: 'X-CSRF-TOKEN'
    }),
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatCardModule,
    MatInputModule,
    MatListModule,
    MatToolbarModule,
    MatSelectModule,
    MatFormFieldModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatProgressBarModule,
    MatIconModule,
    BsDatepickerModule.forRoot(),
    NgbDropdownModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: ErrorInterceptor,
      multi: true
    },

    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthenticationInterceptor,
      multi: true
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HttpXsrfInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
