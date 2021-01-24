import { Status } from '@app/models/user-status.model';

export class UserGroup {
  id?: string;
  firstName: string;
  lastName: string;
  birthDate: Date;
  earthCountry: string;
  earthJob: string;
  status?: Status;
}
