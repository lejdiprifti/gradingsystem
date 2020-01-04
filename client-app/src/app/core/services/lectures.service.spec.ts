/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { LecturesService } from './lectures.service';

describe('Service: Lectures', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [LecturesService]
    });
  });

  it('should ...', inject([LecturesService], (service: LecturesService) => {
    expect(service).toBeTruthy();
  }));
});
