import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Vehicle} from "../_models/vehicle";
import {Globals} from "../globals";
import {Observable} from "rxjs";
import {first} from 'rxjs/operators';
import {log} from 'util';

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

  save(vehicle: any): Observable<any> {
    let result: Observable<Object>;
    if (vehicle['href']) {
      result = this.http.put(vehicle.href, vehicle);
    } else {
      result = this.http.post(`${this.globals.apiUrl}/vehicles`, vehicle);
    }
    return result;
  }

  getAll() {
    return this.http.get<Vehicle[]>(`${this.globals.apiUrl}/vehicles`);
  }

  getById(id: number) {
    return this.http.get(`${this.globals.apiUrl}/vehicles/${id}`);
  }

  remove(id: number) {
    return this.http.delete(`${this.globals.apiUrl}/vehicles/${id}`);
  }

  filteredListOptions() {
    const vehicles = this.vehicleData;
    this.filteredVehiclesList.length = 0;
    for (const vehicle of vehicles) {
      for (const filter of this.searchFilter) { // TODO fiks det her filterrotet: Fjernes ikke når man avhuker filteret
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
