import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule} from "@angular/http"
import {ApplicantComponent} from "./app.component";

@NgModule({
  declarations: [
    ApplicantComponent,
  ],
  imports: [
    BrowserModule, HttpModule
  ],
  providers: [],
  bootstrap: [ApplicantComponent]
})
export class AppModule { }
