import { Component, OnInit } from '@angular/core';
import { ProductDTO } from '../Model/ProductDTO';
import { ProductServiceService } from '../product-service.service';

@Component({
  selector: 'app-displayproductsforuser',
  templateUrl: './displayproductsforuser.component.html',
  styleUrls: ['./displayproductsforuser.component.css']
})
export class DisplayproductsforuserComponent implements OnInit {

  products;
  noOfPages;
  constructor(private productService: ProductServiceService) {
    this.getAllProducts();
  }

  ngOnInit(): void {

  }

  getAllProducts() {
    this.productService.getAllProducts().subscribe((data) => {

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
      this.noOfPages = data;

    }, error => {
      console.log(error.error);
    }
    );
  }

  getPage(index: number) {
    //  this.productService.
  }

}
