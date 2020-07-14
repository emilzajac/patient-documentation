import { Injectable }                  from '@angular/core';
import { HttpClient }                  from "@angular/common/http";
import { map }                         from "rxjs/operators";
import { BehaviorSubject, Observable } from "rxjs";
import { User }                        from "../model/user";
import { Router }                      from "@angular/router";
import { SessionStorageService }       from '../utils/session-storage.service';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private readonly LOGGED_USER = 'user';

  public currentUser: Observable<User>;
  private currentUserSubject: BehaviorSubject<User>;

  constructor(private http: HttpClient,
              private storageService: SessionStorageService,
              private router: Router) {
    this.currentUserSubject = new BehaviorSubject<User>(this.getUserFromStorage());
    this.currentUser = this.currentUserSubject.asObservable();
  }

  get currentUserValue(): User {
    return this.currentUserSubject.value;
  }

  login(username: string, password: string): any {
    return this.http.post<any>('/api/login', {username, password})
      .pipe(
        map(userData => {
          this.storageService.set(this.LOGGED_USER, userData);
          this.currentUserSubject.next(userData);
          return userData;
        })
      );
  }

  logout(): void {
    this.http.post<any>('/api/logout', {}).subscribe(() => {
        this.removeCurrentUserFromStorageAndRedirect();
      },
      () => {
        this.removeCurrentUserFromStorageAndRedirect();
      }
    );
  }

  getLoggedUser(): User {
    return this.getUserFromStorage();
  }

  isUserLoggedIn(): boolean {
    return this.getUserFromStorage() !== null;
  }

  private removeCurrentUserFromStorageAndRedirect(): void {
    sessionStorage.clear();
    this.currentUserSubject.next(null);
    this.router.navigate(['/login']);
  }

  private getUserFromStorage(): User {
    return this.storageService.get(this.LOGGED_USER);
  }

}
