
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UrlAddComponent } from './url-add.component';

describe('UrlAddComponent', () => {
  let component: UrlAddComponent;
  let fixture: ComponentFixture<UrlAddComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UrlAddComponent ]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UrlAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
