import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule} from "@angular/http"
import {AppComponent} from "./app.component";
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {prospectComponent} from "./prospect/prospect.component";

@NgModule({
  declarations: [
    AppComponent, prospectComponent
  ],
  imports: [
    BrowserModule, HttpModule, FormsModule, ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent, prospectComponent]
})
export class AppModule { }
