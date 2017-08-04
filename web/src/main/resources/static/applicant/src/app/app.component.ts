import {Component, ViewChild, ElementRef} from '@angular/core';
import {IApplicant} from "./applicants";
import {ApplicantsService} from "./applicants.service";
import {FormGroup, FormBuilder, Validators} from "@angular/forms";
import {ProspectsService} from "./prospects.service";
import {IProspect} from "./prospects";
import {Observable} from "rxjs/Observable";
import {forEach} from "@angular/router/src/utils/collection";


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
  idcounter: number;
  currentId: number;
  applicantForm:  FormGroup;
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

    this.updateApplicantForm = this.formBuilder.group({
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

    this.prospectForm = this.formBuilder.group({
      firstName : ['', [Validators.required, Validators.minLength(2),Validators.maxLength(50)]],
      lastName: ['', [Validators.required,Validators.minLength(2),Validators.maxLength(50)]],
      email: ['',[Validators.required, Validators.pattern("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+" +
        "@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])" +
        "|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$")]],
      phone: ['',[Validators.required,Validators.pattern("\\+(9[976]\\d|8[987530]\\d|6[987]\\d|5[90]\\d|42\\d|" +
        "3[875]\\d|2[98654321]\\d|9[8543210]|8[6421]|6[6543210]|5[87654321]|" +
        "4[987654310]|3[9643210]|2[70]|7|1)\\d{1,14}")]]
    });

    this.updateProspectForm = this.formBuilder.group({
      firstName : ['', [Validators.required, Validators.minLength(2),Validators.maxLength(50)]],
      lastName: ['', [Validators.required,Validators.minLength(2),Validators.maxLength(50)]],
      email: ['',[Validators.required, Validators.pattern("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+" +
        "@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])" +
        "|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$")]],
      phone: ['',[Validators.required,Validators.pattern("\\+(9[976]\\d|8[987530]\\d|6[987]\\d|5[90]\\d|42\\d|" +
        "3[875]\\d|2[98654321]\\d|9[8543210]|8[6421]|6[6543210]|5[87654321]|" +
        "4[987654310]|3[9643210]|2[70]|7|1)\\d{1,14}")]]
    });

    this._applicant.getApplicants()
      .subscribe(iapplicants => this.iapplicants = iapplicants);
    this._prospects.getProspects()
      .subscribe(iprospects => this.iprospects = iprospects);
    this.idcounter = 1;
  };

  addToIdCounter() {
    this.idcounter++;
  }

  getId($event) {
    if(isNaN($event.target.id)) {
      this.currentId = 0;
    } else {
    this.currentId = $event.target.id;
    }
  };

  readApplicantById(id: number) : IApplicant {
    if(isNaN(id)) return null;
    return this.iapplicants[id];
  };

  addApplicant(form: FormGroup) : void {
    let body = JSON.stringify(form.value);
    this._applicant.createApplicant(body)
      .subscribe(iapplicant => {
        if(this.iapplicants.length == 0) {
          iapplicant[0].applicantId = 1;
        } else {
        iapplicant[0].applicantId = this.iapplicants.length+1;
        }
        this.iapplicants.push(iapplicant[0])

      });
  };

  updateApplicant(form: FormGroup) : void {
    let body = JSON.stringify(form.value);
    this._applicant.updateApplicant(body, this.currentId)
      .subscribe(iapplicant => {
          console.log("lenght = " + this.iapplicants.length);
          console.log("id = " + this.currentId)
          let index = this.iapplicants.findIndex(x => x.applicantId === iapplicant[0].applicantId);
        let array1 = this.iapplicants.slice(0, index);
        let array2 = this.iapplicants.slice(index+1, this.iapplicants.length);
        array1.push(iapplicant[0]);
        this.iapplicants = array1.concat(array2);
    });
    };

    deleteApplicant(id: number) : void {
    this._applicant.deleteApplicant(id)
      .subscribe(iapplicant => {
        let index = this.iapplicants.findIndex(x => x.applicantId === iapplicant[0].applicantId);
          let array1 = this.iapplicants.slice(0, index);
          let array2 = this.iapplicants.slice(index+1, this.iapplicants.length);
          this.iapplicants = array1.concat(array2);
    });
    };

  readProspectById(id: number) : IProspect {
    if(isNaN(id)) return null;
    return this.iprospects[id];
  };

  addProspect(form: FormGroup) : void {
    let body = JSON.stringify(form.value);
    this._prospects.createProspect(body)
      .subscribe(iprospect => {
        if(this.iprospects.length == 0) {
          iprospect[0].prospectId = 1;
        } else {
        iprospect[0].prospectId = this.iprospects.length+1;
        }
        this.iprospects.push(iprospect[0])
      });
  };

  updateProspect(form: FormGroup) : void {
    let body = JSON.stringify(form.value);
    this._prospects.updateProspect(body, this.currentId)
      .subscribe(iprospect => {
        let index = this.iprospects.findIndex(x => x.prospectId === iprospect[0].prospectId);
        let array1 = this.iprospects.slice(0, index);
        let array2 = this.iprospects.slice(index+1, this.iprospects.length);
        array1.push(iprospect[0]);
        this.iprospects = array1.concat(array2);
      });
  }

  deleteProspect(id: number) : void {
    this._prospects.deleteProspect(id)
      .subscribe(iprospect => {
        let index = this.iprospects.findIndex(x => x.prospectId === iprospect[0].prospectId);
        let array1 = this.iprospects.slice(0, index);
        let array2 = this.iprospects.slice(index+1, this.iprospects.length);
        this.iprospects = array1.concat(array2);
      });
  };


}

