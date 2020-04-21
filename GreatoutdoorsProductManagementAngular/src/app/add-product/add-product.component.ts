import { Component, OnInit } from '@angular/core';
import { ProductServiceService } from '../product-service.service';
import { NgForm } from '@angular/forms';
import { ProductDTO } from '../Model/ProductDTO';
import { ExceptionResponse } from '../Model/ExceptionResponse';
import { strict } from 'assert';

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent implements OnInit {

  productDto: ProductDTO = new ProductDTO();
  exceptionResponse: ExceptionResponse = new ExceptionResponse();
  constructor(private productService: ProductServiceService) { }

  ngOnInit(): void {
  }

  addProduct(myForm: NgForm) {

    this.productDto.productBrand = myForm.value.productBrand;
    this.productDto.productCategory = myForm.value.productCategory;
    this.productDto.productColour = myForm.value.productColour;
    this.productDto.productDimension = myForm.value.productDimension;
    this.productDto.productId = myForm.value.productId;
    this.productDto.productManufacturer = myForm.value.productManufacturer;
    this.productDto.productName = myForm.value.productName;
    this.productDto.productPrice = myForm.value.productPrice;
    this.productDto.productQuantity = myForm.value.productQuantity;
    this.productDto.productSpecification = myForm.value.productSpecification;

    this.productService.addProduct(this.productDto).subscribe((data) => {
      alert("Product Added Successfully!!");
    },
      error => {
        console.log("Errors:\n");
        this.exceptionResponse = error.error;
        console.log(this.exceptionResponse.listOfErrors);
      }
    );

  }
}
