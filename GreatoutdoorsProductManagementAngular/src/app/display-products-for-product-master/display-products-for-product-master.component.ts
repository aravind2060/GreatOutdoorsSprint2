import { Component, OnInit, ViewChild } from '@angular/core';
import { ProductServiceService } from '../product-service.service';
import { ProductDTO } from '../Model/ProductDTO';
import { Router, ActivatedRoute } from '@angular/router';
import { DataTransferBetweenComponentsService } from '../data-transfer-between-components.service';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DialogBoxForConfirmationsComponent } from '../dialog-box-for-confirmations/dialog-box-for-confirmations.component';
import { QueryResponse } from '../Model/QueryResponse';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'DisplayProductsForProductMaster',
  templateUrl: './display-products-for-product-master.component.html',
  styleUrls: ['./display-products-for-product-master.component.css']
})
export class DisplayProductsForProductMasterComponent implements OnInit {

  queryResponse: QueryResponse;
  currentPage = 0;
  dataSource;
  @ViewChild(MatSort, { static: true }) sort: MatSort;

  displayedColumns: string[] = ['productId', 'productName', 'productBrand', 'productPrice', 'productCategory', 'productSpecification', 'productManufacturer', 'productDimension', 'productQuantity', 'productColour'];

  constructor(private productService: ProductServiceService, private dataTransfer: DataTransferBetweenComponentsService, private router: Router, public dialog: MatDialog, private _snackBar: MatSnackBar) {

  }

  ngOnInit(): void {
    this.getAllProductsOfProductMaster();

    this.dataSource = new MatTableDataSource(this.queryResponse?.list);
    this.dataSource.sort = this.sort;
  }


  getAllProductsOfProductMaster() {
    this.productService.getProductsWhichBelongsToParticularProductMaster(101, this.currentPage).subscribe(
      (data: QueryResponse) => {

        this.queryResponse = data;
        console.log(this.queryResponse.list);
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
    this.productService.deleteProduct(101, productId).subscribe((data) => {
      console.log("Product Deleted Successfully");
      this.openSnackBar("Product Deleted Successfully");
      this.getAllProductsOfProductMaster();
    }, error => {
      console.log(error.error);
      this.openSnackBar("Some thing Wrong!");
      this.getAllProductsOfProductMaster();
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


}
