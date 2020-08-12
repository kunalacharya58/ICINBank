import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BriComponent } from './bri.component';

describe('BriComponent', () => {
  let component: BriComponent;
  let fixture: ComponentFixture<BriComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BriComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BriComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
