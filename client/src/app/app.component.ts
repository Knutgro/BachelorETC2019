import {Component, OnInit, ViewChild} from '@angular/core';
import {AuthenticationService} from './_services/authentication.service';
import {Router} from '@angular/router';
import {User} from './_models/user';
import {MatSidenav} from '@angular/material';
import {SidenavService} from './_services/sidenav.service';
import {Title} from '@angular/platform-browser';
import {Role} from './_models/role';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  @ViewChild('sidenav') public sideNav: MatSidenav;
  opened: boolean;
  currentUser: User;
  role: Role;
  isAdmin: boolean;

  constructor(
    private authenticationService: AuthenticationService,
    private sidenavService: SidenavService,
    private router: Router,
    private titleService: Title
  ) {}

  ngOnInit(): void {
    this.role = {
      authority: ['']
    };
    this.authenticationService.currentUser.subscribe(x => this.currentUser = x.user);

    if (this.currentUser) {
      for (let i = 0; i < this.currentUser.authorities.length; i++) {
        this.role.authority[i] = this.currentUser.authorities[i];
      }
    }

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

      for (let i = 0; i < this.role.authority.length; i++) {
        if (JSON.stringify(this.role.authority[0]).includes('ROLE_ADMIN')) {
          this.isAdmin = true;
        }
      }
    }
  }
}


