import { Component, OnInit } from '@angular/core';
import { ProductServiceService } from '../product-service.service';
import { ProductDTO } from '../Model/ProductDTO';
import { Router, ActivatedRoute } from '@angular/router';
import { DataTransferBetweenComponentsService } from '../data-transfer-between-components.service';

@Component({
  selector: 'app-get-all-product',
  templateUrl: './get-all-product.component.html',
  styleUrls: ['./get-all-product.component.css']
})
export class GetAllProductComponent implements OnInit {

  productsDto;

  constructor(private productService: ProductServiceService, private dataTransfer: DataTransferBetweenComponentsService, private router: Router) {
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
    });
  }

  updateProduct(id: number) {
    this.dataTransfer.setProductId(id);
    this.router.navigate(["/updateproduct"]);
  }

  deleteProduct(id: number) {
    this.dataTransfer.setProductId(id);
    this.router.navigate(["/deleteproduct"]);
  }
}
