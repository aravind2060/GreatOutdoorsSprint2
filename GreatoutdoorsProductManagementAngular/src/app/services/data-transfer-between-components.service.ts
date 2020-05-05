import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DataTransferBetweenComponentsService {

  productId: number;
  searchKeyword: string;

  constructor() { }

  setProductId(id: number) {
    this.productId = id;
  }

  getProductId() {
    return this.productId;
  }

  setSearchKeyword(searchKeyword: string) {
    this.searchKeyword = searchKeyword;
  }

  getSearchKeyword() {
    return this.searchKeyword;
  }
}
