import { Injectable } from '@angular/core';
import { HttpRequest, HttpResponse, HttpHandler, HttpEvent, HttpInterceptor, HTTP_INTERCEPTORS } from '@angular/common/http';
import { Observable, of, throwError } from 'rxjs';
import { delay, mergeMap, materialize, dematerialize } from 'rxjs/operators';
import {NgxGalleryImage} from 'ngx-gallery';

@Injectable()
export class FakeBackendInterceptor implements HttpInterceptor {
  images: any[] = [];
  constructor() { }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    // array in local storage for registered users
    const users: any[] = JSON.parse(localStorage.getItem('users')) || [];
    const vehicles: any[] = JSON.parse(localStorage.getItem('vehicles')) || [];
    let galleryImages: Blob[];
    // wrap in delayed observable to simulate server api call
    return of(null).pipe(mergeMap(() => {

      // authenticate
      if (request.url.endsWith('/authentication') && request.method === 'POST') {
        // find if any user matches login credentials
        const filteredUsers = users.filter(user => {
          return user.username === request.body.username && user.password === request.body.password;
        });

        if (filteredUsers.length) {
          // if login details are valid return 200 OK with user details and fake jwt token
          const user = filteredUsers[0];
          const body = {
            id: user.id,
            username: user.username,
            firstName: user.firstName,
            lastName: user.lastName,
            token: 'fake-jwt-token'
          };

          return of(new HttpResponse({ status: 200, body: body }));
        } else {
          // else return 400 bad request
          return throwError({ error: { message: 'Username or password is incorrect' } });
        }
      }

      // get users
      if (request.url.endsWith('/users') && request.method === 'GET') {
        // check for fake auth token in header and return users if valid, this security is implemented server side in a real application
        if (request.headers.get('Authorization') === 'Bearer fake-jwt-token') {
          return of(new HttpResponse({ status: 200, body: users }));
        } else {
          // return 401 not authorised if token is null or invalid
          return throwError({ status: 401, error: { message: 'Unauthorised' } });
        }
      }

      // get user by id
      if (request.url.match(/\/users\/\d+$/) && request.method === 'GET') {
        // check for fake auth token in header and return user if valid, this security is implemented server side in a real application
        if (request.headers.get('Authorization') === 'Bearer fake-jwt-token') {
          // find user by id in users array
          const urlParts = request.url.split('/');
          const id = parseInt(urlParts[urlParts.length - 1]);
          const matchedUsers = users.filter(user => user.id === id);
          const user = matchedUsers.length ? matchedUsers[0] : null;

          return of(new HttpResponse({ status: 200, body: user }));
        } else {
          // return 401 not authorised if token is null or invalid
          return throwError({ status: 401, error: { message: 'Unauthorised' } });
        }
      }

      // register user
      if (request.url.endsWith('/register') && request.method === 'POST') {
        // get new user object from post body
        const newUser = request.body;

        // validation
        const duplicateUser = users.filter(user => user.username === newUser.username).length;
        if (duplicateUser) {
          return throwError({ error: { message: 'Username "' + newUser.username + '" is already taken' } });
        }

        // save new user
        newUser.id = users.length + 1;
        users.push(newUser);
        localStorage.setItem('users', JSON.stringify(users));

        // respond 200 OK
        return of(new HttpResponse({ status: 200 }));
      }

      // delete user
      if (request.url.match(/\/users\/\d+$/) && request.method === 'DELETE') {
        // check for fake auth token in header and return user if valid, this security is implemented server side in a real application
        if (request.headers.get('Authorization') === 'Bearer fake-jwt-token') {
          // find user by id in users array
          const urlParts = request.url.split('/');
          const id = parseInt(urlParts[urlParts.length - 1]);
          for (let i = 0; i < users.length; i++) {
            const user = users[i];
            if (user.id === id) {
              // delete user
              users.splice(i, 1);
              localStorage.setItem('users', JSON.stringify(users));
              break;
            }
          }

          // respond 200 OK
          return of(new HttpResponse({ status: 200 }));
        } else {
          // return 401 not authorised if token is null or invalid
          return throwError({ status: 401, error: { message: 'Unauthorised' } });
        }
      }

      // get vehicles
      if (request.url.endsWith('/vehicles') && request.method === 'GET') {
        // check for fake auth token in header and return users if valid, this security is implemented server side in a real application
        if (request.headers.get('Authorization') === 'Bearer fake-jwt-token') {
          return of(new HttpResponse({ status: 200, body: vehicles }));
        } else {
          // return 401 not authorised if token is null or invalid
          return throwError({ status: 401, error: { message: 'Unauthorised' } });
        }
      }

      // get vehicle by id
      if (request.url.match(/\/vehicles\/\d+$/) && request.method === 'GET') {
        // check for fake auth token in header and return user if valid, this security is implemented server side in a real application
        if (request.headers.get('Authorization') === 'Bearer fake-jwt-token') {
          // find user by id in users array
          const urlParts = request.url.split('/');
          const id = parseInt(urlParts[urlParts.length - 1]);
          const matchedVehicles = vehicles.filter(vehicle => vehicle.id === id);
          const vehicle = matchedVehicles.length ? matchedVehicles[0] : null;
          vehicle.href = request.url;
          return of(new HttpResponse({ status: 200, body: vehicle }));
        } else {
          // return 401 not authorised if token is null or invalid
          return throwError({ status: 401, error: { message: 'Unauthorised' } });
        }
      }

      // Edit vehicle
      if (request.url.match(/\/vehicles\/\d+$/) && request.method === 'PUT') {
        const newVehicle = request.body;
        console.log(newVehicle);
        // check for fake auth token in header and return user if valid, this security is implemented server side in a real application
        if (request.headers.get('Authorization') === 'Bearer fake-jwt-token') {
          const urlParts = request.url.split('/');
          const id = parseInt(urlParts[urlParts.length - 1]);
          const items = JSON.parse(localStorage.getItem('vehicles'));
          for (let i = 0; i < users.length; i++) {
            const vehicle = items[i];
            if (vehicle.id === id) {
              items[i].name = newVehicle.name;
              items[i].nick = newVehicle.nick;
              items[i].color = newVehicle.color;
              items[i].make = newVehicle.make;
              items[i].tires = newVehicle.tires;
              items[i].maxFuel = newVehicle.maxFuel;
              items[i].chassisID = newVehicle.chassisID;
              items[i].maxKmTank = newVehicle.maxKmTank;
              items[i].fuelSystem = newVehicle.fuelSystem;
              items[i].gearSystem = newVehicle.gearSystem;
              items[i].drive = newVehicle.drive;
              items[i].interior = newVehicle.interior;
              items[i].barcode = newVehicle.barcode;
              items[i].mileage = newVehicle.mileage;
              localStorage.setItem('vehicles', JSON.stringify(items));
              break;
            }
          }
          return of(new HttpResponse({ status: 200, body: vehicles }));
        } else {
          // return 401 not authorised if token is null or invalid
          return throwError({ status: 401, error: { message: 'Unauthorised' } });
        }
      }

      // Add vehicle
      if (request.url.endsWith('/vehicles/add') && request.method === 'POST') {
        const newVehicle = request.body;

        // validation
        const duplicateVehicle = vehicles.filter(vehicle => vehicle.name === newVehicle.name).length;
        if (duplicateVehicle) {
          return throwError({ error: { message: 'Vehicle "' + newVehicle.name + '" already exists' } });
        }

        // save new vehicle
        newVehicle.id = vehicles.length + 1;
        vehicles.push(newVehicle);
        localStorage.setItem('vehicles', JSON.stringify(vehicles));

        // respond 200 OK
        return of(new HttpResponse({ status: 200 }));
      }

      if (request.url.match(/\/images\/\d+$/) && request.method === 'GET') {
        /*const reader = new FileReader();
        reader.addEventListener('load', () => {
          this.image = reader.result;
        }
        const gallery = new Blob([URL.createObjectURL('assets/1-car.png')], {type: 'image/png'});*/

        this.images = [
          {
            url: 'assets/1-car.png'
          },
          {
            url: 'assets/2-car.jpg'
          }
         ];
        return of(new HttpResponse({ status: 200, body: this.images }));
      }

      // pass through any requests not handled above
      return next.handle(request);

    }))

    // call materialize and dematerialize to ensure delay even if an error is thrown (https://github.com/Reactive-Extensions/RxJS/issues/648)
      .pipe(materialize())
      .pipe(delay(500))
      .pipe(dematerialize());
  }
}

export let fakeBackendProvider = {
  // use fake backend in place of Http service for backend-less development
  provide: HTTP_INTERCEPTORS,
  useClass: FakeBackendInterceptor,
  multi: true
};
