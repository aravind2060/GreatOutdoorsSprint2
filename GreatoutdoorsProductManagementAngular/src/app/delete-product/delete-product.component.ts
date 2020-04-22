import { Component, OnInit } from '@angular/core';
import { ProductServiceService } from '../product-service.service';
import { Router } from '@angular/router';
import { DataTransferBetweenComponentsService } from '../data-transfer-between-components.service';
import { ProductDTO } from '../Model/ProductDTO';

@Component({
  selector: 'app-delete-product',
  templateUrl: './delete-product.component.html',
  styleUrls: ['./delete-product.component.css']
})
export class DeleteProductComponent implements OnInit {

  id: number;
  productDto;
  constructor(private productService: ProductServiceService, private router: Router, private datatransfer: DataTransferBetweenComponentsService) {

    this.id = this.datatransfer.getProductId();
    this.loadProduct();
  }

  ngOnInit(): void {

  }

  loadProduct() {
    if (this.id > 0) {
      this.productService.getProductById(this.id).subscribe((data) => {
        this.productDto = data;
      }, error => {
        console.log(error.error);
      });
    }
    else {
      this.router.navigate(["/viewproductsofproductmaster"]);
    }
  }

  deleteProduct() {
    this.productService.deleteProduct(this.id).subscribe((data) => {

      alert("product Deleted Successfully!");
      this.router.navigate(["/viewproductsofproductmaster"]);
    }, error => {
      console.log(error.error);
      this.router.navigate(["/viewproductsofproductmaster"]);
    });
  }


}
