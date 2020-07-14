import { Component, OnInit }     from '@angular/core';
import { User }                  from "../model/user";
import { AuthenticationService } from '../auth/authentication.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  currentUser: User;

  constructor(private authenticationService: AuthenticationService,) {
    this.currentUser = this.authenticationService.currentUserValue;
  }

  ngOnInit() {
  }

  documentationListEvent() {
    return `/documentation/list/${this.currentUser.username}`;
  }

}
