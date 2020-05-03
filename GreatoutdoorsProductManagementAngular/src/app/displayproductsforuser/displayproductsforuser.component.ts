import { Component, OnInit } from '@angular/core';
import { ProductDTO } from '../Model/ProductDTO';
import { ProductServiceService } from '../product-service.service';
import { HttpResponse } from '@angular/common/http';
import { QueryResponse } from '../Model/QueryResponse';

@Component({
  selector: 'app-displayproductsforuser',
  templateUrl: './displayproductsforuser.component.html',
  styleUrls: ['./displayproductsforuser.component.css']
})
export class DisplayproductsforuserComponent implements OnInit {

  products;
  noOfPages = [];
  currentPage = 0;

  constructor(private productService: ProductServiceService) {
    this.getAllProducts(this.currentPage);
  }

  ngOnInit(): void {

  }

  getAllProducts(pageNumber) {
    this.currentPage = pageNumber;
    this.productService.getAllProducts(102, pageNumber).subscribe(
      (data: QueryResponse) => {
        this.products = data.list;
        for (let i: number = 0; i < data.totalNoOfPages; i++)
          this.noOfPages[i] = i;

        console.log(this.products);
      }
      , error => {
        console.log(error.error);
        //TODO change console to ui
      }

    );

  }



}
