import { NgModule }                from '@angular/core';
import { RouterModule, Routes }    from '@angular/router';
import { LoginComponent }          from './login/login.component';
import { RegisterComponent }       from './register/register.component';
import { ConfirmEmailComponent }   from './confirm-email/confirm-email.component';
import { ChangePasswordComponent } from './change-password/change-password.component';

const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    redirectTo: 'login'
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'register',
    component: RegisterComponent
  },
  {
    path: 'confirm-email',
    component: ConfirmEmailComponent
  },
  {
    path: 'change-password/:token',
    component: ChangePasswordComponent
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AuthRoutingModule {
}
