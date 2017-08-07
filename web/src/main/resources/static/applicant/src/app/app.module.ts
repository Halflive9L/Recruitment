import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule} from "@angular/http"
import {AppComponent} from "./app.component";
import {postApplicantComponent} from "./postApplicant.component";
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    postApplicantComponent
  ],
  imports: [
    BrowserModule, HttpModule, FormsModule, ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
