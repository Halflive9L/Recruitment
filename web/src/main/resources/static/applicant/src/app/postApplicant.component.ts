import { Component } from '@angular/core';
import {IApplicant} from "./applicants";
import {ApplicantsService} from "./applicants.service";
import {FormGroup} from "@angular/forms/forms";
@Component({
  selector: 'post-applicant',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [ApplicantsService]
})

export   class   postApplicantComponent  {
  iapplicant: IApplicant;
  constructor(private _applicant: ApplicantsService) {
  }
}
