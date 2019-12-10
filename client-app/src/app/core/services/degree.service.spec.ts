/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { DegreeService } from './degree.service';

describe('Service: Degree', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [DegreeService]
    });
  });

  it('should ...', inject([DegreeService], (service: DegreeService) => {
    expect(service).toBeTruthy();
  }));
});
