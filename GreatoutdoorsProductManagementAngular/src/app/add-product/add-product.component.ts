import { Component, OnInit } from '@angular/core';
import { ProductServiceService } from '../product-service.service';
import { NgForm } from '@angular/forms';
import { ProductDTO } from '../Model/ProductDTO';
import { ExceptionResponse } from '../Model/ExceptionResponse';
import { strict } from 'assert';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { MatSnackBar } from '@angular/material/snack-bar';


@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent implements OnInit {

  productDto: ProductDTO = new ProductDTO();
  exceptionResponse: ExceptionResponse = new ExceptionResponse();
  listOfErrors: Map<string, Map<string, string>>;

  constructor(private productService: ProductServiceService, private _snackBar: MatSnackBar) { }

  ngOnInit(): void {

  }

  addProduct(myForm: NgForm) {

    this.productService.addProduct(101, this.productDto).subscribe(
      (data) => {
        console.log("inside success!");
        this.openSnackBar("Product Added Successfully!");
        console.log(data);
        myForm.resetForm();
      },
      (error: ExceptionResponse) => {
        console.log("Error Block");
        if (error == undefined) {
          this.openSnackBar("Product Added Successfully");
          myForm.resetForm();
        }
        else {
          if (error.listOfErrors == undefined) {
            this.openSnackBar("product Added Successfully");
            myForm.resetForm();
          }
          else {
            console.log(error.listOfErrors);
            this.openSnackBar("Some thing wrong!");
          }
        }

      }
    );
    //this.calling();
  }



  openSnackBar(message: string) {
    this._snackBar.open(message)._dismissAfter(3 * 1000);
  }






}
