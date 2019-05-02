import {Region} from './region';

export class Company {
  id: number;
  name: string;
  orgNr: number;
  phoneNumber: number;
  regions: Region[];
}
