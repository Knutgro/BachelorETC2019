import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VehicleEditComponent } from './vehicle-edit.component';
import {CUSTOM_ELEMENTS_SCHEMA} from '@angular/core';

describe('VehicleEditComponent', () => {
  let component: VehicleEditComponent;
  let fixture: ComponentFixture<VehicleEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VehicleEditComponent ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VehicleEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
