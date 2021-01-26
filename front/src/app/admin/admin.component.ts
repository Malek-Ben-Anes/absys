import { Component, OnInit } from '@angular/core';
import { User } from '@app/models/user.model';
import { MessageService, TreeNode } from 'primeng/api';
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
  files: TreeNode[];

  constructor(
    private userService: UserService,
    private messageService: MessageService,
    private webSocket: WebsocketService
  ) {}

  async ngOnInit() {
    await this.loadGroupedUsersByJob();
    await this.loadWebSocket();
    this.getDataList();
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

  getCountryList = (job) => {
    console.log(job);
    return Object.keys(this.jobAndCountryUsers[job]).map(
      (countryName, index) => ({
        countryName,
        index,
      })
    );
  };

  getUsersList = (job, country) => {
    console.log(job, country, this.jobAndCountryUsers[job][country]);
    return this.jobAndCountryUsers[job][country];
  };

  getDataList = (): TreeNode[] => {
    const { data } = Object.keys(this.jobAndCountryUsers).reduce(
      (res: any, job) => {
        if (!res.data) res.data = [];

        const obj: { data: any; children: any[] } = { data: {}, children: [] };
        obj.data.name = job;

        for (let country of Object.keys(this.jobAndCountryUsers[job])) {
          obj.children.push({
            data: { name: country },
            children: this.jobAndCountryUsers[job][country].map((user) => ({
              data: user,
            })),
          });
        }

        res.data.push(obj);
        return res;
      },
      {}
    );
    console.log(data);
    this.files = data;
    return data as TreeNode[];
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
