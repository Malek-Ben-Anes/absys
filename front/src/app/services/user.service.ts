import { Injectable } from '@angular/core';
import { ApiService } from '@app/services/api.service';
import { User } from '@app/models/user.model';
import { AuthService } from '@app/services/auth.service';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private readonly USER_URL = 'users';

  constructor(
    private readonly apiService: ApiService,
    private readonly authService: AuthService
  ) {}

  public async register(user: User): Promise<User> {
    const newUser = await this.apiService.post(this.USER_URL, user);
    this.authService.currentUser = newUser;
    return newUser;
  }

  async findAll(): Promise<User[]> {
    return this.apiService.get(this.USER_URL);
  }

  async workflow(userId: string): Promise<User[]> {
    return this.apiService.patch(`${this.USER_URL}/${userId}/workflow/`);
  }
}
