import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {UserMT} from "../model/user";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  constructor(private http: HttpClient) {
  }

  register(user: UserMT) {
    return this.http.post(`api/users/register`, user);
  }

}
