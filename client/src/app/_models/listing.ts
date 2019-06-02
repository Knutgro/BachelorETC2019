import {Vehicle} from './vehicle';

export class Listing {
  id: number;
  title: string;
  underTitle: String;
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
