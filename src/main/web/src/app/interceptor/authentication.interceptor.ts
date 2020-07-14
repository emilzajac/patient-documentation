import { Injectable }                                                        from '@angular/core';
import { HttpEvent, HttpHandler, HttpHeaders, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Observable }                                                        from 'rxjs';
import { User }                                                              from '../model/user';
import { AuthenticationService }                                             from '../auth/authentication.service';

@Injectable()
export class AuthenticationInterceptor implements HttpInterceptor {

  constructor(private authenticationService: AuthenticationService) {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let currentUser: User = this.authenticationService.currentUserValue;
    if (currentUser) {
      const authRequest = request.clone({
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
          'Authorization': currentUser.tokenType + ' ' + currentUser.token
        })
      });
      return next.handle(authRequest);
    } else {
      return next.handle(request);
    }
  }

}
