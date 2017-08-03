import { Component } from '@angular/core';
import {IApplicant} from "./applicants";
import {ApplicantsService} from "./applicants.service";
import {FormGroup} from "@angular/forms/forms";
import {ProspectsService} from "./prospects.service";
import {IProspect} from "./prospects";


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [ApplicantsService, ProspectsService]
})

export   class   AppComponent  {
  iapplicants: IApplicant[];
  iapplicant: IApplicant;
  iprospects: IProspect[];
  iprospect: IProspect;

  constructor(private _applicant: ApplicantsService, private _prospects: ProspectsService) {
  }

  addApplicant(form: FormGroup) : void {
    let body = JSON.stringify(form.value);
    this._applicant.createApplicant(body)
      .subscribe(iapplicant => this.iapplicant = iapplicant);
  }

  addProspect(form: FormGroup) : void {
    let body = JSON.stringify(form.value);
    this._prospects.createProspect(body)
      .subscribe(iprospect => this.iprospect = iprospect);
  };

  ngOnInit() : void {
    this._applicant.getApplicants()
      .subscribe(iapplicants => this.iapplicants = iapplicants);
    this._prospects.getProspects()
      .subscribe(iprospects => this.iprospects = iprospects);
  };
}

