import { Component, OnInit } from '@angular/core';
import { ProductServiceService } from '../product-service.service';
import { ProductDTO } from '../Model/ProductDTO';
import { Router, ActivatedRoute } from '@angular/router';
import { DataTransferBetweenComponentsService } from '../data-transfer-between-components.service';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DialogBoxForConfirmationsComponent } from '../dialog-box-for-confirmations/dialog-box-for-confirmations.component';


@Component({
  selector: 'DisplayProductsForProductMaster',
  templateUrl: './display-products-for-product-master.component.html',
  styleUrls: ['./display-products-for-product-master.component.css']
})
export class DisplayProductsForProductMasterComponent implements OnInit {

  productsDto;

  constructor(private productService: ProductServiceService, private dataTransfer: DataTransferBetweenComponentsService, private router: Router, public dialog: MatDialog) {
    this.getAllProductsOfProductMaster();

  }

  ngOnInit(): void {
  }


  getAllProductsOfProductMaster() {
    this.productService.getProductsWhichBelongsToParticularProductMaster(101).subscribe((data) => {

      this.productsDto = data;
      console.log(this.productsDto);
    }, error => {
      console.log(error.error);
      if (error.error == "No Products Found")
        this.productsDto = null;

    });
  }

  updateProduct(id: number) {
    this.dataTransfer.setProductId(id);
    this.router.navigate(["/updateproduct"]);
  }

  deleteProduct(id: number) {
    this.productService.deleteProduct(id).subscribe((data) => {
      console.log("Product Deleted Successfully");
      this.getAllProductsOfProductMaster();
    }, error => {
      console.log(error.error);
      this.getAllProductsOfProductMaster();
    })
  }

  openDialog(id: number): void {
    const dialogRef = this.dialog.open(DialogBoxForConfirmationsComponent, {
      width: '250px',
      height: '600px',
      autoFocus: true,
      disableClose: true,
      closeOnNavigation: true
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result)
        this.deleteProduct(id);
    });
  }

}
