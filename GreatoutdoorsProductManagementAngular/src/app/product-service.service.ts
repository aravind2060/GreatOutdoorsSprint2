import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http'
import { ProductDTO } from './Model/ProductDTO';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductServiceService {

  url: string = "http://localhost:8084/";
  constructor(private httpClient: HttpClient) { }

  addProduct(productDto: ProductDTO) {

    return this.httpClient.post(this.url + "addproduct", productDto, { headers: { 'Content-Type': 'application/json' } });
  }



  getProductsWhichBelongsToParticularProductMaster(productMasterId: number) {
    return this.httpClient.get(this.url + "viewproductsofproductmaster/" + productMasterId);
  }

  getProductById(id: number) {
    return this.httpClient.get(this.url + "getproductbyid/" + id);
  }
  updateProduct(productDto: ProductDTO) {
    return this.httpClient.put(this.url + "updateproduct", productDto);
  }
  deleteProduct(id: number) {
    return this.httpClient.delete(this.url + "deleteproduct/" + id);
  }
  getAllProducts(pageNumber: number) {
    return this.httpClient.get(this.url + "getallproducts/" + pageNumber);
  }


  searchProduct(searchKeyword: string, pageNumber: number) {
    return this.httpClient.get(this.url + "searchproduct/" + searchKeyword + "/" + pageNumber);
  }
}
