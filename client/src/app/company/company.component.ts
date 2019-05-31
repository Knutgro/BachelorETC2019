import {Component, OnDestroy, OnInit} from '@angular/core';
import {User} from '../_models/user';
import {Subscription} from 'rxjs';
import {Company} from '../_models/company';
import {MatTableDataSource} from '@angular/material';

@Component({
  selector: 'app-company',
  templateUrl: './company.component.html',
  styleUrls: ['./company.component.scss']
})
export class CompanyComponent implements OnInit, OnDestroy {

  currentUser: User;
  currentUserSubscription: Subscription;
  columnsVehicles: string[] = ['id', 'name', 'phone_number', 'update', 'delete'];
  columnsListings: string[] = ['id', 'username', 'lastName', 'firstName', 'update', 'delete'];
  users: User[] = [];
  companies: Company[] = [];
  dataUsers: MatTableDataSource<User>;
  dataCompanies: MatTableDataSource<Company>;
  constructor() { }

  ngOnInit() {
  }
  ngOnDestroy(): void {
  }

}
