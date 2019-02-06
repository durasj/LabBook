import { Component, OnInit } from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd';

import { RestService } from './rest.service';

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

  constructor(
    private message: NzMessageService,
    private rest: RestService,
  ) {}

  async ngOnInit() {
    await this.loadLabs();
  }

  async loadLabs() {
    try {
      this.labs = await this.rest.getLaboratories();

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
    } catch (e) {
      this.message.error(e.message || e);
    }
  }

  async loadItems() {
    try {
      this.items = this.rawItems = await this.rest.getItems();
    } catch (e) {
      this.message.error(e.message || e);
    }
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
