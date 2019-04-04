import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VehicleAdComponent } from './vehicle-ad.component';

describe('VehicleAdComponent', () => {
  let component: VehicleAdComponent;
  let fixture: ComponentFixture<VehicleAdComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VehicleAdComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VehicleAdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
