import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { URLEntity } from '../model/URLEntity';

const httpOptions = { headers: new HttpHeaders({
    'Access-Control-Allow-Origin': '*',
    'Access-Control-Allow-Methods' : 'HEAD, GET, POST, PUT, PATCH, DELETE',
    'Access-Control-Allow-Headers' : 'Origin, Content-Type, X-Auth-Token' }),
    responseType: 'text'};


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
    return this.http.post(this.shortenerUrl, urlEntity, httpOptions);
  }
}
