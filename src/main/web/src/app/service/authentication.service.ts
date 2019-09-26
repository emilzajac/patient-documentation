import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  public username: String;
  public password: String;

  private BASE_PATH: string = 'http://localhost:8080';
  private USER_NAME_SESSION_ATTRIBUTE_NAME: string = 'authenticatedUser';

  constructor(private http: HttpClient) {
  }

  public authenticationService(username: String, password: String): any {
    return this.http.get(this.BASE_PATH + `/api/basicauth`,
      {headers: {authorization: AuthenticationService.createBasicAuthToken(username, password)}}).pipe(
      map(() => {
        this.username = username;
        this.password = password;
        this.registerSuccessfulLogin(username, password);
      }));
  }

  public logout() {
    sessionStorage.removeItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME);
    this.username = null;
    this.password = null;
  }

  public isUserLoggedIn() {
    let user = sessionStorage.getItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME);
    return user !== null;

  }

  private static createBasicAuthToken(username: String, password: String) {
    return 'Basic ' + window.btoa(username + ":" + password)
  }

  private registerSuccessfulLogin(username, password) {
    sessionStorage.setItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME, username)
  }

}
