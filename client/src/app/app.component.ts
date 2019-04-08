import {Component, OnInit, ViewChild} from '@angular/core';
import {AuthenticationService} from "./_services/authentication.service";
import {Router} from "@angular/router";
import {User} from "./_models/user";
import {MatSidenav} from '@angular/material';
import {SidenavService} from './_services/sidenav.service';
import {Title} from '@angular/platform-browser';

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
    private router: Router,
    private titleService: Title
  ) {}

  ngOnInit(): void {
    this.authenticationService.currentUser.subscribe(x => this.currentUser = x);
    console.log(this.currentUser);
    this.sidenavService.setSideNav(this.sideNav);
  }

  public setTitle( newTitle: string) {
    this.titleService.setTitle( newTitle );
  }

  toggleSidenav() {
    this.sidenavService.toggle();
  }

  logout() {
    this.authenticationService.logout();
    this.router.navigate(['/login']);
  }
}


