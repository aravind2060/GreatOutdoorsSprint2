import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http'
import { ProductDTO } from '../Model/ProductDTO'
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductServiceService {

  url: string = "http://localhost:8084/product/";
  constructor(private httpClient: HttpClient) { }

  addProduct(productMasterId: number, productDto: ProductDTO) {

    return this.httpClient.post(this.url + "addproduct/" + productMasterId, productDto, { headers: { 'Content-Type': 'application/json' } });
  }



  getProductsWhichBelongsToParticularProductMaster(productMasterId: number, pageNumber: number) {
    return this.httpClient.get(this.url + "getproductsofproductmaster/" + productMasterId + "/" + pageNumber);
  }

  getProductById(productId: number) {
    return this.httpClient.get(this.url + "getproductbyid/" + productId);
  }
  updateProduct(productMasterId: number, productDto: ProductDTO) {
    return this.httpClient.put(this.url + "updateproduct/" + productMasterId, productDto);
  }
  deleteProduct(productMasterId: number, productId: number) {
    return this.httpClient.delete(this.url + "deleteproduct/" + productMasterId + "/" + productId);
  }
  getAllProducts(userId: number, pageNumber: number) {
    return this.httpClient.get(this.url + "getproductsofuser/" + userId + "/" + pageNumber);
  }


  searchProduct(searchKeyword: string, pageNumber: number) {
    return this.httpClient.get(this.url + "searchproduct/" + searchKeyword + "/" + pageNumber);
  }
}
