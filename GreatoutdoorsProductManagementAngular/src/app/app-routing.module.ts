import { NgModule, Component } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AddProductComponent } from './add-product/add-product.component';
import { EditProductComponent } from './edit-product/edit-product.component';
import { ErrorPageNotFoundComponent } from './error-page-not-found/error-page-not-found.component';
import { DisplayproductsforuserComponent } from './displayproductsforuser/displayproductsforuser.component';
import { DisplayProductsForProductMasterComponent } from './display-products-for-product-master/display-products-for-product-master.component';
import { SearchResultsComponent } from './search-results/search-results.component';
import { SignInComponent } from './sign-in/sign-in.component';
import { SignUpComponent } from './sign-up/sign-up.component';


const routes: Routes = [
  { path: "signup", component: SignUpComponent },
  { path: "signin", component: SignInComponent },
  { path: "addproduct", component: AddProductComponent },
  { path: "updateproduct", component: EditProductComponent },
  { path: "displayproductsforproductmaster", component: DisplayProductsForProductMasterComponent },
  { path: "displayproductsforuser", component: DisplayproductsforuserComponent },
  { path: "searchresultsforproducts", component: SearchResultsComponent },
  { path: "**", component: ErrorPageNotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
