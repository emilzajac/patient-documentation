import {Component, OnInit} from '@angular/core';
import {User} from "../model/user";
import {AuthenticationService} from "../service/authentication.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  currentUser: User;

  constructor(
    private authenticationService: AuthenticationService,
    private router: Router
  ) {
    this.currentUser = this.authenticationService.currentUserValue;
  }

  ngOnInit() {
  }

  documentationListEvent() {
    this.router.navigate([`/documentation/list/${this.currentUser.username}`])
  }
}
