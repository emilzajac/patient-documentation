import { Component, OnInit }      from '@angular/core';
import { ActivatedRoute, Router } from "@angular/router";
import { AuthenticationService }  from "../service/authentication.service";
import { User }                   from "../model/user";

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent implements OnInit {

  private isLoggedIn: boolean = false;
  public loggedUser: User;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private authenticationService: AuthenticationService) {
  }

  ngOnInit() {
    this.isLoggedIn = this.authenticationService.isUserLoggedIn();
    this.loggedUser = this.getLoggedUser();
  }

  getLoggedUser(): User {
    return this.authenticationService.getLoggedUser();
  }

  handleLogout() {
    this.authenticationService.logout();
  }

  documentationListEvent() {
    return `/documentation/list/${this.loggedUser.username}`;
  }
}
