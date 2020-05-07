import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { UserDTO } from '../Model/UserDTO';
import { CurrentLoggedUserService } from '../services/current-logged-user.service';
import { Router } from '@angular/router';
import { UserManagementService } from '../services/user-management.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css']
})
export class SignInComponent implements OnInit {

  userDto: UserDTO = new UserDTO();
  hide: boolean = false;
  constructor(private currentUser: CurrentLoggedUserService, private router: Router, private userService: UserManagementService,private _snackBar: MatSnackBar) { }

  ngOnInit(): void {
  }

  signIn(myForm: NgForm) {
    this.userService.signIn(this.userDto.userName, this.userDto.userPassword).subscribe(
      (data:UserDTO) => 
      {
        this.openSnackBar("Welcome !",2); 
       this.setDataToCurrentUser(data); 
      this.router.navigate(['/displayproductsforuser']);     
     
      },
      (error) => {
        this.openSnackBar("Some thing went wrong check username and password",3);
      });
  }

 
  setDataToCurrentUser(currentUser:UserDTO)
  {
     this.currentUser.setCurrentUser(currentUser);
  }

  openSnackBar(message: string,time:number) {
    this._snackBar.open(message)._dismissAfter(time * 1000);
  }

}
