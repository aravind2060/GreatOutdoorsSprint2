import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserDTO } from '../Model/UserDTO';

@Injectable({
  providedIn: 'root'
})
export class UserManagementService {

  url:string="http://localhost:8084/user/";
  constructor(private httpClient:HttpClient) { }

  signUp(userDto:UserDTO,userId?:number)
  {
   if(userId==undefined)
      return this.httpClient.post(this.url+"signup",userDto);
   else  
      return this.httpClient.post(this.url+"signup/"+userId,UserDTO);
  }
  signIn(userName:string,userPassword:string)
  {
   return this.httpClient.get(this.url+"signin/"+userName+"/"+userPassword);
  }
}
