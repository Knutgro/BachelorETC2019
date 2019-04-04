import {Component, ElementRef, EventEmitter, OnInit, Output, ViewChild} from '@angular/core';
import {VehicleService} from '../_services/vehicle.service';
import {Vehicle} from '../_models/vehicle';


export interface CatFilter { // TODO reconsider
  category: string;
  name: string;
}

@Component({
  selector: 'app-search-filter',
  templateUrl: './search-filter.component.html',
  styleUrls: ['./search-filter.component.scss']
})
export class SearchFilterComponent implements OnInit {
  selectedCount: 0;
  selectedFilter: any;
  vehicles: Vehicle[];
  catFilters: any;

  @ViewChild('checkboxFilter') checkboxFilter: ElementRef;
  @Output() onSelectedFilter = new EventEmitter();

  //region TODO Replace with filters incoming from the database.
  filters = [
    {
      name: 'Hjuldrift',
      category: 'Drive',
      options: [
        {
          name: 'Forhjulsdrift',
          selected: false
        },
        {
          name: 'Bakhjulsdrift',
          selected: false
        },
        {
          name: 'Firehjulsdrift',
          selected: false
        }
      ]
    },
    {
      name: 'Farge',
      category: 'Color',
      options: [
        {
          name: 'Rød',
          selected: false
        },
        {
          name: 'Blå',
          selected: false
        },
        {
          name: 'Grønn',
          selected: false
        }
      ]
    }
  ]
  //endregion

  constructor(
    private vehicleService: VehicleService
  ) { }

  ngOnInit() {
    this.vehicleService.getAll().subscribe(vehicles => {
      this.vehicles = vehicles;
    });
    this.catFilters = [{category: '', name: ''}];
    this.catFilters.pop();
  }

  filterVehicleList(event, selected) {
    const filters = event.source.value;
    //endregion
    if (!filters) {
      this.vehicleService.searchFilter.length = 0;
    } else {
      //region TODO reconsider
      let separated = filters.split(",");
      for (let i = 0; i < separated.length; i += 2) {
        console.log(separated[i] + '-+-' + separated[i + 1]);
        this.vehicleService.searchFilter.push({category: separated[i], name: separated[i + 1]});
      }
      this.onSelectedFilter.emit(this.vehicleService.searchFilter);
    }
  }


  getSelectedFilter() {
    this.selectedCount = 0;
    this.vehicleService.searchFilter.length = 0;
    this.selectedFilter = this.filters.filter( filterTop => {
      return filterTop.options.filter( option => {
        if (option.selected) {
          this.selectedCount++;
          this.vehicleService.searchFilter.push({name: option.name, selected: true});
          return option.name;
        }
      });
    });
    this.vehicleService.selectedFilterCount = this.selectedCount;
    this.onSelectedFilter.emit(this.vehicleService.searchFilter);
    // this.onSelectedFilter.emit(this.vehicleService.searchFilter);
  }

}
