import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, NgForm, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {AuthenticationService} from '../_services/authentication.service';
import {UserService} from '../_services/user.service';
import {AlertService} from '../_services/alert.service';
import {Title} from '@angular/platform-browser';
import {first} from 'rxjs/operators';
import {CompanyService} from '../_services/company.service';
import {Company} from '../_models/company';
import {Subscription} from 'rxjs';
import {RegionService} from '../_services/region.service';
import {Region} from '../_models/region';
import {element} from 'protractor';

@Component({
  selector: 'app-register-company',
  templateUrl: './register-company.component.html',
  styleUrls: ['./register-company.component.scss']
})
export class RegisterCompanyComponent implements OnInit {
    sub: Subscription;
  registerForm: FormGroup;
  company: any = {};
  exist: boolean;

  tmp: Region[] = [];
  regions: any[] = [];

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private route: ActivatedRoute,
    private authenticationService: AuthenticationService,
    private userService: UserService,
    private alertService: AlertService,
    private companyService: CompanyService,
    private regionService: RegionService,
    private titleService: Title
  ) { }

  ngOnInit() {/*
    this.registerForm = this.formBuilder.group({
      name: ['', Validators.required],
      orgNr: ['', Validators.required],
      phoneNumber: ['', Validators.required],
      region: ['', Validators.required]
    });*/
    this.exist = false;
    this.regionService.getAll().subscribe( regions => {
      /*this.tmp.push(region);
      console.log(this.tmp);
      for (let i = 0; i < this.tmp.length; i++) {
        this.regions.push(this.tmp[i]);
      }*/
      for (const region of regions) {
        this.regions.push(region);
      }
    });
    console.log(this.regions);
    this.sub = this.route.params.subscribe(params => {
      const id = params['id'];
      console.log(id);
      if (id) {
        this.companyService.getById(id).subscribe((company: any) => {
          if (company) {
            this.company = company;
            console.log(company);
            this.titleService.setTitle(`Rediger ${company.name}`);
            this.exist = true;
          } else {
            // console.log(`company with id '${id}' not found, returning to list`);
            this.alertService.error(`company with id '${id}' not found, returning to home`);
            this.gotoList();
          }
        });
      }
    });
  }

  gotoList() {
    this.router.navigate(['/']);
  }

  onSubmit(form: NgForm) {
    //form.controls['role'].setValue(['']);
    console.log(form);
    this.companyService.save(form, this.exist)
      .pipe(first())
      .subscribe(
        data => {
          // alertservice success
          this.alertService.success('Registration successful', true);
        },
        error => {
          this.alertService.error(error);
        });
  }

}
