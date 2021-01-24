import { Injectable } from '@angular/core';
import { ApiService } from '@app/services/api.service';
import { User } from '@app/models/user.model';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private readonly LOGIN_URL = '/users/login';

  private _currentUser: User;
  constructor(private readonly apiService: ApiService) {}

  public async login(id: string): Promise<User> {
    const user = await this.apiService.post(this.LOGIN_URL, id);
    this._currentUser = user;
    return user;
  }

  get currentUser() {
    return this._currentUser;
  }

  set currentUser(user: User) {
    this._currentUser = user;
  }
}
