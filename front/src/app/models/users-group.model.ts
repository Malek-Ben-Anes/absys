import { User } from './user.model';

export interface GroupedUsers {
  users: Map<string, Map<string, User[]>>;
}
