/**
 * Created by Lander on 2/08/2017.
 */
import { Injectable } from '@angular/core';
import { Http , Response, RequestOptions, Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';
import { IApplicant} from "./applicants";

@Injectable()
export class ApplicantsService {
  private _applicantUrl='http://localhost:9090/applicant';
  constructor(private _http: Http){}

  getApplicants(): Observable<IApplicant[]> {
    return this._http.get(this._applicantUrl)
      .map((response: Response) => <IApplicant[]> response.json());
  }

  createApplicant(body: string) {
    let headers = new Headers({ 'content-type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    return this._http.post(this._applicantUrl, body, options ).map((res: Response) => res.json());
  }


}
