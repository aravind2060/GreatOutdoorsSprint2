import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { ProductDTO } from './Model/ProductDTO';
@Injectable({
  providedIn: 'root'
})
export class ProductServiceService {

  url: string = "http://localhost:8084/";
  constructor(private httpClient: HttpClient) { }

  addProduct(productDto: ProductDTO) {
    return this.httpClient.post(this.url + "addproduct", productDto);
  }

  getProductsWhichBelongsToParticularProductMaster(id: number) {
    return this.httpClient.get(this.url + "viewproductsofproductmaster/" + id);
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
}
