import { TestBed } from '@angular/core/testing';

import { ListingService } from './listing.service';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {Globals} from '../globals';

describe('ListingService', () => {
  beforeEach(() => TestBed.configureTestingModule({
    imports: [
      HttpClientTestingModule
    ],
    providers: [Globals]
  }));

  it('should be created', () => {
    const service: ListingService = TestBed.get(ListingService);
    expect(service).toBeTruthy();
  });
});
