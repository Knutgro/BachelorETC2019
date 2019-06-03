import {ChangeDetectorRef, Component, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {Listing} from '../_models/listing';
import {ListingService} from '../_services/listing.service';
import {MatPaginator, MatSort, MatTableDataSource} from '@angular/material';
import {AlertService} from '../_services/alert.service';
import {first} from 'rxjs/operators';
import {ImagesService} from '../_services/images.service';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-listing-search',
  templateUrl: './listing-search.component.html',
  styleUrls: ['./listing-search.component.scss']
})
export class ListingSearchComponent implements OnInit, OnDestroy {
  listings: Listing[];
  dataListings: MatTableDataSource<Listing>;
  obs: Observable<any>;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(    private listingsService: ListingService,
                  private alertService: AlertService,
                  private imagesService: ImagesService,
                  private changeDetectorRef: ChangeDetectorRef) {
    this.listingsService = listingsService;
    this.alertService = alertService;
    this.imagesService = imagesService;
    this.changeDetectorRef = changeDetectorRef;
  }

  ngOnInit() {
    this.loadAllListings();
  }

  ngOnDestroy() {
    if (this.dataListings) {
      this.dataListings.disconnect();
    }
  }

  private loadAllListings() {
    this.listingsService.getAll().pipe(first()).subscribe(listings => {
      this.listings = listings;
      this.listingsService.listingData = listings;
      for (let i = 0; i < listings.length; i++) {
        this.listings[i].vehicle.thumbnail =
          this.imagesService.convertImage(listings[i].vehicle.vehicleImages[0].image,
            listings[i].vehicle.vehicleImages[0].imageContentType);
      }
      this.dataListings = new MatTableDataSource(listings);
      this.changeDetectorRef.detectChanges();
      this.dataListings.paginator = this.paginator;
      this.obs = this.dataListings.connect();
    }, error1 => {
      this.alertService.error(error1);
    });
  }

  applyFilter(filterValue: string) {
    this.dataListings.filter = filterValue.trim().toLowerCase();

    if (this.dataListings.paginator) {
      this.dataListings.paginator.firstPage();
    }
  }

}
