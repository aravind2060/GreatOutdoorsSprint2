import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogBoxForConfirmationsComponent } from './dialog-box-for-confirmations.component';

describe('DialogBoxForConfirmationsComponent', () => {
  let component: DialogBoxForConfirmationsComponent;
  let fixture: ComponentFixture<DialogBoxForConfirmationsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DialogBoxForConfirmationsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogBoxForConfirmationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
