import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({providedIn: 'root'})
export class UrlService {
  public API = '//localhost:8080';
  public URL_API = this.API + '/urls';

  constructor(private http: HttpClient) {
  }

  getAll(): Observable<any> {
    return this.http.get(this.API + '/cool-urls');
  }

  get(id: string) {
    return this.http.get(this.URL_API + '/' + id);
  }

  save(url: any): Observable<any> {
    let result: Observable<Object>;
    if (url['href']) {
      result = this.http.put(url.href, url);
    } else {
      result = this.http.post(this.URL_API, url);
    }
    return result;
  }

  remove(href: string) {
    return this.http.delete(href);
  }
}
