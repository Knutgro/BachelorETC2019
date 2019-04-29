import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from "rxjs";
import {ActivatedRoute, Router} from "@angular/router";
import {VehicleService} from "../_services/vehicle.service";
import {NgForm} from "@angular/forms";
import {AlertService} from '../_services/alert.service';
import {Title} from '@angular/platform-browser';
import {VehicleImage} from '../_models/vehicleImage';
import {ImagesService} from '../_services/images.service';

@Component({
  selector: 'app-vehicle-edit',
  templateUrl: './vehicle-edit.component.html',
  styleUrls: ['./vehicle-edit.component.css']
})
export class VehicleEditComponent implements OnInit, OnDestroy {
  vehicle: any = {};
  ourFile: File[];
  vImage: VehicleImage;
  sub: Subscription;
  imageArr: any[] = [];
  url: any;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private vehicleService: VehicleService,
              private alertService: AlertService,
              private titleService: Title,
              private imageService: ImagesService
              ) {
  }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      const id = params['id'];
      if (id) {
        this.vehicleService.getById(id).subscribe((vehicle: any) => {
          if (vehicle) {
            this.vehicle = vehicle;
            this.titleService.setTitle(`Rediger ${vehicle.name}`);
            this.vehicle.href = vehicle._links.self.href;
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
    this.vehicleService.save(form).subscribe(result => {
      this.gotoList();
    }, error => this.alertService.error(error));
  }

  remove(id) {
    this.vehicleService.remove(id).subscribe(result => {
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
      };
      reader.readAsDataURL(event.target.files[0]);
      this.imageArr.push(event.target.files[0]);
      console.log(event.target.files[0].name);
    }
  }

  upload() { // TODO
    console.log('Send to server');
    // let vehicleAlbum = VehicleImage[];
    for (let i = 0; i < this.imageArr.length; i++) {
      let fileExt = this.imageArr[i].name.split('.').pop();
      console.log(fileExt);
      this.vImage = {
        image: new Blob([this.imageArr[i]]),
        imageContentType: fileExt,
        vehicle_id: this.vehicle.id
      };
      this.imageService.postImage(this.vImage);
    }
  }

}
