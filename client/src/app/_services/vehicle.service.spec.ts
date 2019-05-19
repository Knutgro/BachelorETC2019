import { TestBed } from '@angular/core/testing';

import { VehicleService } from './vehicle.service';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {Globals} from '../globals';

describe('VehicleService', () => {
  beforeEach(() => TestBed.configureTestingModule({
    imports: [
      HttpClientTestingModule
    ],
    providers: [Globals]
  }));

  it('should be created', () => {
    const service: VehicleService = TestBed.get(VehicleService);
    expect(service).toBeTruthy();
  });
});
