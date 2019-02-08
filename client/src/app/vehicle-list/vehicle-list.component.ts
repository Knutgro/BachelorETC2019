import { Component, OnInit } from '@angular/core';
import {VehicleService} from "../_services/vehicle.service";

@Component({
  selector: 'app-vehicle-list',
  templateUrl: './vehicle-list.component.html',
  styleUrls: ['./vehicle-list.component.css']
})
export class VehicleListComponent implements OnInit {
  vehicles: Array<any>;

  constructor( private vehicleService: VehicleService ) { }

  ngOnInit() {
    this.vehicleService.getAll().subscribe(
      data => {
        this.vehicles = data;
      });
  }

}
