import {Component, OnInit, ViewChild} from '@angular/core';
import {AuthenticationService} from "./_services/authentication.service";
import {Router} from "@angular/router";
import {User} from "./_models/user";
import {MatSidenav} from '@angular/material';
import {SidenavService} from './_services/sidenav.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  @ViewChild(MatSidenav) sideNav: MatSidenav;
  currentUser: User;
  constructor(
    private authenticationService: AuthenticationService,
    private sidenavService: SidenavService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.authenticationService.currentUser.subscribe(x => this.currentUser = x);
    this.sidenavService.setSideNav(this.sideNav);
  }

  toggleSidenav() {
    this.sidenavService.toggle();
  }

  logout() {
    this.authenticationService.logout();
    this.router.navigate(['/login']);
  }
}


