import { TestBed } from '@angular/core/testing';

import { UserService } from './user.service';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {Globals} from '../globals';

describe('UserService', () => {
  beforeEach(() => TestBed.configureTestingModule({
    imports: [
      HttpClientTestingModule
    ],
    providers: [Globals]
  }));

  it('should be created', () => {
    const service: UserService = TestBed.get(UserService);
    expect(service).toBeTruthy();
  });
});
