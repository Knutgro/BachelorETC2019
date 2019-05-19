import { TestBed } from '@angular/core/testing';

import { SidenavService } from './sidenav.service';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {Globals} from '../globals';

describe('SidenavService', () => {
  beforeEach(() => TestBed.configureTestingModule({
    imports: [
      HttpClientTestingModule
    ],
    providers: [Globals]
  }));

  it('should be created', () => {
    const service: SidenavService = TestBed.get(SidenavService);
    expect(service).toBeTruthy();
  });
});
