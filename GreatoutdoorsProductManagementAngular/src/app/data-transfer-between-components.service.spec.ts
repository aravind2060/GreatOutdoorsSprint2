import { TestBed } from '@angular/core/testing';

import { DataTransferBetweenComponentsService } from './data-transfer-between-components.service';

describe('DataTransferBetweenComponentsService', () => {
  let service: DataTransferBetweenComponentsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DataTransferBetweenComponentsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
