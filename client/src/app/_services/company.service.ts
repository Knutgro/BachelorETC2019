import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Globals} from '../globals';
import {User} from '../_models/user';
import {Company} from '../_models/company';
import {Observable} from 'rxjs';

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

  register(company: any): Observable<any> {
    let result: Observable<Object>;
    if (company['href']) {
      result = this.http.put(company.href, company);
    } else {
      result = this.http.post(`${this.globals.apiUrl}/companies`, company);
    }
    return result;
  }

  update(company: Company) {
    return this.http.put(`${this.globals.apiUrl}/companies`, company);
  }

  delete(id: number) {
    return this.http.delete(`${this.globals.apiUrl}/companies/${id}`);
  }
}
