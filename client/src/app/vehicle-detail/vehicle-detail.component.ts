import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Subscription} from 'rxjs';
import {VehicleService} from '../_services/vehicle.service';
import {AlertService} from '../_services/alert.service';
import {Title} from '@angular/platform-browser';

@Component({
  selector: 'app-vehicle-detail',
  templateUrl: './vehicle-detail.component.html',
  styleUrls: ['./vehicle-detail.component.scss']
})
export class VehicleDetailComponent implements OnInit {
  vehicle: any = {};
  sub: Subscription;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private vehicleService: VehicleService,
    private alertService: AlertService,
    private titleService: Title
  ) { }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      const id = params['id'];
      if (id) {
        this.vehicleService.getById(id).subscribe((vehicle: any) => {
          if (vehicle) {
            this.vehicle = vehicle;
            this.vehicle.href = vehicle.href;
            this.titleService.setTitle(this.vehicle.name);
          } else {
            // console.log(`vehicle with id '${id}' not found, returning to list`);
            this.alertService.error(`vehicle with id '${id}' not found, returning to list`);
            this.gotoList();
          }
        });
      }
    });
  }

  gotoList() {
    this.router.navigate(['/vehicle-list']);
  }

}
