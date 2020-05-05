import { Component, OnInit, ViewChild } from '@angular/core';
import { ProductServiceService } from '../services/product-service.service';
import { ProductDTO } from '../Model/ProductDTO'
import { Router, ActivatedRoute } from '@angular/router';
import { DataTransferBetweenComponentsService } from '../services/data-transfer-between-components.service';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DialogBoxForConfirmationsComponent } from '../dialog-box-for-confirmations/dialog-box-for-confirmations.component';
import { QueryResponse } from '../Model/QueryResponse'
import { MatSnackBar } from '@angular/material/snack-bar';
import { CurrentLoggedUserService } from '../services/current-logged-user.service';

@Component({
  selector: 'DisplayProductsForProductMaster',
  templateUrl: './display-products-for-product-master.component.html',
  styleUrls: ['./display-products-for-product-master.component.css']
})
export class DisplayProductsForProductMasterComponent implements OnInit {

  queryResponse: QueryResponse;
  currentPage = 0;
  noOfPages = [];
  filter;
  isSortedById: boolean = false;
  isSortedByName: boolean = false;
  isSortedByBrand: boolean = false;
  isSortedByPrice: boolean = false;
  isSortedByCategory: boolean = false;
  isSortedBySpecification: boolean = false;
  isSortedByManufacturer: boolean = false;
  isSortedByDimension: boolean = false;
  isSortedByQuantity: boolean = false;
  isSortedByColour: boolean = false;
  constructor(private productService: ProductServiceService, private dataTransfer: DataTransferBetweenComponentsService, private router: Router, public dialog: MatDialog, private _snackBar: MatSnackBar, private currentUser: CurrentLoggedUserService) {

  }

  ngOnInit(): void {
    if (this.currentUser.getCurrentUser()?.userRole != 2)
      this.router.navigate(['/']);
    else
      this.getAllProductsOfProductMaster(this.currentPage);
  }


  getAllProductsOfProductMaster(pageNumber) {

    this.productService.getProductsWhichBelongsToParticularProductMaster(this.currentUser.getCurrentUser().userId, pageNumber).subscribe(
      (data: QueryResponse) => {
        this.queryResponse = data;
        console.log("Length: " + this.queryResponse.list.length);
        console.log(this.queryResponse.list);
        this.noOfPages = new Array(this.queryResponse.totalNoOfPages);
        this.currentPage = this.queryResponse.currentPageNumber;
      },
      error => {
        console.log(error.error);
      });
  }

  updateProduct(id: number) {
    this.dataTransfer.setProductId(id);
    this.router.navigate(["/updateproduct"]);
  }

  deleteProduct(productId: number) {
    this.productService.deleteProduct(this.currentUser.getCurrentUser().userId, productId).subscribe((data) => {
      console.log("Product Deleted Successfully");
      this.openSnackBar("Product Deleted Successfully");
      if (this.queryResponse.list.length == 1) {
        if (this.currentPage == 0) {
          this.queryResponse = null;
          this.noOfPages = null;
        }
        else {
          this.currentPage -= 1;
          this.getAllProductsOfProductMaster(this.currentPage);
        }
      }
      else {
        this.getAllProductsOfProductMaster(this.currentPage);
      }
    }, error => {
      console.log(error.error);
      this.openSnackBar("Some thing Wrong!");
      this.getAllProductsOfProductMaster(this.currentPage);
    })
  }

  openDialog(id: number): void {
    const dialogRef = this.dialog.open(DialogBoxForConfirmationsComponent, {
      width: '250px',
      height: '200px',
      autoFocus: true,
      disableClose: true,
      closeOnNavigation: true
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result)
        this.deleteProduct(id);
    });
  }

  openSnackBar(message: string) {
    this._snackBar.open(message)._dismissAfter(3 * 1000);
  }


  sortById() {
    if (this.isSortedById)
      this.queryResponse.list.sort((a, b) => (a.productId > b.productId) ? 1 : -1);
    else
      this.queryResponse.list.sort((a, b) => (a.productId > b.productId) ? -1 : 1);
    this.isSortedById = !this.isSortedById;
  }

  sortByName() {
    if (this.isSortedByName)
      this.queryResponse.list.sort((a, b) => (a.productName > b.productName) ? 1 : -1);
    else
      this.queryResponse.list.sort((a, b) => (a.productName > b.productName) ? -1 : 1);
    this.isSortedByName = !this.isSortedByName;
  }
  sortByBrand() {
    if (this.isSortedByBrand)
      this.queryResponse.list.sort((a, b) => (a.productBrand > b.productBrand) ? 1 : -1);
    else
      this.queryResponse.list.sort((a, b) => (a.productBrand > b.productBrand) ? -1 : 1);
    this.isSortedByBrand = !this.isSortedByBrand;
  }

  sortByPrice() {
    if (this.isSortedByPrice)
      this.queryResponse.list.sort((a, b) => (a.productPrice > b.productPrice) ? 1 : -1);
    else
      this.queryResponse.list.sort((a, b) => (a.productPrice > b.productPrice) ? -1 : 1);
    this.isSortedByPrice = !this.isSortedByPrice;
  }
  sortByCategory() {
    if (this.isSortedByCategory)
      this.queryResponse.list.sort((a, b) => (a.productCategory > b.productCategory) ? 1 : -1);
    else
      this.queryResponse.list.sort((a, b) => (a.productCategory > b.productCategory) ? -1 : 1);
    this.isSortedByCategory = !this.isSortedByCategory;
  }

  sortBySpecification() {
    if (this.isSortedBySpecification)
      this.queryResponse.list.sort((a, b) => (a.productSpecification > b.productSpecification) ? 1 : -1);
    else
      this.queryResponse.list.sort((a, b) => (a.productSpecification > b.productSpecification) ? -1 : 1);
    this.isSortedBySpecification = !this.isSortedBySpecification;
  }

  sortByManufacturer() {
    if (this.isSortedByManufacturer)
      this.queryResponse.list.sort((a, b) => (a.productManufacturer > b.productManufacturer) ? 1 : -1);
    else
      this.queryResponse.list.sort((a, b) => (a.productManufacturer > b.productManufacturer) ? -1 : 1);
    this.isSortedByManufacturer = !this.isSortedByManufacturer;
  }
  sortByDimension() {
    if (this.isSortedByDimension)
      this.queryResponse.list.sort((a, b) => (a.productDimension > b.productDimension) ? 1 : -1);
    else
      this.queryResponse.list.sort((a, b) => (a.productDimension > b.productDimension) ? -1 : 1);
    this.isSortedByDimension = !this.isSortedByDimension;
  }

  sortByQuantity() {
    if (this.isSortedByQuantity)
      this.queryResponse.list.sort((a, b) => (a.productQuantity > b.productQuantity) ? 1 : -1);
    else
      this.queryResponse.list.sort((a, b) => (a.productQuantity > b.productQuantity) ? -1 : 1);
    this.isSortedByQuantity = !this.isSortedByQuantity;
  }
  sortByColour() {
    if (this.isSortedByColour)
      this.queryResponse.list.sort((a, b) => (a.productColour > b.productColour) ? 1 : -1);
    else
      this.queryResponse.list.sort((a, b) => (a.productColour > b.productColour) ? -1 : 1);
    this.isSortedByColour = !this.isSortedByColour;
  }
}
