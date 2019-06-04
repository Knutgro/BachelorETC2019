import {TypeData} from './type-data';
import {VehicleImage} from './vehicleImage';
import {Company} from './company';
export class Vehicle{
  id: number;
  nick: string;
  firstRegistration: string;
  registrationClass: string;
  regNo: string;
  warranty: string;
  warrantyDuration: string;
  warrantyDistance: number;
  warrantyUrl: string;
  carPremium: string;
  carPremiumLink: string;
  exteriorColorMain: string;
  exteriorColor: string;
  interiorColor: string;
  interiorType: string;
  tires: string;
  maxFuel: number;
  chassisId: string;
  maxKmTank: number;
  mileage: number;
  serviceHistory: boolean;
  servicePlanFollowed: boolean;
  financePartner: string;
  insurancePartner: string;
  vehicleImages: VehicleImage[];
  thumbnail: string;
  company: Company;
  typeData: TypeData;
}
