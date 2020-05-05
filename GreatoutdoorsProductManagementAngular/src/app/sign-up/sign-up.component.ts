import { Component, OnInit } from '@angular/core';
import { UserDTO } from '../Model/UserDTO';
import { CurrentLoggedUserService } from '../services/current-logged-user.service';
import { NgForm } from '@angular/forms';
import { UserManagementService } from '../services/user-management.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {

  userDto:UserDTO=new UserDTO();
  hide:boolean=true;
  constructor(public currentUser:CurrentLoggedUserService,private userManagementService:UserManagementService,private _snackBar: MatSnackBar,private router:Router) { }

  ngOnInit(): void {

  }

  signUp(myForm:NgForm)
  {
    this.userManagementService.signUp(this.userDto,this.currentUser?.getCurrentUser()?.userRole).subscribe(
      (data)=>{
      console.log(data);
      if(this.currentUser?.getCurrentUser()?.userRole==1)
       {
        this.openSnackBar("Account Created!\n userName: "+this.userDto.userName+"\n password:"+this.userDto.userPassword,7); 
       }
       else
       {
        this.setCurrentUserInService(); 
        this.openSnackBar("Account Created!",3);
        this.router.navigate(['/signin']);
       }
      
      },
      (error)=>{
       console.log(error.error);
       this.openSnackBar("Some thing wrong",2);
      }
    );
  }


  setCurrentUserInService()
  {
   if(this.currentUser.getCurrentUser()!=null || this.currentUser.getCurrentUser()!=undefined) 
     this.currentUser.setCurrentUser(this.userDto);
  }

  openSnackBar(message: string,time:number) {
    this._snackBar.open(message)._dismissAfter(time * 1000);
  }
}
