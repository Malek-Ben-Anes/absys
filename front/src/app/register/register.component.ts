import { Component, OnInit } from '@angular/core';
import { User } from '@app/models/user.model';
import { MessageService } from 'primeng/api';
import { Router } from '@angular/router';
import { UserService } from '@app/services/user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent {
  user = new User();

  constructor(
    private userService: UserService,
    private messageService: MessageService,
    private route: Router
  ) {}

  async onSubmit() {
    try {
      await this.userService.register(this.user);
      this.messageService.add({
        severity: 'success',
        summary: 'Registration',
        detail: 'Your account has been created',
      });
      this.route.navigate(['/login']);
    } catch (e) {
      this.messageService.add({
        severity: 'error',
        summary: 'Registration',
        detail: 'Unable to create your account, please contact support',
      });
    }
  }

  onReset() {
    this.user = new User();
  }

  isValid() {
    return (
      this.user.firstName &&
      this.user.lastName &&
      this.user.birthDate &&
      this.user.earthCountry &&
      this.user.earthJob
    );
  }
}
