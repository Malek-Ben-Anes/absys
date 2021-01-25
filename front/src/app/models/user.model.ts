import { Status } from '@app/models/user-status.model';

export class User {
  id?: string;
  firstName: string;
  lastName: string;
  birthDate: Date;
  earthCountry?: string;
  earthJob?: string;
  status?: Status;
}
