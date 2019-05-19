import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterCompanyComponent } from './register-company.component';
import {CUSTOM_ELEMENTS_SCHEMA} from '@angular/core';

describe('RegisterCompanyComponent', () => {
  let component: RegisterCompanyComponent;
  let fixture: ComponentFixture<RegisterCompanyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegisterCompanyComponent ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisterCompanyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
