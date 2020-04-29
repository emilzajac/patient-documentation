import { Component, OnInit }                  from '@angular/core';
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Router }                             from "@angular/router";
import { AlertService }                       from "../../service/alert.service";
import { first }                              from "rxjs/operators";
import { PatientService }                     from "../../service/patient.service";
import { BsLocaleService }                    from 'ngx-bootstrap/datepicker';

@Component({
  selector: 'app-add-patient',
  templateUrl: './patient-add.component.html',
  styleUrls: ['./patient-add.component.scss']
})
export class PatientAddComponent implements OnInit {
  patientAddForm: FormGroup;
  loading = false;
  submitted = false;

  constructor(private formBuilder: FormBuilder,
              private router: Router,
              private patientService: PatientService,
              private alertService: AlertService,
              private bsLocaleService: BsLocaleService) {
    bsLocaleService.use('pl');
  }

  ngOnInit() {
    this.patientAddForm = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      pesel: ['', Validators.required],
      dateOfBirth: ['', Validators.required],
      gender: ['MALE', Validators.required],
      city: ['', Validators.required],
      street: ['', Validators.required],
      houseNumber: ['', Validators.required],
      postCode: ['', Validators.required],
    });
  }

  get field() {
    return this.patientAddForm.controls;
  }

  onSubmit() {
    this.submitted = true;

    // reset alerts on submit
    this.alertService.clear();

    // stop here if form is invalid
    if (this.patientAddForm.invalid) {
      return;
    }

    this.loading = true;
    this.patientService.add(this.patientAddForm.value)
      .pipe(first())
      .subscribe(
        data => {
          this.alertService.success('Pacjent został dodany do listy', true);
          this.router.navigate(['/patient/list']);
        },
        error => {
          this.alertService.error(error);
          this.loading = false;
        });
  }
}
