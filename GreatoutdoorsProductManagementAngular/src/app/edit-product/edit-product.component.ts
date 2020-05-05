import { Component, OnInit } from '@angular/core';
import { ProductServiceService } from '../services/product-service.service';
import { DataTransferBetweenComponentsService } from '../services/data-transfer-between-components.service';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CurrentLoggedUserService } from '../services/current-logged-user.service';
import { ProductDTO } from '../Model/ProductDTO'

@Component({
  selector: 'app-edit-product',
  templateUrl: './edit-product.component.html',
  styleUrls: ['./edit-product.component.css']
})
export class EditProductComponent implements OnInit {

  productDto: ProductDTO = new ProductDTO();
  idToEdit: number;

  constructor(private productService: ProductServiceService, private dataTransfer: DataTransferBetweenComponentsService, private router: Router, private _snackBar: MatSnackBar, private currentUser: CurrentLoggedUserService) {
  }

  ngOnInit(): void {
    if (this.currentUser.getCurrentUser()?.userRole != 2)
      this.router.navigate(['/']);
    else {
      this.idToEdit = this.dataTransfer.getProductId();
      this.getProduct();
    }
  }

  getProduct() {
    if (this.idToEdit > 0) {
      this.productService.getProductById(this.idToEdit).subscribe(
        (data: ProductDTO) => {
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
    this.productService.updateProduct(this.currentUser.getCurrentUser().userId, this.productDto).subscribe(
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
