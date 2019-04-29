import { Injectable } from '@angular/core';
import {
  Router,
  CanActivate,
  ActivatedRouteSnapshot
} from '@angular/router';
import { AuthenticationService } from './authentication.service';
import decode from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class RoleGuardService implements CanActivate {

  constructor(
    public authenticationService: AuthenticationService,
    public router: Router
  ) { }

  canActivate(route: ActivatedRouteSnapshot): boolean {
    const expectedRole = route.data.expectedRole;
    let asExpected = false;
    const token = JSON.parse(localStorage.getItem('currentUser')).token;
    const tokenPayload = decode(token);
    console.log(tokenPayload.role);
    for (let i = 0; i < tokenPayload.role.length; i++) {
      if (tokenPayload.role[i].authority === expectedRole) {
        console.log(tokenPayload.role[i].authority);
        asExpected = true;
        console.log('Role as expected');
      }
    }
    if (
      !this.authenticationService.isAuthenticated() ||
      !expectedRole
    ) {
      console.log('Access denied!');
      this.router.navigate(['']);
      return false;
    }
    return true;
  }
}
