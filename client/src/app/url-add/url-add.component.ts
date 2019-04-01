import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { UrlService } from '../shared/url/url.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-url-add',
  templateUrl: './url-add.component.html',
  styleUrls: ['./url-add.component.css']
})
export class UrlAddComponent implements OnInit, OnDestroy {
  url: any = {};

  sub: Subscription;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private urlService: UrlService) {
  }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      const id = params['id'];
      if (id) {
        this.urlService.get(id).subscribe((url: any) => {
          if (url) {
            this.url = url;
            this.url.href = url._links.self.href;
          } else {
            console.log(`url with id '${id}' not found, returning to list`);
            this.gotoList();
          }
        });
      }
    });
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }

  gotoList() {
    this.router.navigate(['/url-add']);
  }

  save(form: NgForm) {
    this.urlService.save(form).subscribe(result => {
      this.gotoList();
    }, error => console.error(error));
  }

  remove(href) {
    this.urlService.remove(href).subscribe(result => {
      this.gotoList();
    }, error => console.error(error));
  }
}
