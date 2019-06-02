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
    for (let i = 0; i < tokenPayload.role.length; i++) {
      if (tokenPayload.role[i].authority === expectedRole) {

        asExpected = true;

      }
    }
    if (
      !this.authenticationService.isAuthenticated() ||
      !asExpected
    ) {

      this.router.navigate(['']);
      return false;
    }
    return true;
  }
}
