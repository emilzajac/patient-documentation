import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {AuthenticationService} from "../../service/authentication.service";
import {AlertService} from "../../service/alert.service";
import {first} from "rxjs/operators";
import {DocumentationService} from "../../service/documentation.service";
import {PatientInterface} from "../../model/patient-interface";
import {PatientService} from "../../service/patient.service";

@Component({
  selector: 'app-documentation-add',
  templateUrl: './documentation-add.component.html',
  styleUrls: ['./documentation-add.component.scss']
})
export class DocumentationAddComponent implements OnInit {
  documentationAddForm: FormGroup;
  loading = false;
  submitted = false;
  patient: PatientInterface;

  constructor(private formBuilder: FormBuilder,
              private router: Router,
              private documentationService: DocumentationService,
              private patientService: PatientService,
              private authenticationService: AuthenticationService,
              private alertService: AlertService) {
  }

  ngOnInit() {
    this.documentationAddForm = this.formBuilder.group({
      pesel: ['', Validators.required],
      interview: [''],
      physicalExamination: [''],
      diagnosisOfTheDisease: [''],
      recommendations: [''],
      medicines: [''],
      patient: [''],
      user: [this.authenticationService.currentUserValue]
    });
  }

  checkPesel(pesel: string) {
    const control = this.documentationAddForm.controls['pesel'];
    if (pesel.length === 11) {
      this.patientService.getByPesel(pesel)
        .subscribe((patient) => {
          this.patient = patient;
          if (!this.patient) {
            control.setErrors({notExists: true});
          } else {
            control.setErrors(null);
          }
        });
    } else {
      control.setErrors({notProperLength: true});
    }
  }

  onSubmit() {
    this.submitted = true;

    this.documentationAddForm.value.patient = this.patient;

    this.alertService.clear();

    if (this.documentationAddForm.invalid) {
      return;
    }

    this.loading = true;
    this.documentationService.add(this.documentationAddForm.value)
      .pipe(first())
      .subscribe(
        data => {
          this.alertService.success('Dokumentacja została dodana do listy Pacjenta', true);
          this.router.navigate([`/documentation/list/${data.patient.id}`]);
        },
        error => {
          this.alertService.error(error);
          this.loading = false;
        });
  }

  get field() {
    return this.documentationAddForm.controls;
  }

}
