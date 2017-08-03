/**
 * Created by Lander on 3/08/2017.
 */
import { Injectable } from '@angular/core';
import { Http , Response, RequestOptions, Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';
import { IProspect} from "./prospects";

@Injectable()
export class ProspectsService {

  private _prospectUrl='http://localhost:9090/prospect';
  constructor(private _http: Http){}

  getProspects(): Observable<IProspect[]> {
    return this._http.get(this._prospectUrl)
      .map((response: Response) => <IProspect[]> response.json());
  }

  createProspect(body: string) {
    let headers = new Headers({ 'content-type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    return this._http.post(this._prospectUrl, body, options ).map((res: Response) => res.json());
  }


}
