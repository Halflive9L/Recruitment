import { Component } from '@angular/core';
import {IApplicant} from "./applicants";
import {ApplicantsService} from "./applicants.service";
import {FormGroup, FormBuilder, Validators} from "@angular/forms";
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

  constructor(private _applicant: ApplicantsService, private _prospects: ProspectsService,
              private formBuilder: FormBuilder) {
  }

  applicantForm:  FormGroup;

  ngOnInit() {
    this.applicantForm = this.formBuilder.group({
      firstName: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(50)]],
      lastName: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(50)]],
      email: ['',[Validators.required, Validators.pattern("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+" +
        "@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])" +
        "|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$")]],
      phone: ['',[Validators.required,Validators.pattern("\\+(9[976]\\d|8[987530]\\d|6[987]\\d|5[90]\\d|42\\d|" +
        "3[875]\\d|2[98654321]\\d|9[8543210]|8[6421]|6[6543210]|5[87654321]|" +
        "4[987654310]|3[9643210]|2[70]|7|1)\\d{1,14}")]],
      address: '',
      dateOfBirth: '',
      education: ''
    });

    this._applicant.getApplicants()
      .subscribe(iapplicants => this.iapplicants = iapplicants);
    this._prospects.getProspects()
      .subscribe(iprospects => this.iprospects = iprospects);
  };



  addApplicant(form: FormGroup) : void {
    let body = JSON.stringify(form.value);
    this._applicant.createApplicant(body)
      .subscribe(iapplicant => {
        iapplicant[0].applicantId = this.iapplicants.length+1;
        this.iapplicants.push(iapplicant[0])
      });
  };

  addProspect(form: FormGroup) : void {
    let body = JSON.stringify(form.value);
    this._prospects.createProspect(body)
      .subscribe(iprospect => {
        iprospect[0].prospectId = this.iprospects.length+1;
        this.iprospects.push(iprospect[0])
      });
  };

}

