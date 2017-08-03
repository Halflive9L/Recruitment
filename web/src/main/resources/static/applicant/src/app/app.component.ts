import { Component } from '@angular/core';
import {IApplicant} from "./applicants";
import {ApplicantsService} from "./applicants.service";
import {FormGroup} from "@angular/forms/forms";


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [ApplicantsService]
})

export   class   AppComponent  {
  iapplicants: IApplicant[];
  iapplicant: IApplicant;

  constructor(private _applicant: ApplicantsService) {
  }
  submit(form: FormGroup) {
    console.log("Template-driven form submitted: ", form);
  }

  addApplicant(form: FormGroup) : void {

    let body = JSON.stringify(form.value);
    this._applicant.createApplicant(body)
      .subscribe(iapplicant => this.iapplicant = iapplicant);
  }

  ngOnInit() : void {
    this._applicant.getapplicants()
      .subscribe(iapplicants => this.iapplicants = iapplicants);
  }




}

