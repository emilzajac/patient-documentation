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
  users = [];

  constructor(
    private authenticationService: AuthenticationService,
    private router: Router
    // private userService: UserService
  ) {
    this.currentUser = this.authenticationService.currentUserValue;
  }

  ngOnInit() {
    // this.loadAllUsers();
  }

  //
  // deleteUser(id: number) {
  //   this.userService.delete(id)
  //     .pipe(first())
  //     .subscribe(() => this.loadAllUsers());
  // }
  //
  // private loadAllUsers() {
  //   this.userService.getAll()
  //     .pipe(first())
  //     .subscribe(users => this.users = users);
  // }
}
