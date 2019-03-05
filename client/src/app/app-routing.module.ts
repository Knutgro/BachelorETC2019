import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from './login/login.component';
import {RegisterComponent} from './register/register.component';
import {VehicleListComponent} from './vehicle-list/vehicle-list.component';
import {VehicleEditComponent} from './vehicle-edit/vehicle-edit.component';
import {HomeComponent} from './home/home.component';
import {UserAdminComponent} from './user-admin/user-admin.component';
import {VehicleDetailComponent} from './vehicle-detail/vehicle-detail.component';

const routes: Routes = [];

@NgModule({
  imports: [RouterModule.forRoot([
    { path: '', component: HomeComponent },
    { path: 'register', component: RegisterComponent },
    { path: 'login', component: LoginComponent },
    { path: 'vehicle-list', component: VehicleListComponent},
    { path: 'vehicle-add', component: VehicleEditComponent},
    { path: 'vehicle-edit', component: VehicleEditComponent},
    { path: 'vehicle-edit/:id', component: VehicleEditComponent},
    { path: 'user-admin', component: UserAdminComponent},
    { path: 'vehicle-detail/:id', component: VehicleDetailComponent},
    { path: '**', redirectTo: '' }
  ])],
  exports: [RouterModule]
})
export class AppRoutingModule { }
