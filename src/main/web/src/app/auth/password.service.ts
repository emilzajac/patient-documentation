import { Injectable }              from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable }              from 'rxjs';
import { ChangePasswordForm }      from './change-password-form';

@Injectable({
  providedIn: 'root'
})
export class PasswordService {

  constructor(private http: HttpClient) {
  }

  sendToken(email: string): Observable<void> {
    return this.http.get<void>(`api/password/reset-token/${email}`);
  }

  change(changePasswordForm: ChangePasswordForm): Observable<void> {
    return this.http.put<void>(`api/password/reset`, JSON.stringify(changePasswordForm), {headers: new HttpHeaders({'Content-Type': 'application/json'})});
  }

}
