import { Component, OnInit } from '@angular/core';
import {Vehicle} from '../_models/vehicle';
import {VehicleService} from '../_services/vehicle.service';

@Component({
  selector: 'app-search-result',
  templateUrl: './search-result.component.html',
  styleUrls: ['./search-result.component.scss']
})
export class SearchResultComponent implements OnInit {
  vehicles: Vehicle[];

  constructor(
    private vehicleService: VehicleService
  ) { }

  ngOnInit() {
    this.vehicleService.getAll().subscribe(vehicles => {
      this.vehicles = vehicles;
      this.vehicleService.vehicleData = vehicles;
    });
  }


  onSelectedOption(e) {
    this.getFilteredExpenseList();
  }

  getFilteredExpenseList() {
    if (this.vehicleService.searchOption.length > 0) {
      this.vehicles = this.vehicleService.filteredListOptions();
    } else {
      this.vehicles = this.vehicleService.vehicleData;
    }

    console.log(this.vehicles);
  }
}
