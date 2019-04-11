import { Component, OnInit } from '@angular/core';
import {User} from '../_models/user';
import {Title} from '@angular/platform-browser';
import {Router} from '@angular/router';
import {AuthenticationService} from '../_services/authentication.service';
import {SidenavService} from '../_services/sidenav.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {

  currentUser: User;
  constructor(
    title: Title,
    private router: Router,
    private authenticationService: AuthenticationService,
    private sidenavService: SidenavService
  ) {}

  ngOnInit() {
    this.authenticationService.currentUser.subscribe(x => this.currentUser = x);
  }

}
