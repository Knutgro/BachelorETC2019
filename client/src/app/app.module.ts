import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { fakeBackendProvider } from './_helper/fake-backend';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { VehicleListComponent } from './vehicle-list/vehicle-list.component';
import { LoginComponent } from './login/login.component';
import {
  MatButtonModule,
  MatCardModule,
  MatInputModule,
  MatListModule,
  MatPaginatorModule,
  MatTableModule,
  MatToolbarModule
} from '@angular/material';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { RegisterComponent } from './register/register.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {Globals} from './globals';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import { VehicleEditComponent } from './vehicle-edit/vehicle-edit.component';
import { HomeComponent } from './home/home.component';
import {JwtInterceptor} from './_helper/jwt.interceptor';
import {HeaderComponent} from './header/header.component';
import {FooterComponent} from './footer/footer.component';
import {ErrorInterceptor} from './_helper/error.interceptor';
import { AlertComponent } from './_components/alert.component';
import { UserAdminComponent } from './user-admin/user-admin.component';
import { VehicleDetailComponent } from './vehicle-detail/vehicle-detail.component';

@NgModule({
  declarations: [
    AppComponent,
    VehicleListComponent,
    LoginComponent,
    RegisterComponent,
    VehicleEditComponent,
    HomeComponent,
    HeaderComponent,
    FooterComponent,
    AlertComponent,
    UserAdminComponent,
    VehicleDetailComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    MatButtonModule,
    MatCardModule,
    MatInputModule,
    MatListModule,
    MatToolbarModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatTableModule,
    MatPaginatorModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },
    fakeBackendProvider,
    Globals
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
