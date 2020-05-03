import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms'
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { AppComponent } from './app.component';
import { AddProductComponent } from './add-product/add-product.component';
import { EditProductComponent } from './edit-product/edit-product.component';

import { ErrorPageNotFoundComponent } from './error-page-not-found/error-page-not-found.component';
import { DisplayproductsforuserComponent } from './displayproductsforuser/displayproductsforuser.component';
import { DisplayProductsForProductMasterComponent } from './display-products-for-product-master/display-products-for-product-master.component';
import { DialogBoxForConfirmationsComponent } from './dialog-box-for-confirmations/dialog-box-for-confirmations.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { SearchResultsComponent } from './search-results/search-results.component';
import { MaterialModule } from './material/material.module'


@NgModule({
  declarations: [
    AppComponent,
    AddProductComponent,
    EditProductComponent,
    ErrorPageNotFoundComponent,
    DisplayproductsforuserComponent,
    DisplayProductsForProductMasterComponent,
    DialogBoxForConfirmationsComponent,
    SearchResultsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule, FormsModule, HttpClientModule, BrowserAnimationsModule, MaterialModule, ReactiveFormsModule
  ],
  entryComponents: [
    DialogBoxForConfirmationsComponent
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
