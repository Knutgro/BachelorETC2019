import {Component, ElementRef, EventEmitter, OnInit, Output, ViewChild} from '@angular/core';
import {Vehicle} from '../_models/vehicle';
import {VehicleService} from '../_services/vehicle.service';
import {AlertService} from '../_services/alert.service';
import {ImagesService} from '../_services/images.service';

@Component({
  selector: 'app-search-result',
  templateUrl: './search-result.component.html',
  styleUrls: ['./search-result.component.scss']
})
export class SearchResultComponent implements OnInit {
  vehicles: Vehicle[];

  constructor(
    private vehicleService: VehicleService,
    private alertService: AlertService,
    private imageService: ImagesService
  ) { }

  ngOnInit() {
    this.vehicleService.getAll().subscribe(vehicles => {
      this.vehicles = vehicles;
      this.vehicleService.vehicleData = vehicles;
      for (let i = 0; i < this.vehicles.length; i++) {
        this.imageService.getImages(this.vehicles[i].id.toString()).subscribe(images => {
          this.vehicles[i].thumbnail = images[0].url;
        }, error2 => {
          this.alertService.error(error2);
        });
      }
    }, error1 => {
      this.alertService.error(error1);
    });
  }

  onSelectedOption(e) {
    this.getFilteredExpenseList();
  }

  onSelectedFilter(e) {
    this.getFilteredExpenseList();
  }


  getFilteredExpenseList() {
    if (this.vehicleService.searchOption.length > 0 || this.vehicleService.searchFilter.length > 0) {
      this.vehicles = this.vehicleService.filteredListOptions();
    } else {
      this.vehicles = this.vehicleService.vehicleData;
    }
  }

  // Filter


}
