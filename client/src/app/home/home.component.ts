import {Component, OnDestroy, OnInit} from '@angular/core';
import {first} from "rxjs/operators";
import {UserService} from "../_services/user.service";
import {AuthenticationService} from "../_services/authentication.service";
import {User} from "../_models/user";
import {Subscription} from "rxjs";
import {Title} from '@angular/platform-browser';


// TODO fiks 401-error unauthorized eternal refresh hvis man ikke er logget inn

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  currentUser: User;
  currentUserSubscription: Subscription;
  users: User[] = [];

  constructor(
    private authenticationService: AuthenticationService,
    private titleService: Title
  ) {
    this.currentUserSubscription = this.authenticationService.currentUser.subscribe(user => {
      this.currentUser = user;
    });
  }

  ngOnInit() {
    this.titleService.setTitle('ETC Salg');
  }
}
