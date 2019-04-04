import {Component, ElementRef, EventEmitter, OnInit, Output, ViewChild} from '@angular/core';
import {VehicleService} from '../_services/vehicle.service';
import {Vehicle} from '../_models/vehicle';
import {Observable} from 'rxjs';
import {FormControl} from '@angular/forms';

@Component({
  selector: 'app-search-bar',
  templateUrl: './search-bar.component.html',
  styleUrls: ['./search-bar.component.scss']
})
export class SearchBarComponent implements OnInit {

  myControl = new FormControl();
  filteredOptions: Observable<string[]>;
  allVehicles: Vehicle[];
  autoCompleteList: any[];

  @ViewChild('autocompleteInput') autocompleteInput: ElementRef;
  @Output() onSelectedOption = new EventEmitter();

  constructor(
    private vehicleService: VehicleService
  ) { }

  ngOnInit() {
    this.vehicleService.getAll().subscribe(vehicles => {
      this.allVehicles = vehicles;
    });

    this.myControl.valueChanges.subscribe(userInput => {
      this.autoCompleteExpenseList(userInput);
    });
  }

  private autoCompleteExpenseList (input){
    this.autoCompleteList = this.filterCategoryList(input);
  }

  filterCategoryList(val) {
    const categoryList = [];
    if (typeof val !== 'string' || val === '' || val === null) {
      return [];
    }
    return val ? this.allVehicles.filter(s =>
      s.name.toLowerCase().indexOf(val.toLowerCase()) !== -1) : this.allVehicles;
  }

  displayFn(vehicle: Vehicle) {
    const k = vehicle ? vehicle.name : vehicle;
    return k;
  }

  filterVehicleList(event) {
    const vehicles = event.source.value;
    if (!vehicles) {
      this.vehicleService.searchOption = [];
    } else {
      this.vehicleService.searchOption.push(vehicles);
      this.onSelectedOption.emit(this.vehicleService.searchOption);
    }
    this.focusOnPlaceInput();
  }

  removeOption(option) {

    const index = this.vehicleService.searchOption.indexOf(option);
    if (index >= 0) {
      this.vehicleService.searchOption.splice(index, 1);
    }
    this.focusOnPlaceInput();

    this.onSelectedOption.emit(this.vehicleService.searchOption);
  }

  focusOnPlaceInput() {
    this.autocompleteInput.nativeElement.focus();
    this.autocompleteInput.nativeElement.value = '';
  }
}
