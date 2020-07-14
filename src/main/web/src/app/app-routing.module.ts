import { NgModule }                           from '@angular/core';
import { NoPreloading, RouterModule, Routes } from '@angular/router';
import { HomeComponent }                      from "./home/home.component";
import { PatientAddComponent }                from "./patient/patient-add/patient-add.component";
import { PatientListComponent }               from "./patient/patient-list/patient-list.component";
import { DocumentationAddComponent }          from "./documentation/documentation-add/documentation-add.component";
import { DocumentationPatientListComponent }  from "./documentation/documentation-patient-list/documentation-patient-list.component";
import { environment }                        from '../environments/environment';
import { AuthGuard }                          from './auth/guards/auth.guard';


const routes: Routes = [
  {
    path: '',
    redirectTo: 'auth',
    pathMatch: 'full',
  },
  {
    path: 'auth',
    loadChildren: () => import('./auth/auth.module').then(module => module.AuthModule),
  },

  {path: 'home', component: HomeComponent, canActivate: [AuthGuard]},
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
