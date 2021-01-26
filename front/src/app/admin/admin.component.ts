import { Component, OnInit } from '@angular/core';
import { User } from '@app/models/user.model';
import { MessageService } from 'primeng/api';
import { UserService } from '@app/services/user.service';
import { WebsocketService } from '@app/services/websocket.service';
import { Status } from '@app/models/user-status.model';
import { JobAndCountry } from '@app/models/users-group.model';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css'],
})
export class AdminComponent implements OnInit {
  users: User[];
  jobAndCountryUsers: JobAndCountry;
  rowGroupMetadata: any;

  constructor(
    private userService: UserService,
    private messageService: MessageService,
    private webSocket: WebsocketService
  ) {}

  ngOnInit() {
    this.loadGroupedUsersByJob();
    this.loadWebSocket();
  }

  async loadGroupedUsersByJob() {
    this.jobAndCountryUsers = (await this.userService.findGroupedAll())
      .jobs as JobAndCountry;
  }

  async loadWebSocket() {
    await this.webSocket.connect();
    // update user on state change
    this.webSocket.subscribe('/workflow/states', (user: User) => {
      if (user) {
        this.appendUser(user);
      }
    });
  }

  getJobList = () =>
    Object.keys(this.jobAndCountryUsers).map((jobName, index) => ({
      jobName,
      index,
    }));

  getCountryList = (job) =>
    Object.keys(this.jobAndCountryUsers[job]).map((countryName, index) => ({
      countryName,
      index,
    }));

  getUsersList = (job, country) => {
    console.log(job, country, this.jobAndCountryUsers[job][country]);
    return this.jobAndCountryUsers[job][country];
  };

  /**
   * Save user in existing structure to the current state:
   *    - Adding a new user to the list if it does not exists
   *    - Updating existing user in the list
   */
  private appendUser(user: User): void {
    console.log('');
    const { earthJob, earthCountry } = user;
    const jobX = this.jobAndCountryUsers[earthJob];
    if (jobX) {
      const countryX = jobX[earthCountry];
      if (countryX) {
        const index = countryX.findIndex((u) => u.id === user.id);
        index > -1 ? (countryX[index] = user) : countryX.push(user);
      } else {
        jobX[earthCountry] = [user];
      }
    } else {
      this.jobAndCountryUsers[earthJob] = { [earthCountry]: [user] };
    }
  }

  async onApprove(userId: string) {
    console.log(userId);
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
