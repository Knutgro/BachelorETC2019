import { Injectable } from '@angular/core';
import {Vehicle} from '../_models/vehicle';
import { Listing } from '../_models/listing';
import {HttpClient} from '@angular/common/http';
import {Globals} from '../globals';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ListingService {
  constructor(
    private http: HttpClient,
    private globals: Globals
  ) { }

  save(listing: any): Observable<any> {
    let result: Observable<Object>;
    if (listing['href']) {
      result = this.http.put(listing.href, listing);
    } else {
      result = this.http.post(`${this.globals.apiUrl}/listings`, listing);
    }
    return result;
  }

  getAll() {
    return this.http.get<Listing[]>(`${this.globals.apiUrl}/listings`);
  }

  getById(id: number) {
    return this.http.get(`${this.globals.apiUrl}/listings/${id}`);
  }
}
