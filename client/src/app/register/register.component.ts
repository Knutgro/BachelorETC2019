import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {UserService} from '../_services/user.service';
import {Router} from '@angular/router';
import {AuthenticationService} from '../_services/authentication.service';
import {first} from 'rxjs/operators';

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
  ) {
    if (this.authenticationService.currentUserValue) {
      this.router.navigate(['/']);
    }
  }

  ngOnInit() {
    console.log('Before init');
    this.registerForm = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', Validators.required],
      username: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(6)]]
    });
    console.log('After Init');
  }

  get f() { return this.registerForm.controls; }

  onSubmit() {
    console.log('On Submit');
    this.submitted = true;
    if (this.registerForm.invalid) {
      console.log('Invalid Form');
      return;
    }
    console.log('Valid Form');
    this.loading = true;
    this.userService.register(this.registerForm.value)
      .pipe(first())
      .subscribe(
        data => {
          // alertservice success
          console.log('Success');
          this.router.navigate(['/login']);
        },
        error => {
          // this.alertService.error(error);
          this.loading = false;
        });
    console.log('On Submit after');
  }
}
