import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserDTO } from '../Model/UserDTO';

@Injectable({
  providedIn: 'root'
})
export class UserManagementService {

  url:string="http://localhost:8084/user/";
  
  constructor(private httpClient:HttpClient) { }

  signUp(userDto:UserDTO,adminUserId?:number)
  {
   if(adminUserId==undefined)
      return this.httpClient.post(this.url+"signup/0",userDto);
   else  
      return this.httpClient.post(this.url+"signup/"+adminUserId,UserDTO);
  }
  signIn(userName:string,userPassword:string)
  {
   return this.httpClient.get(this.url+"signin/"+userName+"/"+userPassword);
  }
}
