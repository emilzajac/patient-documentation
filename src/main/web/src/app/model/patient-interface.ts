import { GenderEnum } from "./gender.enum";

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
  gender: GenderEnum;
  dateOfBirth: Date;
}
