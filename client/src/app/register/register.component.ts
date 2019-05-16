import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, NgForm, Validators} from '@angular/forms';
import {UserService} from '../_services/user.service';
import {ActivatedRoute, Router} from '@angular/router';
import {AuthenticationService} from '../_services/authentication.service';
import {first} from 'rxjs/operators';
import {AlertService} from '../_services/alert.service';
import {Title} from '@angular/platform-browser';
import {Subscription} from 'rxjs';
import {ɵplatformCoreDynamicTesting} from '@angular/platform-browser-dynamic/testing';
import {User} from '../_models/user';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  sub: Subscription;
  currentUser: User;
  registerForm: FormGroup;
  loading = false;
  submitted = false;
  user: any = {};
  exist: boolean;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private route: ActivatedRoute,
    private authenticationService: AuthenticationService,
    private userService: UserService,
    private alertService: AlertService,
    private titleService: Title
  ) {
    this.authenticationService.currentUser.subscribe(x => this.currentUser = x);
    if (this.authenticationService.currentUserValue) {
      // this.router.navigate(['/']);
    }
  }

  ngOnInit() {
    this.exist = false;
    this.titleService.setTitle('Registrer');
    this.sub = this.route.params.subscribe( params => {
      const id = params['id'];
      console.log(id);
      console.log(this.currentUser.firstName);
      if (id) {
        this.userService.getById(id).subscribe((user: any) => {
          if (user) {
            this.user = user;
            console.log(user);
            this.titleService.setTitle(`Rediger ${user.name}`);
            this.exist = true;
          } else {
            // console.log(`user with id '${id}' not found, returning to list`);
            this.alertService.error(`user with id '${id}' not found, returning to home`);
            this.router.navigate(['/']);
          }
        });
      } else {
        this.user.role = [['user']];
      }
    });
    /*this.registerForm = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', Validators.required, Validators.email],
      username: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(6)]],
      company_id: ['', Validators.required],
      role: [['']]
    });*/
  }

  get f() { return this.registerForm.controls; }

  onSubmit(form: NgForm) {
    this.submitted = true;
    this.loading = true;
    console.log(this.exist);
    console.log(form);
    this.userService.register(form, this.exist)
      .pipe(first())
      .subscribe(
        data => {
          // alertservice success
          this.alertService.success('Registration successful', true);
          // this.router.navigate(this.router.getCurrentNavigation());
        },
        error => {
          this.alertService.error(error);
          this.loading = false;
        });
  }
}
