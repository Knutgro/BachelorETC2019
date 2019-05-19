import { TestBed } from '@angular/core/testing';

import { RegionService } from './region.service';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {Globals} from '../globals';

describe('RegionService', () => {
  beforeEach(() => TestBed.configureTestingModule({
    imports: [
      HttpClientTestingModule
    ],
    providers: [Globals]
  }));

  it('should be created', () => {
    const service: RegionService = TestBed.get(RegionService);
    expect(service).toBeTruthy();
  });
});
