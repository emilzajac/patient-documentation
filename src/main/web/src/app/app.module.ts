import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {LoginComponent} from './login/login.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {MenuComponent} from './menu/menu.component';
import {LogoutComponent} from './logout/logout.component';
import {RegisterComponent} from './register/register.component';
import {AlertComponent} from './alert/alert.component';
import {ErrorInterceptor} from "./interceptor/error.interceptor";
import {HomeComponent} from './home/home.component';
import {AuthenticationInterceptor} from "./interceptor/authentication.interceptor";
import {PatientAddComponent} from './patient/patient-add/patient-add.component';
import {PatientListComponent} from './patient/patient-list/patient-list.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
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
} from "@angular/material";
import {DocumentationAddComponent} from './documentation/documentation-add/documentation-add.component';
import {DocumentationPatientListComponent} from './documentation/documentation-patient-list/documentation-patient-list.component';
import {BsDatepickerModule} from 'ngx-bootstrap/datepicker';
import {defineLocale} from 'ngx-bootstrap/chronos';
import {plLocale} from 'ngx-bootstrap/locale';

defineLocale('pl', plLocale);

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    MenuComponent,
    LogoutComponent,
    RegisterComponent,
    AlertComponent,
    HomeComponent,
    PatientAddComponent,
    PatientListComponent,
    DocumentationAddComponent,
    DocumentationPatientListComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
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
    BsDatepickerModule.forRoot()
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
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
