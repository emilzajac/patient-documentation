import { Component, OnInit }                  from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router }             from '@angular/router';
import { AlertService }                       from '../../service/alert.service';
import { MustMatch }                          from '../../helper/validators';
import { first }                              from 'rxjs/operators';
import { PasswordService }                    from '../password.service';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.scss']
})
export class ChangePasswordComponent implements OnInit {
  changePasswordForm: FormGroup;
  loading = false;
  submitted = false;
  token: string;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private passwordService: PasswordService,
    private route: ActivatedRoute,
    private alertService: AlertService
  ) {
  }

  get field() {
    return this.changePasswordForm.controls;
  }

  ngOnInit() {
    this.changePasswordForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      confirmationToken: [this.token],
      newPassword: ['', [Validators.required, Validators.minLength(6)]],
      confirmPassword: ['', Validators.required]
    }, {
      validator: MustMatch('newPassword', 'confirmPassword')
    });

    this.route.paramMap.subscribe(value => {
      if (value.has('token')) {
        this.token = value.get('token');
      }

    });
  }

  onSubmit() {
    this.submitted = true;
    this.alertService.clear();
    if (this.changePasswordForm.invalid) {
      return;
    }
    this.changePasswordForm.get('confirmationToken').setValue(this.token);
    this.loading = true;
    this.passwordService.change(this.changePasswordForm.value)
      .pipe(first())
      .subscribe(
        data => {
          this.alertService.success('Hasło zostało zmienione', true);
          this.router.navigate(['/login']);
        },
        error => {
          this.alertService.error(error);
          this.loading = false;
        });
  }
}
