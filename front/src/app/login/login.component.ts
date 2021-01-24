import { Component, OnInit } from '@angular/core';
import { User } from '@app/models/user.model';
import { MessageService } from 'primeng/api';
import { WebsocketService } from '@app/services/websocket.service';
import { AuthService } from '@app/services/auth.service';
import { Status } from '@app/models/user-status.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  public readonly Status = Status;
  user: User;

  constructor(
    private authService: AuthService,
    private messageService: MessageService,
    private webSocket: WebsocketService
  ) {}

  ngOnInit() {
    // load the current user is defined
    const user = this.authService.currentUser;
    if (user) {
      this.user = user;
      this.loadWebSocket();
    }
  }

  async loadWebSocket() {
    await this.webSocket.connect();
    // update user on state change
    this.webSocket.subscribe('/workflow/states', (user) => {
      if (user.id === this.user.id) {
        this.user = user;
      }
    });
  }

  async login() {
    // if user has successfully registered and loggedIn then no need to login a second time.
    if (this.user && this.user.id) {
      return;
    }
    try {
      this.user = await this.authService.login(this.user.id);
      this.messageService.add({
        severity: 'success',
        summary: 'Login',
        detail: 'You have been logged',
      });
      await this.loadWebSocket();
    } catch (e) {
      this.messageService.add({
        severity: 'error',
        summary: 'Login',
        detail: 'Unable to login you',
      });
    }
  }
}
