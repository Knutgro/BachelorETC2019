export class User {
  id: number;
  username: string;
  password: string;
  firstName: string;
  lastName: string;
  email: string;
  token: string;
  image: Blob;
  company_id: number;
  role: string[];
  authorities: string[];
  user: any;
}
