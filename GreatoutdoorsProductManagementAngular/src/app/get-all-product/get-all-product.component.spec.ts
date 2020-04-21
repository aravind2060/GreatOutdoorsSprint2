import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GetAllProductComponent } from './get-all-product.component';

describe('GetAllProductComponent', () => {
  let component: GetAllProductComponent;
  let fixture: ComponentFixture<GetAllProductComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GetAllProductComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GetAllProductComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
