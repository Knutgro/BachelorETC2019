import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Globals} from '../globals';
import {User} from '../_models/user';
import {Company} from '../_models/company';

@Injectable({
  providedIn: 'root'
})
export class CompanyService {

  constructor(private http: HttpClient, private globals: Globals) { }

  getAll() {
    return this.http.get<Company[]>(`${this.globals.apiUrl}/companies`);
  }

  getById(id: number) {
    return this.http.get(`${this.globals.apiUrl}/companies/${id}`);
  }

  register(company: Company) {
    return this.http.post(`${this.globals.apiUrl}/companies`, company);
  }

  update(company: Company) {
    return this.http.put(`${this.globals.apiUrl}/companies`, company);
  }

  delete(id: number) {
    return this.http.delete(`${this.globals.apiUrl}/companies/${id}`);
  }
}
