import { NgModule }                from '@angular/core';
import { CommonModule }            from '@angular/common';
import { ChangePasswordComponent } from './change-password/change-password.component';
import { ConfirmEmailComponent }   from './confirm-email/confirm-email.component';
import { LoginComponent }          from './login/login.component';
import { RegisterComponent }       from './register/register.component';
import { AuthRoutingModule }       from './auth.routing.module';
import { PasswordService }         from './password.service';
import { ReactiveFormsModule }     from '@angular/forms';
import { AuthenticationService }   from './authentication.service';

const SERVICES = [PasswordService, AuthenticationService];
const GUARDS = [];
const RESOLVERS = [];

@NgModule({
  imports: [
    CommonModule,
    AuthRoutingModule,
    ReactiveFormsModule
  ],
  declarations: [
    RegisterComponent,
    LoginComponent,
    ConfirmEmailComponent,
    ChangePasswordComponent,
  ],
  providers: [
    ...SERVICES,
    ...RESOLVERS,
    ...GUARDS
  ]

})
export class AuthModule {
}
