import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VehicleGalleryComponent } from './vehicle-gallery.component';

describe('VehicleGalleryComponent', () => {
  let component: VehicleGalleryComponent;
  let fixture: ComponentFixture<VehicleGalleryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VehicleGalleryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VehicleGalleryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
