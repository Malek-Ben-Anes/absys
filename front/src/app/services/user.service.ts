import { Injectable } from '@angular/core';
import { ApiService } from '@app/services/api.service';
import { User } from '@app/models/user.model';
import { AuthService } from '@app/services/auth.service';
import { GroupedUsers } from '@app/models/users-group.model';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private readonly USER_URL = 'users';
  private readonly USER_JOB_COUNTRY_GROUP_URL = 'users/earthJob/country';

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

  async findGroupedAll(): Promise<GroupedUsers> {
    return this.apiService.get(this.USER_JOB_COUNTRY_GROUP_URL);
  }

  async workflow(userId: string): Promise<User[]> {
    return this.apiService.patch(`${this.USER_URL}/${userId}/workflow/`);
  }
}
