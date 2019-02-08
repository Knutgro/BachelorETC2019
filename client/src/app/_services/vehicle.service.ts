import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Vehicle} from "../_models/vehicle";
import {Globals} from "../globals";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class VehicleService {

  constructor(private http: HttpClient, private globals: Globals) { }

  save(vehicle: any): Observable<any> {
    let result: Observable<Object>;
    if (vehicle['href']) {
      result = this.http.put(vehicle.href, vehicle);
    } else {
      result = this.http.post(`${this.globals.apiUrl}/vehicles/add`, vehicle);
    }
    return result;
  }

  getAll() {
    return this.http.get<Vehicle[]>(`${this.globals.apiUrl}/vehicles`);
  }

  getById(id: number) {
    return this.http.get(`${this.globals.apiUrl}/vehicles/${id}`);
  }

  remove(href: string) {
    return this.http.delete(href);
  }
}
