import { Injectable } from '@angular/core';
import {ApiService} from "./api.service";
import {User} from "../model/user.model";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  readonly USER_URL = 'users';

  private currentUser: User;
  constructor(
    private apiService: ApiService
  ) { }

  public async login(id: string): Promise<User> {
      const user = await this.apiService.post('/login', id);
      this.currentUser = user;
      return user;
  }
}
