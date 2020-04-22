import { Component, OnInit } from '@angular/core';
import { ProductServiceService } from '../product-service.service';
import { DataTransferBetweenComponentsService } from '../data-transfer-between-components.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-edit-product',
  templateUrl: './edit-product.component.html',
  styleUrls: ['./edit-product.component.css']
})
export class EditProductComponent implements OnInit {

  productDto;
  idToEdit: number;

  constructor(private productService: ProductServiceService, private dataTransfer: DataTransferBetweenComponentsService, private router: Router) {

    this.idToEdit = dataTransfer.getProductId();
    this.getProduct();
  }

  ngOnInit(): void {
  }

  getProduct() {
    if (this.idToEdit > 0) {
      this.productService.getProductById(this.idToEdit).subscribe((data) => {
        this.productDto = data;
        console.log(this.productDto);
      },
        error => {
          console.log(error.error);
        }
      );
    }
    else {
      this.router.navigate(["/viewproductsofproductmaster"]);
    }

  }

  updateProduct() {
    this.productService.updateProduct(this.productDto).subscribe((data) => {
      //TODO change alert to standard pop
      console.log("Data updated successfully!");
      alert("Data updated Successfully");
      this.router.navigate(["/viewproductsofproductmaster"])
    }, error => {
      console.log(error.error);
      this.router.navigate(["/viewproductsofproductmaster"])
    });
  }

}
