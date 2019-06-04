import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from './login/login.component';
import {RegisterComponent} from './register/register.component';
import {VehicleListComponent} from './vehicle-list/vehicle-list.component';
import {VehicleEditComponent} from './vehicle-edit/vehicle-edit.component';
import {HomeComponent} from './home/home.component';
import {UserAdminComponent} from './user-admin/user-admin.component';
import {VehicleDetailComponent} from './vehicle-detail/vehicle-detail.component';
import {VehicleGalleryComponent} from './vehicle-gallery/vehicle-gallery.component';
import {HeaderComponent} from './header/header.component';
import {FooterComponent} from './footer/footer.component';
import {VehicleListingComponent} from './vehicle-listing/vehicle-listing.component';
import {UserProfileComponent} from './user-profile/user-profile.component';
import {AuthGuardService} from './_services/auth-guard.service';
import {RoleGuardService} from './_services/role-guard.service';
import {RegisterCompanyComponent} from './register-company/register-company.component';
import {CompanyComponent} from './company/company.component';
import {ListingEditComponent} from './listing-edit/listing-edit.component';

const routes: Routes = [];

@NgModule({
  imports: [RouterModule.forRoot([
    { path: '', component: HomeComponent },
    { path: 'register', component: RegisterComponent, canActivate: [RoleGuardService], data: {expectedRole: 'ROLE_ADMIN' }},
    { path: 'register-company', component: RegisterCompanyComponent, canActivate: [RoleGuardService], data: {expectedRole: 'ROLE_ADMIN'}},
    { path: 'login', component: LoginComponent },
    { path: 'vehicle-list', component: VehicleListComponent},
    { path: 'vehicle-add', component: VehicleEditComponent, canActivate: [AuthGuardService]},
    { path: 'vehicle-edit', component: VehicleEditComponent, canActivate: [AuthGuardService]},
    { path: 'user-edit/:id', component: RegisterComponent, canActivate: [AuthGuardService]},
    { path: 'company/vehicle-edit/:id', component: VehicleEditComponent, canActivate: [AuthGuardService]},
    { path: 'company/listing-edit/:id', component: ListingEditComponent, canActivate: [AuthGuardService]},
    { path: 'company/listing-edit', component: ListingEditComponent, canActivate: [AuthGuardService]},
    { path: 'vehicle-listing/:id', component: VehicleListingComponent},
    { path: 'user-admin', component: UserAdminComponent, canActivate: [RoleGuardService], data: {expectedRole: 'ROLE_ADMIN'}},
    { path: 'user-admin/company-edit/:id', component: RegisterCompanyComponent, canActivate: [RoleGuardService], data: {expectedRole: 'ROLE_ADMIN'} },
    { path: 'user-admin/user-edit/:id', component: RegisterComponent, canActivate: [RoleGuardService], data: {expectedRole: 'ROLE_ADMIN'} },
    { path: 'vehicle-detail/:id', component: VehicleDetailComponent, canActivate: [AuthGuardService]},
    { path: 'vehicle-gallery-test', component: VehicleGalleryComponent},
    { path: 'user-profile/:id', component: UserProfileComponent, canActivate: [AuthGuardService]},
    { path: 'company', component: CompanyComponent, canActivate: [AuthGuardService]},
    // { path: '**', redirectTo: '' }
  ])],
  exports: [RouterModule]
})
export class AppRoutingModule { }
