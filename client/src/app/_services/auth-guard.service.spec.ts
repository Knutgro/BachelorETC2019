import { TestBed } from '@angular/core/testing';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {Globals} from '../globals';
import { RouterTestingModule } from '@angular/router/testing';
import {JwtHelperService, JwtModule} from '@auth0/angular-jwt';
import { AuthGuardService } from './auth-guard.service';
import {Injectable} from '@angular/core';
import {AuthenticationService} from './authentication.service';

describe('AuthGuardService', () => {
  beforeEach(() => TestBed.configureTestingModule({
    imports: [HttpClientTestingModule,
      RouterTestingModule,
      JwtModule.forRoot({
        config: {
          tokenGetter: () => {
            return '';
          }
        }
      })
    ],
    providers: [Globals, JwtHelperService, Injectable, AuthenticationService]
  }));

  it('should be created', () => {
    const service: AuthGuardService = TestBed.get(AuthGuardService);
    expect(service).toBeTruthy();
  });
});
