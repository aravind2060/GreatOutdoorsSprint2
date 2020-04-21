import { NgModule, Component } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AddProductComponent } from './add-product/add-product.component';
import { DeleteProductComponent } from './delete-product/delete-product.component';
import { EditProductComponent } from './edit-product/edit-product.component';
import { GetProductComponent } from './get-product/get-product.component';
import { GetAllProductComponent } from './get-all-product/get-all-product.component';
import { ErrorPageNotFoundComponent } from './error-page-not-found/error-page-not-found.component';


const routes: Routes = [
  { path: "addproduct", component: AddProductComponent },
  { path: "editproduct", component: EditProductComponent },
  { path: "deleteproduct", component: DeleteProductComponent },
  { path: "getproduct", component: GetProductComponent },
  { path: "getallproduct", component: GetAllProductComponent },
  { path: "**", component: ErrorPageNotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
