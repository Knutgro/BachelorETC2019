import {Component, Injectable, OnInit} from '@angular/core';
import {User} from '../_models/user';
import {Router} from '@angular/router';
import {AuthenticationService} from '../_services/authentication.service';
import {Title} from '@angular/platform-browser';
import {SidenavService} from '../_services/sidenav.service';

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
    private authenticationService: AuthenticationService,
    private sidenavService: SidenavService
  ) {}

  ngOnInit() {
    this.authenticationService.currentUser.subscribe(x => this.currentUser = x);
  }

  toggleSidenav() {
    this.sidenavService.toggle();
  }

  logout() {
    this.authenticationService.logout();
    //this.router.navigate(['/']);
    this.redirectTo('/');
  }

  redirectTo(uri:string){
    this.router.navigateByUrl('/', {skipLocationChange: true}).then(()=>
      this.router.navigate([uri]));}

}
