import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DataTransferBetweenComponentsService {

  productId:number;
  constructor() { }

  setProductId(id:number)
  {
    this.productId=id;
  }

  getProductId()
  {
    return this.productId;
  }
}
