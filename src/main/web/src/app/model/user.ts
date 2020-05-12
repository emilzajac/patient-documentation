export class User {
  id: number;
  username: string;
  password: string;
  token: string;
  tokenType: string;
  firstName: string;
  lastName: string;
  email: string;
  userRoles: [];
}

export class UserMT {
  username: string;
  firstName: string;
  lastName: string;
  email: string;
  password: string;
}
