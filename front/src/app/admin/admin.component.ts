import { Component, OnInit } from '@angular/core';
import { User } from '@app/models/user.model';
import { MessageService } from 'primeng/api';
import { UserService } from '@app/services/user.service';
import { WebsocketService } from '@app/services/websocket.service';
import { Status } from '@app/models/user-status.model';
import { GroupedUsers } from '@app/models/users-group.model';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css'],
})
export class AdminComponent implements OnInit {
  users: User[];
  groupedUsers: GroupedUsers;

  constructor(
    private userService: UserService,
    private messageService: MessageService,
    private webSocket: WebsocketService
  ) {}

  ngOnInit() {
    this.loadUsers();
    this.loadGroupedUsers();
    this.loadWebSocket();
  }

  async loadUsers() {
    this.users = await this.userService.findAll();
  }

  async loadGroupedUsers() {
    this.groupedUsers = await this.userService.findGroupedAll();
    console.log(this.groupedUsers);
  }

  async loadWebSocket() {
    await this.webSocket.connect();
    // update user on state change
    this.webSocket.subscribe('/workflow/states', (user: User) => {
      this.saveUser(user);
      //this.loadUsers();
    });
  }

  /**
   * Save user consists:
   *    - Adding a new user to the list if it does not exists
   *    - Updating existing user in the list
   */
  private saveUser(user: User): void {
    const index = this.users.findIndex((u) => u && user.id === u.id);
    if (index > -1) {
      this.users[index] = user;
    } else {
      this.users.push(user);
    }
  }

  async onApprove(userId: string) {
    try {
      await this.userService.workflow(userId);
      this.messageService.add({
        severity: 'success',
        summary: 'Workflow',
        detail: 'User has been updated',
      });
    } catch (e) {
      this.messageService.add({
        severity: 'error',
        summary: 'Workflow',
        detail: 'Unable to update user',
      });
    }
  }

  /**
   *  Check whether workflow has been lanched for a given user or not.
   */
  isApproved(status: Status): boolean {
    return status === Status.DONE || status === Status.REFUSED;
  }
}
