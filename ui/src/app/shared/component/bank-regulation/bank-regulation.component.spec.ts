import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BankRegulationComponent } from './bank-regulation.component';

describe('BankRegulationComponent', () => {
  let component: BankRegulationComponent;
  let fixture: ComponentFixture<BankRegulationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BankRegulationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BankRegulationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
