import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Vehicle} from "../_models/vehicle";
import {Globals} from "../globals";
import {Observable} from "rxjs";
import {first} from 'rxjs/operators';
import {log} from 'util';
import {Listing} from '../_models/listing';
import {VehicleDetailComponent} from '../vehicle-detail/vehicle-detail.component';

@Injectable({
  providedIn: 'root'
})
export class VehicleService {

  public vehicleData: Vehicle[];
  selectedFilterCount = 0;
  filteredVehiclesList = [];
  searchOption = [];
  searchFilter = [];
  constructor(private http: HttpClient, private globals: Globals) { }

  save(vehicle: any, exist: boolean): Observable<any> {
    let result: Observable<Object>;
    if (exist) {
      result = this.http.put(`${this.globals.apiUrl}/vehicles`, vehicle);
    } else {
      result = this.http.post(`${this.globals.apiUrl}/vehicles`, vehicle);
    }
    return result;
  }

  getAll() {
    return this.http.get<Vehicle[]>(`${this.globals.apiUrl}/vehicles`);
  }

  getByCompanyId(id: number) {
    return this.http.get<Vehicle[]>(`${this.globals.apiUrl}/companyVehicles/${id}`);
  }

  getById(id: number) {
    return this.http.get(`${this.globals.apiUrl}/vehicles/${id}`);
  }

  update(vehicle: Vehicle) {
    return this.http.put(`${this.globals.apiUrl}/listings`, vehicle);
  }

  delete(id: number) {
    return this.http.delete(`${this.globals.apiUrl}/vehicles/${id}`);
  }

  filteredListOptions() {
    const vehicles = this.vehicleData;
    this.filteredVehiclesList.length = 0;
    for (const vehicle of vehicles) {
      for (const filter of this.searchFilter) {
          if (filter.name === vehicle.exteriorColorMain && filter.selected && !this.filteredVehiclesList.includes(vehicle)) {
            this.filteredVehiclesList.push(vehicle);
          }
      }
      for (const options of this.searchOption) {
        //if (options.name === vehicle.name) {
        if (vehicle.nick.includes(options.name) && !this.filteredVehiclesList.includes(vehicle)) {
          this.filteredVehiclesList.push(vehicle);
        }
      }
    }
    return this.filteredVehiclesList;
  }
}
