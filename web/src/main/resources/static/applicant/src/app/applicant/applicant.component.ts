import {Component} from '@angular/core';
import {IApplicant} from "./applicants";
import {ApplicantsService} from "./applicants.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {isUndefined} from "util";


@Component({
  selector: 'applicant',
  templateUrl: './applicant.component.html',
  styleUrls: ['./applicant.component.css'],
  providers: [ApplicantsService]
})

export class applicantComponent {
  iapplicants: IApplicant[] = [];
  iapplicant: IApplicant;
  currentApplicantId: number;
  highestApplicantId: number = 0;
  applicantForm: FormGroup;
  updateApplicantForm: FormGroup;
  file: FileList;
  applicantFileList: File;

  constructor(private _applicant: ApplicantsService,
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
      education: '',
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
      education: '',
    });

    this._applicant.getApplicants()
      .subscribe(iapplicants => {
        iapplicants.forEach(iapplicant => this.highestApplicantId = iapplicant.applicantId);
        this.iapplicants = iapplicants;
      });
  };

  readAllApplicantFiles(id: number) {
    this._applicant.readApplicantFileList(id).subscribe(
      file => {
        this.applicantFileList = file;
      });
  }

  onSelectFile(event) {
    this.file = event.srcElement.files;
  }

  getApplicantId($event) {
    if (isNaN($event.target.id)) {
      this.currentApplicantId = 0;
    } else {
      this.currentApplicantId = $event.target.id;
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
        this.iapplicants.push(iapplicant);
        if(!isUndefined(this.file) &&  this.file.length > 0) {
        this._applicant.createApplicantFile(this.file, iapplicant.applicantId).subscribe();
        }
        this.highestApplicantId++;
      });
  };

  downloadApplicantFile(fileId: number) : void {
    this._applicant.downloadApplicantFile(fileId);
  }

  deleteApplicantFile(fileId: number) : void {
    this._applicant.deleteApplicantFile(fileId)
      .subscribe(() => this.readAllApplicantFiles(this.currentApplicantId));
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
        if(!isUndefined(this.file) && this.file.length > 0) {
          this._applicant.createApplicantFile(this.file, iapplicant.applicantId).subscribe();
        }
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

}

