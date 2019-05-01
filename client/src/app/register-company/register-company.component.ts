import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {AuthenticationService} from '../_services/authentication.service';
import {UserService} from '../_services/user.service';
import {AlertService} from '../_services/alert.service';
import {Title} from '@angular/platform-browser';
import {first} from 'rxjs/operators';
import {CompanyService} from '../_services/company.service';

@Component({
  selector: 'app-register-company',
  templateUrl: './register-company.component.html',
  styleUrls: ['./register-company.component.scss']
})
export class RegisterCompanyComponent implements OnInit {
  registerForm: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private authenticationService: AuthenticationService,
    private userService: UserService,
    private alertService: AlertService,
    private companyService: CompanyService,
    private titleService: Title
  ) { }

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      name: ['', Validators.required],
      orgNr: ['', Validators.required],
      phoneNumber: ['', Validators.required],
    });
  }

  onSubmit() {
    if (this.registerForm.invalid) {
      return;
    }
    console.log(this.registerForm);
    this.companyService.register(this.registerForm.value)
      .pipe(first())
      .subscribe(
        data => {
          // alertservice success
          this.alertService.success('Registration successful', true);
          // this.router.navigate(this.router.getCurrentNavigation());
        },
        error => {
          this.alertService.error(error);
        });
  }

}
