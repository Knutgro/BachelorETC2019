import { Injectable } from '@angular/core';
import {Company} from '../_models/company';
import {HttpClient} from '@angular/common/http';
import {Globals} from '../globals';
import {Region} from '../_models/region';

@Injectable({
  providedIn: 'root'
})
export class RegionService {

  constructor(private http: HttpClient, private globals: Globals) { }

  getAll() {
    return this.http.get<Region[]>(`${this.globals.apiUrl}/regions`);
  }
}
