import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {UserService} from '../_services/user.service';
import {Router} from '@angular/router';
import {AuthenticationService} from '../_services/authentication.service';
import {first} from 'rxjs/operators';
import {AlertService} from '../_services/alert.service';
import {Title} from '@angular/platform-browser';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  registerForm: FormGroup;
  loading = false;
  submitted = false;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private authenticationService: AuthenticationService,
    private userService: UserService,
    private alertService: AlertService,
    private titleService: Title
  ) {
    if (this.authenticationService.currentUserValue) {
      // this.router.navigate(['/']);
    }
  }

  ngOnInit() {
    this.titleService.setTitle('Registrer');
    this.registerForm = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', Validators.required, Validators.email],
      username: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(6)]],
      company_id: ['', Validators.required],
      role: [['']]
    });
  }

  get f() { return this.registerForm.controls; }

  onSubmit() {
    this.submitted = true;
    if (this.registerForm.invalid) {
      return;
    }
    this.loading = true;
    console.log(this.registerForm);
    this.userService.register(this.registerForm.value)
      .pipe(first())
      .subscribe(
        data => {
          // alertservice success
          this.alertService.success('Registration successful', true);
          this.router.navigate(this.router.getCurrentNavigation());
        },
        error => {
          this.alertService.error(error);
          this.loading = false;
        });
  }
}
