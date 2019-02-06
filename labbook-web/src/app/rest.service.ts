import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { environment } from 'src/environments/environment';
import Laboratory from './laboratory';
import Item from './item';

@Injectable({
  providedIn: 'root'
})
export class RestService {

  constructor(private http: HttpClient) { }

  public async getLaboratories(): Promise<Laboratory[]> {
    const laboratories =
      (await this.http.get(`${environment.server}laboratories`).toPromise()) as any[];

    return laboratories
      .map((lab) => ({
        id: lab.laboratoryID,
        name: lab.name,
        location: lab.location,
      }));
  }

  public async getItems(): Promise<Item[]> {
    const items =
      (await this.http.get(`${environment.server}items`).toPromise()) as any[];

    return items
      .map((item) => ({
        id: item.itemID,
        name: item.name,
        quantity: item.quantity,
        available: item.available,
        laboratory: item.laboratory ? {
          id: item.laboratory.laboratoryID,
          name: item.laboratory.name,
          location: item.laboratory.location,
        } : undefined,
      }));
  }
}
