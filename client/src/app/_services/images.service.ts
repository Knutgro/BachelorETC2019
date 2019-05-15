import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Globals} from '../globals';
import {Observable} from 'rxjs';
import {VehicleImage} from '../_models/vehicleImage';

@Injectable({
  providedIn: 'root'
})
export class ImagesService {
  imageToShow: any;


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

  convertImage(dataImage: string, dataContentType: string) {
    const base64String = JSON.stringify(dataImage);
    const startString = 'dataimage/' + dataContentType + ';base64';
    const base64Data = base64String.substring(startString.length, base64String.length - 1);
    return 'data:image/' + dataContentType + ';base64,' + base64Data;
  }




}
