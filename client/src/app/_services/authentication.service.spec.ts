import { TestBed } from '@angular/core/testing';

import { AuthenticationService } from './authentication.service';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {AppComponent} from '../app.component';
import {Globals} from '../globals';

describe('AuthenticationService', () => {
  beforeEach(() => TestBed.configureTestingModule({
    imports: [
      HttpClientTestingModule
    ],
    declarations: [
      Globals
    ],
  }));

  it('should be created', () => {
    const service: AuthenticationService = TestBed.get(AuthenticationService);
    expect(service).toBeTruthy();
  });
});
