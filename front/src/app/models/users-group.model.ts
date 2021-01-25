import { User } from './user.model';

export interface GroupedUsers {
  jobs: JobAndCountry;
}

export type JobAndCountry = Record<string, Record<string, User[]>>;
