import { Component } from '@angular/core';
import { User } from '@app/models/user.model';
import { Router } from '@angular/router';
import { UserService } from '@app/services/user.service';
import { MessageFactory } from '@app/services/message.factory';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent {
  user = new User();

  constructor(
    private userService: UserService,
    private messageFactory: MessageFactory,
    private route: Router
  ) {}

  async onSubmit() {
    try {
      await this.userService.register(this.user);
      this.messageFactory.sendSuccessMessage(
        'Registration',
        'Your account has been created'
      );
      this.route.navigate(['/login']);
    } catch (e) {
      this.messageFactory.sendFailureMessage(
        'Registration',
        'Unable to create your account, please contact support'
      );
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
