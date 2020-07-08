import { NgModule }                           from '@angular/core';
import { NoPreloading, RouterModule, Routes } from '@angular/router';
import { HomeComponent }                      from "./home/home.component";
import { PatientAddComponent }                from "./patient/patient-add/patient-add.component";
import { PatientListComponent }               from "./patient/patient-list/patient-list.component";
import { DocumentationAddComponent }          from "./documentation/documentation-add/documentation-add.component";
import { DocumentationPatientListComponent }  from "./documentation/documentation-patient-list/documentation-patient-list.component";
import { AuthGuard }                          from "./guard/auth.guard";
import { LoginComponent }                     from './auth/login/login.component';
import { RegisterComponent }                  from './auth/register/register.component';
import { environment }                        from '../environments/environment';
import { ConfirmEmailComponent }              from './auth/confirm-email/confirm-email.component';
import { ChangePasswordComponent }            from './auth/change-password/change-password.component';


const routes: Routes = [
  {path: '', pathMatch: 'full', redirectTo: 'login'},
  {path: 'home', component: HomeComponent, canActivate: [AuthGuard]},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'confirm-email', component: ConfirmEmailComponent},
  {path: 'change-password/:token', component: ChangePasswordComponent},
  {path: 'patient/add', component: PatientAddComponent, canActivate: [AuthGuard]},
  {path: 'patient/list', component: PatientListComponent, canActivate: [AuthGuard]},
  {path: 'documentation/add', component: DocumentationAddComponent, canActivate: [AuthGuard]},
  {path: 'documentation/add/:pesel', component: DocumentationAddComponent, canActivate: [AuthGuard]},
  {path: 'documentation/list/:doctorUsername', component: DocumentationPatientListComponent, canActivate: [AuthGuard]},
  {path: 'documentation/list/patient/:patientId', component: DocumentationPatientListComponent, canActivate: [AuthGuard]},
  // otherwise redirect to home
  {path: '**', redirectTo: 'login'}
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, {
      useHash: !environment.production,
      anchorScrolling: 'enabled',
      scrollPositionRestoration: 'enabled',
      preloadingStrategy: NoPreloading,
    }),
  ],
  exports: [RouterModule],
})
export class AppRoutingModule {
}
