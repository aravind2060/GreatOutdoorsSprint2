import { Component, OnInit } from '@angular/core';
import { ProductDTO } from '../Model/ProductDTO'
import { ProductServiceService } from '../services/product-service.service';
import { HttpResponse } from '@angular/common/http';
import { QueryResponse } from '../Model/QueryResponse'
import { CurrentLoggedUserService } from '../services/current-logged-user.service';

@Component({
  selector: 'app-displayproductsforuser',
  templateUrl: './displayproductsforuser.component.html',
  styleUrls: ['./displayproductsforuser.component.css']
})
export class DisplayproductsforuserComponent implements OnInit {

  queryResponse: QueryResponse;
  noOfPages = [];
  currentPage = 0;

  constructor(private productService: ProductServiceService, private currentUser: CurrentLoggedUserService) {
    this.getAllProducts(this.currentPage);
  }

  ngOnInit(): void {

  }

  getAllProducts(pageNumber) {
    this.currentPage = pageNumber;
    this.productService.getAllProducts(this.currentUser.getCurrentUser()?.userId, pageNumber).subscribe(
      (data: QueryResponse) => {
        this.queryResponse = data;
        this.noOfPages = new Array(this.queryResponse.totalNoOfPages);
        console.log(this.queryResponse.list);
      }
      , error => {
        console.log(error.error);
        //TODO change console to ui
      }

    );

  }



}
