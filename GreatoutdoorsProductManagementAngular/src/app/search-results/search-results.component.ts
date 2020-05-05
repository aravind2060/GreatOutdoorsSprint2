import { Component, OnInit } from '@angular/core';
import { QueryResponse } from '../Model/QueryResponse'
import { DataTransferBetweenComponentsService } from '../services/data-transfer-between-components.service';
import { Router } from '@angular/router';
import { ProductServiceService } from '../services/product-service.service';

@Component({
  selector: 'app-search-results',
  templateUrl: './search-results.component.html',
  styleUrls: ['./search-results.component.css']
})
export class SearchResultsComponent implements OnInit {

  queryResponse: QueryResponse;
  currentPage: number = 0;
  noOfPages = [];
  constructor(private dataTransfer: DataTransferBetweenComponentsService, private router: Router, private productService: ProductServiceService) { }

  ngOnInit(): void {

    this.getResults(this.currentPage);
  }

  getResults(pageNumber: number) {

    let searchKeyword = this.dataTransfer.getSearchKeyword();
    this.currentPage = pageNumber;

    if (searchKeyword?.length > 0) {
      this.productService.searchProduct(searchKeyword, this.currentPage).subscribe(
        (data: QueryResponse) => {
          this.queryResponse = data;
          console.log(this.queryResponse);
          for (let i = 0; i < this.queryResponse.totalNoOfPages; i++) {
            this.noOfPages[i] = i;
          }
        },
        (error) => {
          console.log(error.error);
        }
      );
    }
    else {
      this.router.navigate(["/"]);
    }


  }


}
