import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map} from "rxjs/operators";
import {BehaviorSubject, Observable} from "rxjs";
import {User} from "../model/user";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  private currentUserSubject: BehaviorSubject<User>;
  public currentUser: Observable<User>;

  constructor(private http: HttpClient,
              private router: Router) {
    this.currentUserSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem('currentUser')));
    this.currentUser = this.currentUserSubject.asObservable();
  }

  public get currentUserValue(): User {
    return this.currentUserSubject.value;
  }

  public login(username: String, password: String): any {
    return this.http.get<User>(`/api/login`,
      {headers: {authorization: AuthenticationService.createBasicAuthToken(username, password)}})
      .pipe(map(user => {
        localStorage.setItem('currentUser', JSON.stringify(user));
        this.currentUserSubject.next(user);
        return user;
      }));
  }

  public static getLoggedUser(): User {
    return JSON.parse(localStorage.getItem('currentUser'));
  }

  public static isUserLoggedIn(): boolean {
    let user = localStorage.getItem('currentUser');
    return user !== null;
  }

  public logout() {
    this.http.post<any>('/logout', {}).subscribe(response => {
        this.removeCurrentUserFromStorageAndRedirect();
      },
      error => {
        this.removeCurrentUserFromStorageAndRedirect();
      }
    );
  }

  private static createBasicAuthToken(username: String, password: String): string {
    return 'Basic ' + window.btoa(username + ":" + password)
  }

  private removeCurrentUserFromStorageAndRedirect() {
    console.log("done");
    localStorage.removeItem('currentUser');
    this.currentUserSubject.next(null);
    this.router.navigate(['/login']);
  }

}
