import { Injectable } from '@angular/core';
import { UserDTO } from '../Model/UserDTO'

@Injectable({
  providedIn: 'root'
})
export class CurrentLoggedUserService {

  currentUser: UserDTO;

  constructor() {

  }

  public setCurrentUser(userdto: UserDTO) {
    this.currentUser = userdto;
  }

  public getCurrentUser() {
    return this.currentUser;
  }
}
