import { Component, OnInit }                  from '@angular/core';
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { ActivatedRoute, Router } from "@angular/router";
import { AlertService }           from "../../service/alert.service";
import { first }                  from "rxjs/operators";
import { DocumentationService }   from "../../service/documentation.service";
import { PatientInterface }       from "../../model/patient-interface";
import { PatientService }         from "../../service/patient.service";
import { BsLocaleService }        from "ngx-bootstrap/datepicker";
import * as moment                from 'moment';
import { DateFormatConstant }     from '../../constans/date-format.constants';

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
  pesel: string;

  constructor(private formBuilder: FormBuilder,
              private router: Router,
              private documentationService: DocumentationService,
              private patientService: PatientService,
              private alertService: AlertService,
              private bsLocaleService: BsLocaleService,
              private route: ActivatedRoute) {
    bsLocaleService.use('pl');
  }

  ngOnInit() {
    this.route.paramMap.subscribe(value => {
      if (value.has('pesel')) {
        this.pesel = value.get('pesel');
        this.patientService.getByPesel(this.pesel)
          .subscribe((patient) => {
            this.patient = patient;
          })
      }
    });

    this.documentationAddForm = this.formBuilder.group({
      patientPesel: [this.pesel, Validators.required],
      interview: [''],
      physicalExamination: [''],
      diagnosisOfTheDisease: [''],
      recommendations: [''],
      creationDate: [''],
      medicines: ['', Validators.required],
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
          }
        });
    } else {
      control.setErrors({notProperLength: true});
    }
  }

  onSubmit() {
    this.submitted = true;

    this.alertService.clear();

    if (this.documentationAddForm.invalid) {
      return;
    }

    let creationDateForm = this.documentationAddForm.value.creationDate;
    this.documentationAddForm.value.creationDate = moment(creationDateForm).format(DateFormatConstant.DATE_TIME_REQUEST_FORMAT);

    this.loading = true;
    this.documentationService.add(this.documentationAddForm.value)
      .pipe(first())
      .subscribe(
        () => {
          this.router.navigate([`/documentation/list/patient/${this.patient.id}`]);
          this.alertService.success('Dokumentacja została dodana do listy Pacjenta', true);
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
