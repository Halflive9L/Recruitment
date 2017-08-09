import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule} from "@angular/http"
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {prospectComponent} from "./prospect/prospect.component";
import {applicantComponent} from "./applicant/applicant.component";

@NgModule({
  declarations: [
    applicantComponent, prospectComponent
  ],
  imports: [
    BrowserModule, HttpModule, FormsModule, ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [applicantComponent, prospectComponent]
})
export class AppModule { }
