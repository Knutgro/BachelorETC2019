import { Component, OnInit } from '@angular/core';
import {Subscription} from 'rxjs';
import {ActivatedRoute, Router} from '@angular/router';
import {VehicleService} from '../_services/vehicle.service';
import {NgForm} from '@angular/forms';
import {AlertService} from '../_services/alert.service';
import {Title} from '@angular/platform-browser';
import {VehicleImage} from '../_models/vehicleImage';
import {ImagesService} from '../_services/images.service';
import {first} from 'rxjs/operators';
import {ListingService} from '../_services/listing.service';
import {Vehicle} from '../_models/vehicle';
import {User} from '../_models/user';
import {AuthenticationService} from '../_services/authentication.service';
@Component({
  selector: 'app-listing-edit',
  templateUrl: './listing-edit.component.html',
  styleUrls: ['./listing-edit.component.scss']
})

export class ListingEditComponent implements OnInit {
  listing: any = {};
  vehicles: Vehicle[] = [];
  vehicleModel: Vehicle;
  ourFile: File[];
  vImage: VehicleImage;
  id: number;
  companyID: number;
  currentUserSubscription: Subscription;
  currentUser: User;
  vImages: VehicleImage[];
  sub: Subscription;
  imageArr: string[] = [];
  url: any;
  exist: boolean;
  dateNow: any;
  dateNowPlusThreeMonths: any;

  constructor(private route: ActivatedRoute,
              private authenticationService: AuthenticationService,
              private router: Router,
              private listingService: ListingService,
              private alertService: AlertService,
              private titleService: Title,
              private imageService: ImagesService,
              private vehicleService: VehicleService
  ) {
    this.currentUserSubscription = this.authenticationService.currentUser.subscribe(user => {
      this.currentUser = user;
    });
  }

  ngOnInit() {
    this.exist = false;
    this.dateNow = new Date().getTime();
    this.dateNowPlusThreeMonths = new Date(this.dateNow + (1000 * 60 * 60 * 24 * 90)).getTime();
    this.companyID = this.currentUser.user.company.id;
    this.sub = this.route.params.subscribe(params => {
      this.id = params['id'];
      console.log(this.id);
      if (!this.id) {
        this.vehicleService.getByCompanyId(this.currentUser.user.company.id).subscribe((vehicle: any) => {
          if (vehicle) {
            for (let i = 0; i < vehicle.length; i++) {
              this.vehicles.push(vehicle[i]);
            }
          }
        }, error => {
          this.alertService.error(error);
        });
      }
      console.log(this.vehicles);
      if (this.id) {
        this.listingService.getById(this.id).subscribe((listing: any) => {
          if (listing) {
            this.listing = listing;
            this.listing.company = listing.company;
            this.vehicleModel = listing.vehicle;
            this.listing.id = listing.id;
            console.log(listing);
            this.titleService.setTitle(`Rediger ${listing.name}`);
            this.exist = true;
          } else {
            // console.log(`listing with id '${id}' not found, returning to list`);
            this.alertService.error(`listing with id '${this.id}' not found, returning to list`);
            this.gotoList();
          }
        });
      }
    });
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }

  gotoList() {
    this.router.navigate(['/']);
  }

  save(form: NgForm) {
    console.log(form);
    console.log(this.companyID);
    this.listingService.save(form, this.exist).subscribe(result => {
      this.gotoList();
    }, error => this.alertService.error(error));
  }

  remove(id) {
    this.listingService.delete(id).subscribe(result => {
      this.gotoList();
    }, error => this.alertService.error(error));
  }

  openInput() {
    document.getElementById('fileInput').click();
  }

  fileChange(event: any) {
    if (event.target.files) {
      this.ourFile = event.target.files;
      const reader = new FileReader();
      reader.onload = (eventR: any) => {
        this.url = eventR.target.result;
        this.imageArr.push(this.url);
        console.log(this.url);
      };
      reader.readAsDataURL(event.target.files[0]);
    }
  }

  upload() {
    console.log('Send to server');
    for (let i = 0; i < this.imageArr.length; i++) {
      const imgString = JSON.stringify(this.imageArr[i]);
      const fileExt = imgString.substring('{data:image/'.length, imgString.indexOf(';base64'));
      this.vImage = {
        image: this.imageArr[i],
        imageContentType: fileExt,
        vehicle_id: this.vehicleModel.id
      };
      console.log(this.vehicleModel);
      this.imageService.postImage(this.vImage).pipe(first())
        .subscribe(
          data => {
            this.alertService.success('Image posted');
          }, error => {
            this.alertService.error(error);
          }
        );
    }
  }

}
