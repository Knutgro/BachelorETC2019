import { Injectable } from '@angular/core';
import {User} from '../_models/user';
import {BehaviorSubject, config, Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {map} from 'rxjs/operators';
import {Globals} from '../globals';
import { JwtHelperService, JwtModule } from '@auth0/angular-jwt';


@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  private currentUserSubject: BehaviorSubject<User>;
  public currentUser: Observable<any>;

  constructor(
    private http: HttpClient,
    private globals: Globals,
    public jwtHelper: JwtHelperService
  ) {
    this.currentUserSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem('currentUser')));
    this.currentUser = this.currentUserSubject.asObservable();
  }

  public get currentUserValue(): User {
    return this.currentUserSubject.value;
  }

  public isAuthenticated(): boolean {
    const token = JSON.parse(localStorage.getItem('currentUser'));
    return !this.jwtHelper.isTokenExpired(token.token);
  }

  login(username: string, password: string) {
    return this.http.post<any>(`${this.globals.apiUrl}/auth/signin`, { username, password })
      .pipe(map(token => {
        // login successful if there's a jwt token in the response
        if (token) {
          // console.log(username);
          // store user details and jwt token in local storage to keep user logged in between page refreshes
          localStorage.setItem('currentUser', JSON.stringify(token));
          this.currentUserSubject.next(token);
        }

        return token;
      }));
  }

  logout() {
    // remove user from local storage to log user out
    localStorage.removeItem('currentUser');
    this.currentUserSubject.next(null);
  }
}
