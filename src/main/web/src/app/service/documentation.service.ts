import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Documentation, DocumentationFormInterface} from "../model/documentation-interface";
import {Observable} from "rxjs";
import {DocumentationListInterface} from "../documentation/documentation-list-interface";

@Injectable({
  providedIn: 'root'
})
export class DocumentationService {

  constructor(private http: HttpClient) {
  }

  add(documentation: DocumentationFormInterface): Observable<Documentation> {
    return this.http.post<Documentation>(`api/documentations`, documentation);
  }

  get(doctorUserName: String) {
    return this.http.get(`api/documentations/` + doctorUserName);
  }

  getAllPatientDocumentation(id: string, pesel: string): Observable<DocumentationListInterface[]> {
    const httpParams = new HttpParams();
    return this.http.get<DocumentationListInterface[]>(`api/documentations/patient`,
      {params: httpParams.append('id', id).append('pesel', pesel)});
  }

  update(documentation: DocumentationFormInterface): Observable<DocumentationFormInterface> {
    return this.http.put<DocumentationFormInterface>(`api/documentations`, JSON.stringify(documentation));
  }

  delete(id: number) {
    return this.http.delete<DocumentationFormInterface>(`api/documentations/${id}`);
  }

}
