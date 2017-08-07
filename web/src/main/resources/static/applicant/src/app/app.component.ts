import {Component} from '@angular/core';
import {IApplicant} from "./applicants";
import {ApplicantsService} from "./applicants.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ProspectsService} from "./prospects.service";
import {IProspect} from "./prospects";
import {forEach} from "@angular/router/src/utils/collection";
import {postApplicantComponent} from "./postApplicant.component";


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [ApplicantsService, ProspectsService]
})

export class AppComponent {
  iapplicants: IApplicant[];
  iapplicant: IApplicant;
  iprospects: IProspect[];
  iprospect: IProspect;
  currentApplicantId: number;
  currentProspectId: number;
  highestApplicantId: number = 0;
  highestProspectId: number = 0;
  applicantForm: FormGroup;
  prospectForm: FormGroup;
  updateApplicantForm: FormGroup;
  updateProspectForm: FormGroup;

  constructor(private _applicant: ApplicantsService, private _prospects: ProspectsService,
              private formBuilder: FormBuilder) {
  }


  ngOnInit() {
    this.applicantForm = this.formBuilder.group({
      firstName: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(50)]],
      lastName: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(50)]],
      email: ['', [Validators.required, Validators.pattern("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+" +
        "@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])" +
        "|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$")]],
      phone: ['', [Validators.required, Validators.pattern("\\+(9[976]\\d|8[987530]\\d|6[987]\\d|5[90]\\d|42\\d|" +
        "3[875]\\d|2[98654321]\\d|9[8543210]|8[6421]|6[6543210]|5[87654321]|" +
        "4[987654310]|3[9643210]|2[70]|7|1)\\d{1,14}")]],
      address: '',
      dateOfBirth: '',
      education: ''
    });

    this.updateApplicantForm = this.formBuilder.group({
      firstName: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(50)]],
      lastName: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(50)]],
      email: ['', [Validators.required, Validators.pattern("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+" +
        "@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])" +
        "|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$")]],
      phone: ['', [Validators.required, Validators.pattern("\\+(9[976]\\d|8[987530]\\d|6[987]\\d|5[90]\\d|42\\d|" +
        "3[875]\\d|2[98654321]\\d|9[8543210]|8[6421]|6[6543210]|5[87654321]|" +
        "4[987654310]|3[9643210]|2[70]|7|1)\\d{1,14}")]],
      address: '',
      dateOfBirth: '',
      education: ''
    });

    this.prospectForm = this.formBuilder.group({
      firstName: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(50)]],
      lastName: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(50)]],
      email: ['', [Validators.required, Validators.pattern("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+" +
        "@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])" +
        "|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$")]],
      phone: ['', [Validators.required, Validators.pattern("\\+(9[976]\\d|8[987530]\\d|6[987]\\d|5[90]\\d|42\\d|" +
        "3[875]\\d|2[98654321]\\d|9[8543210]|8[6421]|6[6543210]|5[87654321]|" +
        "4[987654310]|3[9643210]|2[70]|7|1)\\d{1,14}")]]
    });

    this.updateProspectForm = this.formBuilder.group({
      firstName: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(50)]],
      lastName: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(50)]],
      email: ['', [Validators.required, Validators.pattern("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+" +
        "@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])" +
        "|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$")]],
      phone: ['', [Validators.required, Validators.pattern("\\+(9[976]\\d|8[987530]\\d|6[987]\\d|5[90]\\d|42\\d|" +
        "3[875]\\d|2[98654321]\\d|9[8543210]|8[6421]|6[6543210]|5[87654321]|" +
        "4[987654310]|3[9643210]|2[70]|7|1)\\d{1,14}")]]
    });

    this._applicant.getApplicants()
      .subscribe(iapplicants => {
        iapplicants.forEach(iapplicant =>  this.highestApplicantId = iapplicant.applicantId);
        this.iapplicants = iapplicants });
    this._prospects.getProspects()
      .subscribe(iprospects => {
        iprospects.forEach(iprospect => this.highestProspectId = iprospect.prospectId);
      this.iprospects = iprospects; });
  };

  getApplicantId($event) {
    if (isNaN($event.target.id)) {
      this.currentApplicantId = 0;
    } else {
      this.currentApplicantId = $event.target.id;
    }
  };

  getProspectId($event) {
    if (isNaN($event.target.id)) {
      this.currentProspectId = 0;
    } else {
      this.currentProspectId = $event.target.id;
    }
  };

  readApplicantById(id: number): IApplicant {
    if (isNaN(id)) return null;
    return this.iapplicants[id];
  };

  addApplicant(form: FormGroup): void {
    let body = JSON.stringify(form.value);
    this._applicant.createApplicant(body)
      .subscribe(iapplicant => {
          iapplicant.applicantId = (this.highestApplicantId + 1);
          this.highestApplicantId++;
        this.iapplicants.push(iapplicant);
      });
  };

  updateApplicant(form: FormGroup): void {
    let body = JSON.stringify(form.value);
    this._applicant.updateApplicant(body, this.currentApplicantId)
      .subscribe(iapplicant => {
        let index = this.iapplicants.findIndex(x => x.applicantId === iapplicant.applicantId);
        let array1 = this.iapplicants.slice(0, index);
        let array2 = this.iapplicants.slice(index + 1, this.iapplicants.length);
        array1.push(iapplicant);
        this.iapplicants = array1.concat(array2);
      });
  };

  deleteApplicant(id: number): void {
    this._applicant.deleteApplicant(id)
      .subscribe(iapplicant => {
        let index = this.iapplicants.findIndex(x => x.applicantId === iapplicant.applicantId);
        let array1 = this.iapplicants.slice(0, index);
        let array2 = this.iapplicants.slice(index + 1, this.iapplicants.length);
        this.iapplicants = array1.concat(array2);
      });
  };

  readProspectById(id: number): IProspect {
    if (isNaN(id)) return null;
    return this.iprospects[id];
  };

  addProspect(form: FormGroup): void {
    let body = JSON.stringify(form.value);
    this._prospects.createProspect(body)
      .subscribe(iprospect => {
          iprospect.prospectId = this.highestProspectId + 1;
          this.highestProspectId++;
        this.iprospects.push(iprospect)
      });
  };

  updateProspect(form: FormGroup): void {
    let body = JSON.stringify(form.value);
    this._prospects.updateProspect(body, this.currentProspectId)
      .subscribe(iprospect => {
        let index = this.iprospects.findIndex(x => x.prospectId === iprospect.prospectId);
        let array1 = this.iprospects.slice(0, index);
        let array2 = this.iprospects.slice(index + 1, this.iprospects.length);
        array1.push(iprospect);
        this.iprospects = array1.concat(array2);
      });
  };

  deleteProspect(id: number): void {
    console.log(id);
    this._prospects.deleteProspect(id)
      .subscribe(iprospect => {
        let index = this.iprospects.findIndex(x => x.prospectId === iprospect.prospectId);
        let array1 = this.iprospects.slice(0, index);
        let array2 = this.iprospects.slice(index + 1, this.iprospects.length);
        this.iprospects = array1.concat(array2);
      });
  };


}

