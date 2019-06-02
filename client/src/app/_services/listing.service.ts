import { Injectable } from '@angular/core';
import {Vehicle} from '../_models/vehicle';
import { Listing } from '../_models/listing';
import {HttpClient} from '@angular/common/http';
import {Globals} from '../globals';
import {Observable} from 'rxjs';
import {VehicleService} from './vehicle.service';

@Injectable({
  providedIn: 'root'
})
export class ListingService {
  public listingData: Listing[];
  selectedFilterCount = 0;
  filteredListingsList = [];
  searchOption = [];
  searchFilter = [];

  constructor(
    private http: HttpClient,
    private globals: Globals,
    private vehicleService: VehicleService,
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

  getByCompany(id: number) {
    return this.http.get<Listing[]>(`${this.globals.apiUrl}/companyListings/${id}`);

  }

  filteredListOptions() {
    const listings = this.listingData;
    this.filteredListingsList.length = 0;
    for (const listing of listings) {
      const vehicle = listing.vehicle;
      for (const filter of this.searchFilter) {
        if (filter.name === vehicle.exteriorColorMain && filter.selected && !this.filteredListingsList.includes(listing)) {
          this.filteredListingsList.push(listing);
        }
      }
      for (const options of this.searchOption) {
        //if (options.name === vehicle.name) {
        if (vehicle.typeData.model.name.includes(options.name) && !this.filteredListingsList.includes(listing)) {
          this.filteredListingsList.push(listing);
        }
      }
    }
    return this.filteredListingsList;
  }
}
