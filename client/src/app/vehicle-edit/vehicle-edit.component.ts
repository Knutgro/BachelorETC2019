import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from 'rxjs';
import {ActivatedRoute, Router} from '@angular/router';
import {VehicleService} from '../_services/vehicle.service';
import {NgForm} from '@angular/forms';
import {AlertService} from '../_services/alert.service';
import {Title} from '@angular/platform-browser';
import {VehicleImage} from '../_models/vehicleImage';
import {ImagesService} from '../_services/images.service';
import {first} from 'rxjs/operators';
import {User} from '../_models/user';
import {AuthenticationService} from '../_services/authentication.service';
import {Vehicle} from '../_models/vehicle';

@Component({
  selector: 'app-vehicle-edit',
  templateUrl: './vehicle-edit.component.html',
  styleUrls: ['./vehicle-edit.component.css']
})
export class VehicleEditComponent implements OnInit, OnDestroy {
  vehicle: any = {};
  currentUser: User;
  ourFile: File[];
  vImage: VehicleImage;
  vImages: VehicleImage[];
  sub: Subscription;
  imageArr: string[] = [];
  url: any;
  exist: boolean;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private authenticationService: AuthenticationService,
              private vehicleService: VehicleService,
              private alertService: AlertService,
              private titleService: Title,
              private imageService: ImagesService
  ) {
    this.sub = this.authenticationService.currentUser.subscribe(user => {
      this.currentUser = user;
    });
  }

  ngOnInit() {
    this.exist = false;
    this.vehicle.company = this.currentUser.user.company;
    this.sub = this.route.params.subscribe(params => {
      const id = params['id'];
      console.log(id);
      if (id) {
        this.vehicleService.getById(id).subscribe((vehicle: any) => {
          if (vehicle) {
            this.vehicle = vehicle;
            console.log(vehicle);
            this.titleService.setTitle(`Rediger ${vehicle.name}`);
            this.exist = true;
          } else {
            // console.log(`vehicle with id '${id}' not found, returning to list`);
            this.alertService.error(`vehicle with id '${id}' not found, returning to list`);
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
    this.router.navigate(['/vehicle-list']);
  }

  save(form: NgForm) {
    this.vehicleService.save(form, this.exist).subscribe(result => {
      this.gotoList();
    }, error => this.alertService.error(error));
  }

  remove(id) {
    this.vehicleService.delete(id).subscribe(result => {
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
        vehicle_id: this.vehicle.id
      };
      this.imageService.postImage(this.vImage).pipe(first())
        .subscribe(
          data => {
            this.alertService.success('Image posted');
          }, error => {
            this.alertService.error(error);
          }
        );
    }
    // let vehicleAlbum = VehicleImage[];
    /*for (let i = 0; i < this.imageArr.length; i++) {
      // let fileExt = this.imageArr[i].split('.').pop();
      // let formData = new FormData();
      // console.log(fileExt);
      // formData.append('image', this.imageArr[i]);
      // formData.append('imageContentType', fileExt);
      // formData.append('vehicle_id', JSON.stringify(this.vehicle.id));
      const imgString = JSON.stringify(this.imageArr[i]);
      const fileExt = imgString.substring('{data:image/'.length, imgString.indexOf(';base64'));
      this.vImage = {
        image: this.imageArr[i],
        imageContentType: fileExt,
        vehicle_id: this.vehicle.id
      };
      console.log(this.vImage.image);
      this.imageService.postImage(this.vImage/*formData, this.imageArr[i]).pipe(first())
        .subscribe(
          data => {
            this.alertService.success('Image posted');
          }, error => {
            this.alertService.error(error);
          }
        );
    }*/
  }

}
