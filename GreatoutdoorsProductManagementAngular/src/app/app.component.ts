import { Component } from '@angular/core';
import { DataTransferBetweenComponentsService } from './services/data-transfer-between-components.service';
import { Router } from '@angular/router';
import { CurrentLoggedUserService } from './services/current-logged-user.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'GreatoutdoorsProductManagementAngular';
  search: string;

  constructor(private dataTransferService: DataTransferBetweenComponentsService, private router: Router, public currentUser: CurrentLoggedUserService) {

  }

  searchProduct() {

    if (this.search?.toString().trim().length > 0) {
      this.dataTransferService.setSearchKeyword(this.search);
      this.search = "";
      this.router.navigate(["/searchresultsforproducts"]);
    }

  }

  logout() {
    this.currentUser.setCurrentUser(null);
  }
}
