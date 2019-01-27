import { Component, OnInit } from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd';

import { environment } from '../environments/environment';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  activeKey = 'labs';

  labs = [];
  items = [];

  availableOptions = [
    { text: 'Yes', value: true },
    { text: 'No', value: false },
  ];
  labOptions = [];

  rawItems = [];
  filterAvailable = undefined;
  filterLaboratories = [];

  constructor(private message: NzMessageService) {}

  async ngOnInit() {
    await this.loadLabs();
  }

  async loadLabs() {
    const response = await fetch(`${environment.server}laboratories`);

    if (response.status !== 200) {
      this.message.error(
        'Looks like there was a problem loading the laboratories. Status Code: ' + response.status,
      );
      return;
    }

    const laboratories = await response.json();

    this.labs = laboratories
      .map((lab) => ({
        id: lab.laboratoryID,
        name: lab.name,
        location: lab.location,
      }));

    this.labOptions = [
      ...this.labs.map((lab) => ({
        value: lab.id,
        text: lab.name,
      })),
      {
        value: undefined,
        text: 'N/A',
      }
    ];
  }

  async loadItems() {
    const response = await fetch(`${environment.server}items`);

    if (response.status !== 200) {
      this.message.error(
        'Looks like there was a problem loading the items. Status Code: ' + response.status,
      );
      return;
    }

    const items = await response.json();

    this.items = this.rawItems = items
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

  async changePage(directive) {
    const key = directive.hostElement.nativeElement.getAttribute('key');

    if (key === 'items') {
      this.loadItems();
      this.filterAvailable = undefined;
      this.filterLaboratories = [];
      this.rawItems = [];
    }
    if (key === 'labs') {
      this.loadLabs();
    }

    this.activeKey = key;
  }

  filter(byKey, $event): void {
    console.log(byKey, $event);

    if (byKey === 'laboratory') {
      this.filterLaboratories = $event;
    }
    if (byKey === 'available') {
      this.filterAvailable = $event === null ? undefined : $event;
    }

    this.items = this.rawItems.filter((item) => {
      if (this.filterAvailable !== undefined && item.available !== this.filterAvailable) {
        return false;
      }

      if (this.filterLaboratories.length !== 0 &&
        (!item.laboratory || !this.filterLaboratories.includes(item.laboratory.id)) &&
        !(!item.laboratory && this.filterLaboratories.includes(undefined))
      ) {
        return false;
      }

      return true;
    });
  }
}
