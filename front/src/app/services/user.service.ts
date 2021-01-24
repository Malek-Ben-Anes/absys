import { Injectable } from '@angular/core';
import {ApiService} from "./api.service";
import {User} from "../model/user.model";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  readonly USER_URL = 'users';
  readonly WORKFLOW_USER_URL = 'users/workflow/';

  private currentUser: User;
  constructor(
    private apiService: ApiService
  ) { }

  public async login(id: string): Promise<User> {
      const user = await this.apiService.post('/login', id);
      this.currentUser = user;
      return user;
  }

  public getCurrentUser() {
    return this.currentUser;
  }

  public async register(user: User): Promise<User> {
    const newUser = await this.apiService.post(this.USER_URL, user);
    this.currentUser = newUser;
    return newUser;
  }

  async findAll() {
    return this.apiService.get(this.USER_URL);
  }

  async workflow(id: any) {
    return this.apiService.patch(this.WORKFLOW_USER_URL + id);
  }
}
