import { Component, OnInit } from '@angular/core';
import { ProductServiceService } from '../product-service.service';
import { NgForm } from '@angular/forms';
import { ProductDTO } from '../Model/ProductDTO';
import { ExceptionResponse } from '../Model/ExceptionResponse';
import { strict } from 'assert';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent implements OnInit {

  productDto: ProductDTO = new ProductDTO();
  exceptionResponse: ExceptionResponse = new ExceptionResponse();
  listOfErrors: Map<string, Map<string, string>>;

  constructor(private productService: ProductServiceService) { }

  ngOnInit(): void {

  }

  addProduct(myForm: NgForm) {



    this.productService.addProduct(this.productDto).subscribe(
      (response) => {
        console.log("inside success!");
        // let data = response.json();
        // console.log("Data :" + data);
        // console.log("Status text: " + response.statusText);
        // console.log("body: " + response.body);
        // console.log("Text: " + response.text);
        console.log(response);
      },
      (error: HttpErrorResponse) => {
        console.log("Error page");
        this.exceptionResponse = error.error;
        this.listOfErrors = this.exceptionResponse.listOfErrors;
        console.log(this.listOfErrors);

      }
    );
    //this.calling();
  }

  calling() {
    console.log(this.listOfErrors.forEach(data => console.log(data.get("NotUnique"))));
  }






}
