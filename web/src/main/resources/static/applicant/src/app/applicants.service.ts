/**
 * Created by Lander on 2/08/2017.
 */
import { Injectable } from '@angular/core';
import { Http , Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';
import { IApplicant} from "./applicants";

@Injectable()
export class ApplicantsService {
  private _applicanturl='http://localhost:9090/applicant';
  constructor(private _http: Http){}

  getapplicants(): Observable<IApplicant[]> {
    return this._http.get(this._applicanturl)
      .map((response: Response) => <IApplicant[]> response.json())
      .do(data => console.log(JSON.stringify(data)));
  }
}
