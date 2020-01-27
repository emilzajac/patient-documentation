import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {PatientService} from "../../service/patient.service";
import {DocumentationService} from "../../service/documentation.service";
import {MatPaginator, MatTableDataSource} from "@angular/material";
import {DocumentationListInterface} from "../documentation-list-interface";
import {Documentation} from "../../model/documentation-interface";
import {ActivatedRoute} from "@angular/router";
import * as moment from 'moment';
import {FormBuilder, FormGroup} from "@angular/forms";

declare var $: any;

@Component({
  selector: 'app-documentation-patient-list',
  templateUrl: './documentation-patient-list.component.html',
  styleUrls: ['./documentation-patient-list.component.scss']
})
export class DocumentationPatientListComponent implements OnInit, AfterViewInit {

  displayedColumns: string[] = ['ID', 'Wywiad', 'Rozpoznanie', 'Badanie_przedmiotowe', 'Zalecenia', 'Leki', 'Data_Wpisu', 'action'];
  dataSource: MatTableDataSource<DocumentationListInterface> = new MatTableDataSource();
  documentations: DocumentationListInterface[];
  selectedDocumentation: Documentation = new Documentation();
  patientId: string;
  errorMessage: string;
  infoMessage: string;
  documentationChangeForm: FormGroup;

  @ViewChild(MatPaginator, {static: false}) paginator: MatPaginator;

  constructor(private patientService: PatientService,
              private documentationService: DocumentationService,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder) {
  }

  ngOnInit() {
    this.route.paramMap.subscribe(value => {
      if (value.has('patientId')) {
        this.patientId = value.get('patientId');
      }
    });
    this.getAllPatientDocumentation();

    this.documentationChangeForm = this.formBuilder.group({
      creationDate: ['']
    });
  }

  ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  private getAllPatientDocumentation() {
    this.documentationService.getAllPatientDocumentation(this.patientId, null)
      .subscribe((documentations: DocumentationListInterface[]) => {
        this.documentations = documentations;
        this.dataSource.data = documentations;
      });
  }

  editDocumentationRequest(documentation: Documentation) {
    this.selectedDocumentation = documentation;
    $('#documentationEditModal').modal('show');
  }

  editDocumentation() {
    this.selectedDocumentation.creationDate = this.documentationChangeForm.value.creationDate;
    this.documentationService.update(this.selectedDocumentation).subscribe(data => {
      const itemIndex = this.documentations.findIndex(item => item.id == this.selectedDocumentation.id);
      this.documentations[itemIndex] = this.selectedDocumentation;
      this.dataSource = new MatTableDataSource(this.documentations);
      this.infoMessage = 'Dane dokumentacji zostały zmienione.';
      $('#documentationEditModal').modal('hide');
    }, err => {
      if (err.status === 409) {
        this.errorMessage = 'Wystąpił nieoczekiwany błąd.';
      }
    });
  }

  deleteDocumentationRequest(documentation: Documentation) {
    this.selectedDocumentation = documentation;
    $('#deleteDocumentationModal').modal('show');
  }

  deleteDocumentation() {
    this.documentationService.delete(this.selectedDocumentation.id).subscribe(data => {
      const itemIndex = this.documentations.findIndex(item => item.id === this.selectedDocumentation.id);
      if (itemIndex !== -1) {
        this.documentations.splice(itemIndex, 1);
      }
      this.dataSource = new MatTableDataSource(this.documentations);
      this.infoMessage = 'Dokumentacja została usunięta.';
      $('#deleteDocumentationModal').modal('hide');
    }, err => {
      this.errorMessage = 'Wystąpił nieoczekiwany błąd.';
    });
  }

  onFormatDate(creationDate: Date) {
    return moment(creationDate).locale('pl').format('LLL');
  }

  getDate(creationDate: Date) {
    return moment(creationDate).format('DD.MM.YYYY, HH:mm:ss');
  }
}
