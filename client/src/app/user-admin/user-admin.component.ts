import {Component, OnDestroy, OnInit} from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {User} from '../_models/user';
import {Subscription} from 'rxjs';
import {AuthenticationService} from '../_services/authentication.service';
import {UserService} from '../_services/user.service';
import {first} from 'rxjs/operators';
import {Title} from '@angular/platform-browser';
import {CompanyService} from '../_services/company.service';
import {Company} from '../_models/company';
import {AlertService} from '../_services/alert.service';

@Component({
  selector: 'app-user-admin',
  templateUrl: './user-admin.component.html',
  styleUrls: ['./user-admin.component.scss']
})
export class UserAdminComponent implements OnInit, OnDestroy {
  currentUser: User;
  currentUserSubscription: Subscription;
  columnsCompanies: string[] = ['id', 'name', 'phone_number', 'update', 'delete'];
  columnsUsers: string[] = ['id', 'username', 'lastName', 'firstName', 'update', 'delete'];
  users: User[] = [];
  companies: Company[] = [];
  dataUsers: MatTableDataSource<User>;
  dataCompanies: MatTableDataSource<Company>;

  applyFilterUsers(filterValue: string) {
    this.dataUsers.filter = filterValue.trim().toLowerCase();
  }

  applyFilterCompanies(filterValue: string) {
    this.dataCompanies.filter = filterValue.trim().toLowerCase();
  }

  constructor(
    private authenticationService: AuthenticationService,
    private userService: UserService,
    private companyService: CompanyService,
    private titleService: Title,
    private alertService: AlertService
  ) {
    this.currentUserSubscription = this.authenticationService.currentUser.subscribe(user => {
      this.currentUser = user;
    });

  }

  ngOnInit() {
    this.titleService.setTitle('Administrer');
    this.loadAllUsers();
    this.loadAllCompanies();
  }

  ngOnDestroy() {
    // unsubscribe to ensure no memory leaks
    this.currentUserSubscription.unsubscribe();
  }

  deleteUser(id: number) {
    this.userService.delete(id).pipe(first()).subscribe(() => {
      this.loadAllUsers();
      this.alertService.success('Bruker slettet');
    }, error => {
      this.alertService.error(error);
    });
  }

  private loadAllUsers() {
    this.userService.getAll().pipe(first()).subscribe(users => {
      this.users = users;
      console.log(users);
      this.dataUsers = new MatTableDataSource(users);
    });
  }

  private loadAllCompanies() {
    this.companyService.getAll().pipe(first()).subscribe(companies => {
      this.companies = companies;
      this.dataCompanies = new MatTableDataSource(companies);
    });
  }

  deleteCompany(id: number) {
    this.companyService.delete(id).pipe(first()).subscribe(() => {
      this.loadAllCompanies();
      this.alertService.success('Selskap slettet');
    }, error => {
      this.alertService.error(error);
    });
  }

}
