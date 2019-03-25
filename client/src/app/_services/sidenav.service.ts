import { Injectable } from '@angular/core';
import {MatSidenav} from '@angular/material';

@Injectable({
  providedIn: 'root'
})
export class SidenavService {
  private sideNav: MatSidenav;

  setSideNav(nav: MatSidenav) {
    this.sideNav = nav;
  }

  toggle(){
    this.sideNav.toggle();
  }

  constructor() { }
}
