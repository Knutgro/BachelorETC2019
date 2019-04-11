import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from "rxjs";
import {ActivatedRoute, Router} from "@angular/router";
import {VehicleService} from "../_services/vehicle.service";
import {NgForm} from "@angular/forms";
import {AlertService} from '../_services/alert.service';
import {Title} from '@angular/platform-browser';

@Component({
  selector: 'app-vehicle-edit',
  templateUrl: './vehicle-edit.component.html',
  styleUrls: ['./vehicle-edit.component.css']
})
export class VehicleEditComponent implements OnInit, OnDestroy {
  vehicle: any = {};
  ourFile: File[];

  sub: Subscription;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private vehicleService: VehicleService,
              private alertService: AlertService,
              private titleService: Title
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
    }
  }

  upload() {
    console.log('Send to server');
  }

}
