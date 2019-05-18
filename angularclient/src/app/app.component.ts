import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { URLConverterService } from './service/url-service';
import { Urlentity } from './model/urlentity';
import { CommonModule } from '@angular/common'
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
 
  title: string;
  isShorted = false;

  constructor(private route: ActivatedRoute, private router: Router, private urlConverterService: URLConverterService) {
    this.title = 'Pocki.to';
  }
  
  onShort(originalUrl: string) {
    this.isShorted = true;
    this.urlConverterService.shortURL(originalUrl).subscribe(
      (response)=> console.log(response),
      (error) =>console.log(error)
    );
  }

  onCopy(){
    
  }
 
  gotoUserList() {
    this.router.navigate(['/shortener']);
  }
}
