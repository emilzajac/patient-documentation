import {UserMT} from "./user";
import {PatientInterface} from "./patient-interface";

export interface DocumentationFormInterface {

  id: number

  interview: string;

  physicalExamination: string;

  diagnosisOfTheDisease: string;

  recommendations: string;

  medicines: string;

  creationDate: Date;

  user: UserMT;

  patient: PatientInterface;

}

export class Documentation implements DocumentationFormInterface {
  id: number;
  diagnosisOfTheDisease: string;
  interview: string;
  medicines: string;
  physicalExamination: string;
  recommendations: string;
  creationDate: Date;
  patient: PatientInterface;
  user: UserMT;
}
