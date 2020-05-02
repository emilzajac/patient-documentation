export interface DocumentationFormInterface {

  id: number

  interview: string;

  physicalExamination: string;

  diagnosisOfTheDisease: string;

  recommendations: string;

  medicines: string;

  creationDate: Date;

}

export class Documentation implements DocumentationFormInterface {
  id: number;
  diagnosisOfTheDisease: string;
  interview: string;
  medicines: string;
  physicalExamination: string;
  recommendations: string;
  creationDate: Date;
}
