import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { URLConverterService } from './service/url-service';
import {URLEntity} from './model/URLEntity';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  urlEntity: URLEntity;
  title: string;
  isShorted = false;

  constructor(private route: ActivatedRoute, private router: Router, private urlConverterService: URLConverterService) {
    this.title = 'Pocki.to';
    this.urlEntity = new URLEntity();
  }
  
  onShort() {
    this.isShorted = true;
    this.urlConverterService.shortURL(this.urlEntity).subscribe(
      (response) => this.urlEntity.shortId = response);
  }

  onCopy() {
    // this.isShorted = true;
    // this.urlConverterService.shortURL(this.urlEntity).subscribe(
    //   (response)=> console.log(response));
  }
  gotoUserList() {
    this.router.navigate(['/shortener']);
  }
}
