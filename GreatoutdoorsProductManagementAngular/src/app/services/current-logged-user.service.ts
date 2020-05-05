import { Injectable } from '@angular/core';
import { UserDTO } from '../Model/UserDTO'

@Injectable({
  providedIn: 'root'
})
export class CurrentLoggedUserService {

  currentUser: UserDTO = new UserDTO();

  constructor() {
    this.currentUser.userId = 101;
    this.currentUser.userRole = 2;
  }

  public setCurrentUser(userdto: UserDTO) {
    this.currentUser = userdto;
  }

  public getCurrentUser() {
    return this.currentUser;
  }
}
