import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayProductsForProductMasterComponent } from './display-products-for-product-master.component';

describe('DisplayProductsForProductMasterComponent', () => {
  let component: DisplayProductsForProductMasterComponent;
  let fixture: ComponentFixture<DisplayProductsForProductMasterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DisplayProductsForProductMasterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DisplayProductsForProductMasterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
