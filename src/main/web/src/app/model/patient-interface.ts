import {GenderEnum} from "./gender.enum";
import {User} from "./user";

export interface PatientInterface {

  id: number;

  firstName: string;

  lastName: string;

  pesel: string;

  city: string;

  street: string;

  houseNumber: string;

  postCode: string;

  gender: GenderEnum;

  dateOfBirth: Date;

  phoneNumber: string;

  doctor: User;

}

export class Patient implements PatientInterface {
  id: number;
  firstName: string;
  lastName: string;
  pesel: string;
  city: string;
  street: string;
  houseNumber: string;
  postCode: string;
  phoneNumber: string;
  gender: GenderEnum;
  dateOfBirth: Date;
  doctor: User;
}
