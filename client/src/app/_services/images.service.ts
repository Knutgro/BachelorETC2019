import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Globals} from '../globals';
import {Observable} from 'rxjs';
import {VehicleImage} from '../_models/vehicleImage';

@Injectable({
  providedIn: 'root'
})
export class ImagesService {

  constructor(
    private http: HttpClient,
    private globals: Globals
  ) { }

  getImages(vehicleID: string): Observable<any> {
    return this.http.get(`${this.globals.apiUrl}/vehicle-albums/${vehicleID}`);
  }

  postImage(image: VehicleImage) {
    return this.http.post(`${this.globals.apiUrl}/vehicle-albums`, image);
  }

}
