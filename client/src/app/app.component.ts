import {Component, OnInit, ViewChild} from '@angular/core';
import {AuthenticationService} from './_services/authentication.service';
import {Router} from '@angular/router';
import {User} from './_models/user';
import {MatSidenav} from '@angular/material';
import {SidenavService} from './_services/sidenav.service';
import {Title} from '@angular/platform-browser';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  @ViewChild('sidenav') public sideNav: MatSidenav;
  opened: boolean;
  currentUser: User;
  role: string[] = [];
  isAdmin: boolean;

  constructor(
    private authenticationService: AuthenticationService,
    private sidenavService: SidenavService,
    private router: Router,
    private titleService: Title
  ) {}

  ngOnInit(): void {
    this.authenticationService.currentUser.subscribe(x => this.currentUser = x.user);
    console.log(this.currentUser);
    if (this.currentUser) {
      for (let i = 0; i < this.currentUser.authorities.length; i++) {
        this.role[i] = this.currentUser.authorities[i];
      }
    }
    console.log(this.role);
    console.log(this.sideNav);
    this.isAdmin = false;
    this.isAdministrator();
    this.sidenavService.setSideNav(this.sideNav);
  }

  public setTitle( newTitle: string) {
    this.titleService.setTitle( newTitle );
  }

  toggleSidenav() {
    this.sidenavService.toggle();
  }

  logout() {
    this.isAdmin = false;
    this.authenticationService.logout();
    this.router.navigate(['/login']);
  }

  isAdministrator() {
    if (this.currentUser) {
      console.log()
      for (let i = 0; i < this.role.length; i++) {
        console.log(this.role[i]);
        if (this.role[i].authority === 'ROLE_ADMIN') {
          console.log('true');
          this.isAdmin = true;
        }
      }
    }
  }
}


