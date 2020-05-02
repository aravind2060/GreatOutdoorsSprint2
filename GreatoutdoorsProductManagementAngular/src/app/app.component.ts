import { Component } from '@angular/core';
import { DataTransferBetweenComponentsService } from './data-transfer-between-components.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'GreatoutdoorsProductManagementAngular';
  search: string;

  constructor(private dataTransferService: DataTransferBetweenComponentsService, private router: Router) {

  }

  searchProduct() {

    if (this.search?.toString().trim().length > 0) {

      this.dataTransferService.setSearchKeyword(this.search);
      this.router.navigate(["/searchresultsforproducts"]);
    }

  }
}
