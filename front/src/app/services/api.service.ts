import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private httpClient: HttpClient) { }

  public get(uri: string): Promise<any> {
    return this.httpClient.get<any>(environment.apiUrl + uri).toPromise();
  }

  public post(uri: string, body: any): Promise<any> {
    return this.httpClient.post<any>(environment.apiUrl + uri, body).toPromise();
  }
}
