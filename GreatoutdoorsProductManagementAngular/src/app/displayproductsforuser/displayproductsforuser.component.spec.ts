import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayproductsforuserComponent } from './displayproductsforuser.component';

describe('DisplayproductsforuserComponent', () => {
    let component: DisplayproductsforuserComponent;
    let fixture: ComponentFixture<DisplayproductsforuserComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [DisplayproductsforuserComponent]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(DisplayproductsforuserComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});