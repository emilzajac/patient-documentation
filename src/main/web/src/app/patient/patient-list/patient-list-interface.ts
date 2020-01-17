import {GenderEnum} from "../../model/gender.enum";

export interface PatientListInterface {

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
