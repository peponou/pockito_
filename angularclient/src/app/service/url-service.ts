import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { URLEntity } from '../model/URLEntity';
import { Observable } from 'rxjs/Observable';

const httpOptions = { headers: new HttpHeaders({
    'Access-Control-Allow-Origin': '*',
    'Access-Control-Allow-Methods' : 'HEAD, GET, POST, PUT, PATCH, DELETE',
    'Access-Control-Allow-Headers' : 'Origin, Content-Type, X-Auth-Token' }) };


@Injectable()
export class URLConverterService {

  private shortenerUrl: string;

  constructor(private http: HttpClient) {
    this.shortenerUrl = 'http://localhost:8080/shortener';
  }

  public findByOriginalUrl(urlEntity: URLEntity) {
    return this.http.get<URLEntity>(this.shortenerUrl);
  }

  public shortURL(urlEntity: URLEntity) {
    return this.http.post<URLEntity>(this.shortenerUrl, urlEntity, httpOptions);
  }
}
