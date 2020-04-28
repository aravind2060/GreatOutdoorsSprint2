import { Component, OnInit } from '@angular/core';
import { ProductDTO } from '../Model/ProductDTO';
import { ProductServiceService } from '../product-service.service';
import { HttpResponse } from '@angular/common/http';

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
    this.getNoOfPagesForProductsOfUser();
    this.getAllProducts(this.currentPage);
  }

  ngOnInit(): void {

  }

  getAllProducts(pageNumber) {
    this.currentPage = pageNumber;
    this.productService.getAllProducts(pageNumber).subscribe((data) => {
      this.products = data;
      console.log(this.products);
    }, error => {
      console.log(error.error);
      //TODO change console to ui
    }

    );

  }

  getNoOfPagesForProductsOfUser() {
    this.productService.getNoOfPagesForProductsOfUser().subscribe((data) => {
      for (let i = 0; i < data; i++) {
        this.noOfPages[i] = i;
      }

    }, error => {
      console.log(error.error);
    }
    );
  }

}
