import { Component } from '@angular/core';
import {IApplicant} from "./applicants";
import {ApplicantsService} from "./applicants.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [ApplicantsService]
})
export   class   ApplicantComponent  {
  iapplicants: IApplicant[];
  constructor(private _applicant: ApplicantsService) {
  }

  ngOnInit() : void {
    this._applicant.getapplicants()
      .subscribe(iapplicants => this.iapplicants = iapplicants);
  }
}

