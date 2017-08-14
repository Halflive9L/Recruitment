/**
 * Created by Lander on 2/08/2017.
 */
import {Injectable} from '@angular/core';
import {Headers, Http, RequestOptions, Response, ResponseContentType} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';
import {IApplicant} from "./applicants";

@Injectable()
export class ApplicantsService {
  private _applicantUrl = 'http://localhost:9090/api/v1/applicant';

  constructor(private _http: Http) {
  }

  getApplicants(): Observable<IApplicant[]> {
    return this._http.get(this._applicantUrl)
      .map((response: Response) => <IApplicant[]> response.json());
  }

  createApplicant(body: string) {
    let headers = new Headers({'content-type': 'application/json'});
    let options = new RequestOptions({headers: headers});
    return this._http.post(this._applicantUrl, body, options).map((res: Response) => res.json());
  }

  updateApplicant(body: string, id: number) {
    let headers = new Headers({'content-type': 'application/json'});
    let options = new RequestOptions({headers: headers});
    return this._http.put(this._applicantUrl + "/" + id, body, options).map((res: Response) => res.json());
  }

  deleteApplicant(id: number) {
    let headers = new Headers({'content-type': 'application/json'});
    let options = new RequestOptions({headers: headers});
    return this._http.delete(this._applicantUrl + "/" + id, options).map((res: Response) => res.json());
  }

  createApplicantFile(fileList: FileList, id: number) {
    if (fileList.length > 0) {
      let file: File = fileList[0];
      let attachment: FormData = new FormData();
      attachment.append('attachment', file, file.name);
      let headers = new Headers();
      let options = new RequestOptions({headers: headers});
      return this._http.post(this._applicantUrl + "/" + id + "/attachment/", attachment)
        .map(res => res.json());
    }
  }

  readApplicantFileList(applicantId: number) {
    return this._http.get(this._applicantUrl + "/" + applicantId + "/attachment/")
      .map((response: Response) => response.json());
  }

  downloadApplicantFile(fileId: number) {
    window.open("http://localhost:9090/api/v1/attachment/" + fileId);
  };

  deleteApplicantFile(fileId: number) {
    return this._http.delete("http://localhost:9090/api/v1/attachment/" + fileId)
      .map(res => res.json());
  }

  setAllApplicantsTags(applicantId: number, tags: String[]) {
    let headers = new Headers({'content-type': 'application/json'});
    let options = new RequestOptions({headers: headers});
    return this._http.put("http://localhost:9090/api/v1/applicant/"+ applicantId+"/tag", options)
      .map(res => res.json());
  }

}
