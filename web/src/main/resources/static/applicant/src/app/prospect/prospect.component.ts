

import {Component} from "@angular/core";
import {ProspectsService} from "./prospects.service";
import {IProspect} from "./prospects";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {applySourceSpanToExpressionIfNeeded} from "@angular/compiler/src/output/output_ast";

@Component({
  selector: 'prospect',
  templateUrl: './prospect.component.html',
  styleUrls: ['./prospect.component.css'],
  providers: [ProspectsService]
})

export class prospectComponent {

  iprospects: IProspect[] = [];
  iprospect: IProspect;
  currentProspectId: number;
  highestProspectId: number = 0;
  prospectForm: FormGroup;
  updateProspectForm: FormGroup;

  constructor(private _prospects: ProspectsService, private formBuilder: FormBuilder) {
  }

  ngOnInit() {
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
    this._prospects.getProspects()
      .subscribe(iprospects => {
        iprospects.forEach(iprospect => this.highestProspectId = iprospect.prospectId);
        this.iprospects = iprospects;
      });
  };

  getProspectId($event) {
    if (isNaN($event.target.id)) {
      this.currentProspectId = 0;
    } else {
      this.currentProspectId = $event.target.id;
    }
  };

  readProspectById(id: number): IProspect {
    if (isNaN(id)) return null;
    return this.iprospects[id];
  };

  addProspect(form: FormGroup): void {
    let body = JSON.stringify(form.value);
    this._prospects.createProspect(body)
      .subscribe(iprospect => {
        iprospect.prospectId = (this.highestProspectId + 1);
        this.iprospects.push(iprospect);
        this.highestProspectId++;
      });
  };


  updateProspect(form: FormGroup): void {
    let body = JSON.stringify(form.value);
    this._prospects.updateProspect(body, this.currentProspectId)
      .subscribe(iprospect => {
        let index = this.iprospects.findIndex(x => x.prospectId === iprospect.prospectId);
        console.log(index);
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
