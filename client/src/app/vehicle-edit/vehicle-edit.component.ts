import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from "rxjs";
import {ActivatedRoute, Router} from "@angular/router";
import {VehicleService} from "../_services/vehicle.service";
import {NgForm} from "@angular/forms";
import {AlertService} from '../_services/alert.service';

@Component({
  selector: 'app-vehicle-edit',
  templateUrl: './vehicle-edit.component.html',
  styleUrls: ['./vehicle-edit.component.css']
})
export class VehicleEditComponent implements OnInit, OnDestroy {
  vehicle: any = {};

  sub: Subscription;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private vehicleService: VehicleService,
              private alertService: AlertService
              ) {
  }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      const id = params['id'];
      if (id) {
        this.vehicleService.getById(id).subscribe((vehicle: any) => {
          if (vehicle) {
            this.vehicle = vehicle;
            console.log(this.vehicle);
            this.vehicle.href = vehicle._links.self.href;
            console.log('Vehicle found');
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

  remove(href) {
    this.vehicleService.remove(href).subscribe(result => {
      this.gotoList();
    }, error => this.alertService.error(error));
  }
}
