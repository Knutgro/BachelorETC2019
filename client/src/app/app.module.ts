import {BrowserModule, Title} from '@angular/platform-browser';
import {LOCALE_ID, NgModule} from '@angular/core';
import 'hammerjs';
import { WavesModule, ButtonsModule, IconsModule } from 'angular-bootstrap-md'
import { fakeBackendProvider } from './_helper/fake-backend';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { VehicleListComponent } from './vehicle-list/vehicle-list.component';
import { LoginComponent } from './login/login.component';
import { registerLocaleData} from '@angular/common';
import localeNb from '@angular/common/locales/nb';
registerLocaleData(localeNb);
import {
  MatAutocompleteModule,
  MatButtonModule,
  MatCardModule, MatCheckboxModule,
  MatChipsModule, MatExpansionModule,
  MatFormFieldModule,
  MatGridListModule,
  MatIconModule,
  MatInputModule,
  MatListModule,
  MatPaginatorModule, MatSelectModule, MatSidenavModule,
  MatTableModule,
  MatToolbarModule,
  MatPaginatorIntl,
  MatTabsModule,
} from '@angular/material';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RegisterComponent } from './register/register.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { Globals } from './globals';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { VehicleEditComponent } from './vehicle-edit/vehicle-edit.component';
import { HomeComponent } from './home/home.component';
import { JwtInterceptor } from './_helper/jwt.interceptor';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { ErrorInterceptor } from './_helper/error.interceptor';
import { AlertComponent } from './_components/alert.component';
import { UserAdminComponent } from './user-admin/user-admin.component';
import { NgxGalleryModule } from 'ngx-gallery';
import { VehicleGalleryComponent } from './vehicle-gallery/vehicle-gallery.component';
import { VehicleDetailComponent } from './vehicle-detail/vehicle-detail.component';
import { SearchBarComponent } from './search-bar/search-bar.component';
import { SearchResultComponent } from './search-result/search-result.component';
import { FlexLayoutModule } from '@angular/flex-layout';
import { SearchFilterComponent } from './search-filter/search-filter.component';
import { VehicleListingComponent } from './vehicle-listing/vehicle-listing.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import {JwtHelperService, JwtModule} from '@auth0/angular-jwt';
import { RegisterCompanyComponent } from './register-company/register-company.component';
import {SidenavService} from './_services/sidenav.service';
import { CompanyComponent } from './company/company.component';
import {getNorwegianPaginatorIntl} from './_helper/norsk-paginator';
import { ListingSearchComponent } from './listing-search/listing-search.component';
import { ListingEditComponent } from './listing-edit/listing-edit.component';

export function getToken() {
  return localStorage.getItem('currentUser');
}

@NgModule({
  declarations: [
    AppComponent,
    VehicleListComponent,
    LoginComponent,
    RegisterComponent,
    VehicleEditComponent,
    VehicleDetailComponent,
    HomeComponent,
    HeaderComponent,
    FooterComponent,
    AlertComponent,
    UserAdminComponent,
    VehicleGalleryComponent,
    SearchBarComponent,
    SearchResultComponent,
    VehicleListingComponent,
    SearchFilterComponent,
    UserProfileComponent,
    RegisterCompanyComponent,
    CompanyComponent,
    ListingSearchComponent,
    ListingEditComponent,
  ],
  imports: [
    JwtModule.forRoot({
      config: {
        tokenGetter: getToken,
        whitelistedDomains: ['localhost:4200']
      }
    }),
    NgxGalleryModule,
    BrowserModule,
    AppRoutingModule,
    MatGridListModule,
    MatButtonModule,
    MatCardModule,
    MatInputModule,
    MatListModule,
    MatToolbarModule,
    MatFormFieldModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatTableModule,
    MatPaginatorModule,
    MatAutocompleteModule,
    MatIconModule,
    MatChipsModule,
    MatInputModule,
    HttpClientModule,
    MatSidenavModule,
    MatCheckboxModule,
    FlexLayoutModule,
    MatExpansionModule,
    MatSelectModule,
    WavesModule.forRoot(),
    IconsModule,
    ButtonsModule,
    MatTableModule,
    MatTabsModule,
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },
    { provide: MatPaginatorIntl, useValue: getNorwegianPaginatorIntl() },
    { provide: LOCALE_ID, useValue: 'nb'},
    Globals,
    Title,
    JwtHelperService,
    SidenavService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
