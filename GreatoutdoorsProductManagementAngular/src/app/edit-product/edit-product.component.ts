import { Component, OnInit } from '@angular/core';
import { ProductServiceService } from '../product-service.service';
import { DataTransferBetweenComponentsService } from '../data-transfer-between-components.service';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-edit-product',
  templateUrl: './edit-product.component.html',
  styleUrls: ['./edit-product.component.css']
})
export class EditProductComponent implements OnInit {

  productDto;
  idToEdit: number;

  constructor(private productService: ProductServiceService, private dataTransfer: DataTransferBetweenComponentsService, private router: Router, private _snackBar: MatSnackBar) {


  }

  ngOnInit(): void {
    this.idToEdit = this.dataTransfer.getProductId();
    this.getProduct();
  }

  getProduct() {
    if (this.idToEdit > 0) {
      this.productService.getProductById(this.idToEdit).subscribe(
        (data) => {
          this.productDto = data;
          console.log(this.productDto);
        },
        (error) => {
          console.log(error.error);
        }
      );
    }
    else {
      this.router.navigate(["/displayproductsforproductmaster"]);
    }

  }

  updateProduct() {
    this.productService.updateProduct(101, this.productDto).subscribe(
      (data) => {

        this.openSnackBar("Data updated Successfully!")
        this.router.navigate(["/displayproductsforproductmaster"]);
      },
      (error) => {
        console.log(error.error);
        this.openSnackBar("Something wrong!");
        this.router.navigate(["/displayproductsforproductmaster"]);
      });
  }

  openSnackBar(message: string) {

    this._snackBar.open(message)._dismissAfter(3 * 1000);
  }

}
