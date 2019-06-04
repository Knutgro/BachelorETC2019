import {Component, ElementRef, EventEmitter, OnInit, Output, ViewChild} from '@angular/core';
import {Vehicle} from '../_models/vehicle';
import {VehicleService} from '../_services/vehicle.service';
import {AlertService} from '../_services/alert.service';
import {ImagesService} from '../_services/images.service';
import {ListingService} from '../_services/listing.service';
import {Listing} from '../_models/listing';
import {DomSanitizer} from '@angular/platform-browser';

@Component({
  selector: 'app-search-result',
  templateUrl: './search-result.component.html',
  styleUrls: ['./search-result.component.scss']
})
export class SearchResultComponent implements OnInit {
  vehicles: Vehicle[];
  listings: Listing[];

  constructor(
    private vehicleService: VehicleService,
    private listingService: ListingService,
    private alertService: AlertService,
    private imageService: ImagesService,
    private sanitizer: DomSanitizer
  ) { }

  ngOnInit() {
    this.listingService.getAll().subscribe(listings => {
      this.listings = listings;
      this.listingService.listingData = listings;
      for (let i = 0; i < listings.length; i++) {
        this.listings[i].vehicle.thumbnail =
          this.imageService.convertImage(listings[i].vehicle.vehicleImages[0].image,
            listings[i].vehicle.vehicleImages[0].imageContentType);
      }
      /* Old image method
      for (let i = 0; i < this.listings.length; i++) {
        this.imageService.getImages(this.listings[i].id.toString()).subscribe(images => {
          this.listings[i].thumbnail = images[0].url;
        }, error2 => {
          this.alertService.error(error2);
        });
      }*/
    }, error1 => {
      this.alertService.error(error1);
    });
    /*
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
    });*/
  }

  onSelectedOption(e) {
    this.getFilteredExpenseList();
  }

  onSelectedFilter(e) {
    this.getFilteredExpenseList();
  }

  getFilteredExpenseList() {
    if (this.listingService.searchOption.length > 0 || this.listingService.searchFilter.length > 0) {
      this.listings = this.listingService.filteredListOptions();
    } else {
      this.listings = this.listingService.listingData;
    }
  }

  /* Old method for getting vehicles not listings
  getFilteredExpenseList() {
    if (this.vehicleService.searchOption.length > 0 || this.vehicleService.searchFilter.length > 0) {
      this.vehicles = this.vehicleService.filteredListOptions();
    } else {
      this.vehicles = this.vehicleService.vehicleData;
    }
  }
  */

  // Filter


}
