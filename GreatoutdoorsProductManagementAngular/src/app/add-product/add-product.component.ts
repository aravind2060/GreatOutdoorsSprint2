import { Component, OnInit } from '@angular/core';
import { ProductServiceService } from '../services/product-service.service';
import { NgForm } from '@angular/forms';
import { ProductDTO } from '../Model/ProductDTO'
import { ExceptionResponse } from '../Model/ExceptionResponse'
import { MatSnackBar } from '@angular/material/snack-bar';
import { CurrentLoggedUserService } from '../services/current-logged-user.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent implements OnInit {

  productDto: ProductDTO = new ProductDTO();
  exceptionResponse: ExceptionResponse = new ExceptionResponse();
  listOfErrors: Map<string, Map<string, string>>;

  constructor(private productService: ProductServiceService, private _snackBar: MatSnackBar, private currentUser: CurrentLoggedUserService, private router: Router) { }

  ngOnInit(): void {
    if (this.currentUser.getCurrentUser()?.userRole != 2)
      this.router.navigate(['/']);
  }


  addProduct(myForm: NgForm) {

    this.productService.addProduct(this.currentUser.getCurrentUser().userId, this.productDto).subscribe(
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
