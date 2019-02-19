import { Component, OnInit } from '@angular/core';
import {VehicleService} from "../_services/vehicle.service";
import {PageEvent} from '@angular/material';

@Component({
  selector: 'app-vehicle-list',
  templateUrl: './vehicle-list.component.html',
  styleUrls: ['./vehicle-list.component.css']
})
export class VehicleListComponent implements OnInit {
  vehicles: Array<any>;
/*
  length = 100;
  pageSize = 10;
  pageSizeOptions: number[] = [5, 10, 25, 100];

  // MatPaginator Output
  pageEvent: PageEvent;

  setPageSizeOptions(setPageSizeOptionsInput: string) {
    this.pageSizeOptions = setPageSizeOptionsInput.split(',').map(str => +str);
  }
*/


  constructor( private vehicleService: VehicleService ) { }

  ngOnInit() {
    this.vehicleService.getAll().subscribe(
      data => {
        this.vehicles = data;
      });
  }

}
