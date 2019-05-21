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
  isCopied = false;

  constructor(private route: ActivatedRoute, private router: Router, private urlConverterService: URLConverterService) {
    this.title = 'Pocki.to';
    this.urlEntity = new URLEntity();
  }

  onShort() {
    this.isShorted = true;
    this.urlConverterService.shortURL(this.urlEntity).subscribe(
      (response) => this.urlEntity.shortId = response,
      console.log(this.urlEntity.shortId));
  }

  copyToClipboard(item) {
    document.addEventListener('copy', (e: ClipboardEvent) => {
      e.clipboardData.setData('text/plain', (item));
      e.preventDefault();
      document.removeEventListener('copy', null);
    });
    document.execCommand('copy');
    this.isCopied = true;
  }

}
