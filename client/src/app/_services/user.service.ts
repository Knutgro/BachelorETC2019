import { Injectable } from '@angular/core';
import {Globals} from '../globals';
import {User} from '../_models/user';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient, private globals: Globals) { }



  getAll() {
    return this.http.get<User[]>(`${this.globals.apiUrl}/users`);
  }

  getById(id: number) {
    return this.http.get(`${this.globals.apiUrl}/users/${id}`);
  }

  register(user: any, exist: boolean): Observable<any> {
    let result: Observable<Object>;
    if (exist) {
      result =  this.http.put(`${this.globals.apiUrl}/auth/signup`, user);
    } else {
      result =  this.http.post(`${this.globals.apiUrl}/auth/signup`, user);
    }
    return result;
  }

  update(user: User) {
    return this.http.put(`${this.globals.apiUrl}/users/${user.id}`, user);
  }

  delete(id: number) {
    return this.http.delete(`${this.globals.apiUrl}/auth/user/${id}`);
  }
}
