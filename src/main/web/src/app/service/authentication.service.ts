import { Injectable }                  from '@angular/core';
import { HttpClient }                  from "@angular/common/http";
import { map }                         from "rxjs/operators";
import { BehaviorSubject, Observable } from "rxjs";
import { User }                        from "../model/user";
import { Router }                      from "@angular/router";
import { StorageService }              from '../utils/storage.service';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  public currentUser: Observable<User>;
  private readonly USER_KEY = 'user';
  private currentUserSubject: BehaviorSubject<User>;
  private userSecureMap: Map<string, string>;

  constructor(private http: HttpClient,
              private storage: StorageService,
              private router: Router) {
    this.currentUserSubject = new BehaviorSubject<User>(JSON.parse(this.getUserFromStorage()));
    this.currentUser = this.currentUserSubject.asObservable();
    this.createUserSecureSessionMap();
  }

  public get currentUserValue(): User {
    return this.currentUserSubject.value;
  }

  public login(username: string, password: string): any {
    return this.http.post<any>('/api/login', {username, password})
      .pipe(
        map(userData => {
          sessionStorage.setItem(this.userSecureMap.get(this.USER_KEY), this.storage.encryptData(JSON.stringify(userData)));
          this.currentUserSubject.next(userData);
          return userData;
        })
      );
  }

  public logout() {
    this.http.post<any>('/api/logout', {}).subscribe(() => {
        this.removeCurrentUserFromStorageAndRedirect();
      },
      () => {
        this.removeCurrentUserFromStorageAndRedirect();
      }
    );
  }

  public getLoggedUser(): User {
    return JSON.parse(this.getUserFromStorage());
  }

  public isUserLoggedIn(): boolean {
    return this.getUserFromStorage() !== null;
  }

  private removeCurrentUserFromStorageAndRedirect() {
    sessionStorage.removeItem(this.userSecureMap.get(this.USER_KEY));
    this.currentUserSubject.next(null);
    this.router.navigate(['/login']);
  }

  private getUserFromStorage() {
    const key = this.userSecureMap;
    if (key) {
      return this.storage.decryptData(sessionStorage.getItem(this.userSecureMap.get(this.USER_KEY)));
    } else {
      return null;
    }
  }

  private createUserSecureSessionMap(): void {
    this.userSecureMap = new Map<string, string>();
    this.userSecureMap.set(this.USER_KEY, this.storage.encryptData(this.USER_KEY));
  }

}
