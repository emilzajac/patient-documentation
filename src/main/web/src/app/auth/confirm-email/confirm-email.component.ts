import { Component, OnInit }                  from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router }                             from '@angular/router';
import { AlertService }                       from '../../service/alert.service';
import { first }                              from 'rxjs/operators';
import { PasswordService }                    from '../password.service';

@Component({
  selector: 'app-confirm-email',
  templateUrl: './confirm-email.component.html',
  styleUrls: ['./confirm-email.component.scss']
})
export class ConfirmEmailComponent implements OnInit {
  confirmationEmailForm: FormGroup;
  loading = false;
  submitted = false;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private passwordService: PasswordService,
    private alertService: AlertService
  ) {
  }

  get field() {
    return this.confirmationEmailForm.controls;
  }

  ngOnInit() {
    this.confirmationEmailForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
    });
  }

  onSubmit() {
    this.submitted = true;
    this.alertService.clear();
    if (this.confirmationEmailForm.invalid) {
      return;
    }
    this.loading = true;
    this.passwordService.sendToken(this.confirmationEmailForm.value.email)
      .pipe(first())
      .subscribe(
        () => {
          this.alertService.success('Na podany adres e-mail został wysłany link potwierdzający adres email', true);
          this.router.navigate(['/login']);
        },
        error => {
          this.alertService.error(error);
          this.loading = false;
        });
  }
}
