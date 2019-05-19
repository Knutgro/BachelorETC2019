import { TestBed } from '@angular/core/testing';

import { RoleGuardService } from './role-guard.service';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {Globals} from '../globals';
import {JwtHelperService, JwtModule} from '@auth0/angular-jwt';
import {RouterTestingModule} from '@angular/router/testing';

describe('RoleGuardService', () => {
  beforeEach(() => TestBed.configureTestingModule({
    imports: [
      HttpClientTestingModule,
      RouterTestingModule,
      JwtModule.forRoot({
        config: {
          tokenGetter: () => {
            return '';
          }
        }
      })
    ],
    providers: [Globals, JwtHelperService]
  }));

  it('should be created', () => {
    const service: RoleGuardService = TestBed.get(RoleGuardService);
    expect(service).toBeTruthy();
  });
});
