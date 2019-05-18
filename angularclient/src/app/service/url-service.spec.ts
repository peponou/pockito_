import { TestBed, inject } from '@angular/core/testing';

import { URLConverterService } from './url-service';

describe('URLConverterService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [URLConverterService]
    });
  });

  it('should be created', inject([URLConverterService], (service: URLConverterService) => {
    expect(service).toBeTruthy();
  }));
});
