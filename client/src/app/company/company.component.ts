import {Component, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {User} from '../_models/user';
import {Subscription} from 'rxjs';
import {Company} from '../_models/company';
import {MatPaginator, MatTableDataSource} from '@angular/material';
import {first} from 'rxjs/operators';
import {Vehicle} from '../_models/vehicle';
import {VehicleService} from '../_services/vehicle.service';
import {UserService} from '../_services/user.service';
import {CompanyService} from '../_services/company.service';
import {Listing} from '../_models/listing';
import {ListingService} from '../_services/listing.service';
import {AuthenticationService} from '../_services/authentication.service';
import {AlertService} from '../_services/alert.service';

@Component({
  selector: 'app-company',
  templateUrl: './company.component.html',
  styleUrls: ['./company.component.scss']
})
export class CompanyComponent implements OnInit, OnDestroy {

  currentUser: User;
  currentUserSubscription: Subscription;
  columnsVehicles: string[] = ['id', 'name', 'regnr', 'update', 'delete'];
  columnsListings: string[] = ['id', 'name', 'regnr', 'created', 'expires', 'update', 'delete'];
  users: User[] = [];
  companies: Company[] = [];
  vehicles: Vehicle[] = [];
  listings: Listing[] = [];
  dataUsers: MatTableDataSource<User>;
  dataCompanies: MatTableDataSource<Company>;
  dataVehicles: MatTableDataSource<Vehicle>;
  dataListings: MatTableDataSource<Listing>;

  @ViewChild('paginator') paginator: MatPaginator;
  @ViewChild('paginator2') paginator2: MatPaginator;

  applyFilterVehicles(filterValue: string) {
    this.dataVehicles.filter = filterValue.trim().toLowerCase();
  }

  applyFilterListings(filterValue: string) {
    this.dataListings.filter = filterValue.trim().toLowerCase();
  }
  constructor(
    private authenticationService: AuthenticationService,
    private userService: UserService,
    private companyService: CompanyService,
    private vehicleService: VehicleService,
    private listingsService: ListingService,
    private alertService: AlertService,
  ) {
    this.companyService = companyService;
    this.userService = userService;
    this.vehicleService = vehicleService;
    this.listingsService = listingsService;
    this.alertService = alertService;
    this.currentUserSubscription = this.authenticationService.currentUser.subscribe(user => {
      this.currentUser = user;
    });

  }

  ngOnInit() {
    this.loadAllListings();
    this.loadAllVehicles();
  }
  ngOnDestroy(): void {
  }

  private loadAllVehicles() {
    this.vehicleService.getByCompanyId(this.currentUser.user.company.id).pipe(first()).subscribe(vehicles => {
      this.vehicles = vehicles;
      console.log(vehicles);
      this.dataVehicles = new MatTableDataSource(vehicles);
      this.dataVehicles.paginator = this.paginator;
    });
  }

  private loadAllListings() {
    this.listingsService.getByCompany(this.currentUser.user.company.id).pipe(first()).subscribe(listings => {
      this.listings = listings;
      this.dataListings = new MatTableDataSource(listings);
      this.dataListings.paginator = this.paginator2;
    });
  }

  private deleteListing(id: number) {
    this.listingsService.delete(id).pipe(first()).subscribe(() => {
      this.loadAllListings();
      this.alertService.success('Annonse slettet');
    }, error => {
      this.alertService.error(error);
    });
  }

  private deleteVehicle(id: number) {
    this.vehicleService.delete(id).pipe(first()).subscribe(() => {
      this.loadAllVehicles();
      this.loadAllListings();
      this.alertService.success('Kjøretøy slettet');
    }, error => {
      this.alertService.error(error);
    });
  }

  _setDataSource(indexNumber) {
    setTimeout(() => {
      switch (indexNumber) {
        case 0:
          !this.dataVehicles.paginator ? this.dataVehicles.paginator = this.paginator : null;
          break;
        case 1:
          !this.dataListings.paginator ? this.dataListings.paginator = this.paginator2 : null;
      }
    });
  }

}
