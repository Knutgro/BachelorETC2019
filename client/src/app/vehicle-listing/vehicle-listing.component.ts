import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {VehicleService} from '../_services/vehicle.service';
import {AlertService} from '../_services/alert.service';
import {Title} from '@angular/platform-browser';
import {ListingService} from '../_services/listing.service';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-vehicle-listing',
  templateUrl: './vehicle-listing.component.html',
  styleUrls: ['./vehicle-listing.component.scss']
})
export class VehicleListingComponent implements OnInit {

  listing: any = {};
  vehicle: any = {}
  sub: Subscription;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private listingService: ListingService,
    private alertService: AlertService,
    private titleService: Title
  ) { }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      const id = params['id'];
      if (id) {
        this.listingService.getById(id).subscribe((listing: any) => {
          if (listing) {
            this.listing = listing;
            this.listing.href = listing.href;
            listing.vehicle.typeData.dinosaur = listing.vehicle.typeData.fuel !== 'elektrisk';
            this.vehicle = this.listing.vehicle;
            this.titleService.setTitle(this.listing.title);
            console.log(listing);
          } else {
            // console.log(`listing with id '${id}' not found, returning to list`);
            this.alertService.error(`listing with id '${id}' not found, returning to list`);
            //this.gotoList();
          }
        });
      }
    });
  }

  gotoList() {
    this.router.navigate(['/vehicle-list']);
  }

}
