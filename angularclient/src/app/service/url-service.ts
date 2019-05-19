import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Urlentity } from '../model/urlentity';
import { Observable } from 'rxjs/Observable';

const httpOptions = { headers: new HttpHeaders({
    'Access-Control-Allow-Origin': '*',
    'Access-Control-Allow-Methods' : 'HEAD, GET, POST, PUT, PATCH, DELETE',
    'Access-Control-Allow-Headers' : 'Origin, Content-Type, X-Auth-Token' }) };


@Injectable()
export class URLConverterService {

  private shortener: string;

  constructor(private http: HttpClient) {
    this.shortener = 'http://localhost:8080/shortener';
  }

  public findByShortId(): Observable<Urlentity> {
    return this.http.get<Urlentity>(this.shortener);
  }

  public shortURL(originalURL: string) {
    return this.http.post<Urlentity>(this.shortener, originalURL, httpOptions);
  }
}
