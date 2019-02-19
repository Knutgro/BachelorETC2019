import {Component, Injectable, OnInit} from '@angular/core';
import {User} from '../_models/user';
import {Router} from '@angular/router';
import {AuthenticationService} from '../_services/authentication.service';
import {Title} from '@angular/platform-browser';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  currentUser: User;

  constructor(
    title: Title,
    private router: Router,
    private authenticationService: AuthenticationService
  ) {}

  ngOnInit() {
    this.authenticationService.currentUser.subscribe(x => this.currentUser = x);
  }

  logout() {
    this.authenticationService.logout();
    this.router.navigate(['/login']);
  }

}
