import {Injectable} from '@angular/core';
import {Patient, PatientInterface} from "../model/patient-interface";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {PatientListInterface} from "../patient/patient-list/patient-list-interface";

@Injectable({
  providedIn: 'root'
})
export class PatientService {

  constructor(private http: HttpClient) {
  }

  add(patient: PatientInterface) {
    return this.http.post(`api/patients`, patient);
  }

  getByDoctorUsername(doctorUsername: String): Observable<PatientListInterface[]> {
    return this.http.get<PatientListInterface[]>(`api/patients/doctor/${doctorUsername}`);
  }

  getByPesel(pesel: String): Observable<PatientInterface> {
    return this.http.get<PatientInterface>(`api/patients/${pesel}`);
  }

  update(patient: Patient): Observable<PatientInterface> {
    return this.http.put<PatientInterface>(`api/patients`, JSON.stringify(patient));
  }

  delete(id: number) {
    return this.http.delete<PatientInterface>(`api/patients/${id}`);
  }
}
