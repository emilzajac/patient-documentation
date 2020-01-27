import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {RegisterComponent} from "./register/register.component";
import {HomeComponent} from "./home/home.component";
import {LogoutComponent} from "./logout/logout.component";
import {PatientAddComponent} from "./patient/patient-add/patient-add.component";
import {PatientListComponent} from "./patient/patient-list/patient-list.component";
import {DocumentationAddComponent} from "./documentation/documentation-add/documentation-add.component";
import {DocumentationPatientListComponent} from "./documentation/documentation-patient-list/documentation-patient-list.component";


const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'login', component: LoginComponent},
  {path: 'logout', component: LogoutComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'patient/add', component: PatientAddComponent},
  {path: 'patient/list', component: PatientListComponent},
  {path: 'documentation/add', component: DocumentationAddComponent},
  {path: 'documentation/add/:pesel', component: DocumentationAddComponent},
  {path: 'documentation/list/:doctorUsername', component: DocumentationPatientListComponent},
  {path: 'documentation/list/:patientId', component: DocumentationPatientListComponent},
  // otherwise redirect to home
  // {path: '**', redirectTo: ''}
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
