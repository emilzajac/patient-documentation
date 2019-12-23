import {UserMT} from "../model/user";
import {PatientInterface} from "../model/patient-interface";

export interface DocumentationListInterface {

  id: number;

  interview: string;

  physicalExamination: string;

  diagnosisOfTheDisease: string;

  recommendations: string;

  medicines: string;

  creationDate: Date;

  user: UserMT;

  patient: PatientInterface;

}
