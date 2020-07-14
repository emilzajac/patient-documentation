import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { PatientService }                              from "../../service/patient.service";
import { Patient }                                     from "../../model/patient-interface";
import { PatientListInterface }                        from "./patient-list-interface";
import { MatPaginator, MatTableDataSource }            from '@angular/material';
import { Router }                                      from "@angular/router";
import { AuthenticationService }                       from '../../auth/authentication.service';

declare var $: any;

@Component({
  selector: 'app-patient-list',
  templateUrl: './patient-list.component.html',
  styleUrls: ['./patient-list.component.scss']
})
export class PatientListComponent implements OnInit, AfterViewInit {

  displayedColumns: string[] = ['ID', 'Imie', 'Nazwisko', 'pesel', 'Data_Urodzenia', 'Plec', 'Miasto', 'Kod_pocztowy', 'Ulica', 'Numer_domu', 'action'];
  dataSource: MatTableDataSource<PatientListInterface> = new MatTableDataSource();
  patients: PatientListInterface[];
  selectedPatient: Patient = new Patient();
  errorMessage: string;
  infoMessage: string;

  @ViewChild(MatPaginator, {static: false}) paginator: MatPaginator;

  constructor(private patientService: PatientService,
              private authenticationService: AuthenticationService,
              private router: Router) {
  }

  ngOnInit() {
    this.getAllPatientsOfDoctor();
  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  onTransformGenderToDisplay(gender: string) {
    if (gender === 'MALE') {
      return 'MĘŻCZYZNA';
    } else {
      return 'KOBIETA';
    }
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }

  getAllPatientsOfDoctor() {
    this.patientService.getByDoctorUsername(this.authenticationService.getLoggedUser().username)
      .subscribe((patients: PatientListInterface[]) => {
        this.patients = patients;
        this.dataSource.data = patients;
      });
  }

  editPatientRequest(patient: Patient) {
    this.selectedPatient = patient;
    $('#patientModal').modal('show');
  }

  editPatient() {
    this.patientService.update(this.selectedPatient).subscribe(data => {
      const itemIndex = this.patients.findIndex(item => item.id == this.selectedPatient.id);
      this.patients[itemIndex] = this.selectedPatient;
      this.dataSource = new MatTableDataSource(this.patients);
      this.infoMessage = 'Dane pacjenta zostały zmienione.';
      $('#patientModal').modal('hide');
    }, err => {
      if (err.status === 409) {
        this.errorMessage = 'Wystąpił nieoczekiwany błąd.';
      }
    });
  }

  deletePatientRequest(patient: Patient) {
    this.selectedPatient = patient;
    $('#deleteModal').modal('show');
  }

  deletePatient() {
    this.patientService.delete(this.selectedPatient.id).subscribe(data => {
      const itemIndex = this.patients.findIndex(item => item.id === this.selectedPatient.id);
      if (itemIndex !== -1) {
        this.patients.splice(itemIndex, 1);
      }
      this.dataSource = new MatTableDataSource(this.patients);
      this.infoMessage = 'Pacjent został usunięty.';
      $('#deleteModal').modal('hide');
    }, () => {
      this.errorMessage = 'Wystąpił nieoczekiwany błąd.';
    });
  }

  documentationPatientRequest(patient: PatientListInterface) {
    this.router.navigate([`/documentation/list/patient/${patient.id}`])
  }

  addDocumentation(pesel: string) {
    this.router.navigate([`/documentation/add/${pesel}`])
  }
}
