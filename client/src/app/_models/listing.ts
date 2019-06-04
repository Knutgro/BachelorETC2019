import {Vehicle} from './vehicle';
import {Company} from './company';

export class Listing {
  id: number;
  company: Company;
  title: string;
  underTitle: string;
  description: string;
  urlText: string;
  keywords: string;
  registrationTaxIncluded: boolean;
  registrationExemption: boolean;
  vatIncluded: boolean;
  totalPrice: number;
  netPrice: number;
  registration: string;
  currency: string;
  position: string;
  noOfOwners: number;
  carCondition: string;
  dateCreated: string;
  dateExpired: string;
  vehicle: Vehicle;
}
