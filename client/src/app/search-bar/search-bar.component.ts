import {Component, ElementRef, EventEmitter, OnInit, Output, ViewChild} from '@angular/core';
import {VehicleService} from '../_services/vehicle.service';
import {Vehicle} from '../_models/vehicle';
import {Observable} from 'rxjs';
import {FormControl} from '@angular/forms';
import {Listing} from '../_models/listing';
import {ListingService} from '../_services/listing.service';

@Component({
  selector: 'app-search-bar',
  templateUrl: './search-bar.component.html',
  styleUrls: ['./search-bar.component.scss']
})
export class SearchBarComponent implements OnInit {

  myControl = new FormControl();
  filteredOptions: Observable<string[]>;
  allListings: Listing[];
  autoCompleteList: any[];

  @ViewChild('autocompleteInput') autocompleteInput: ElementRef;
  @Output() onSelectedOption = new EventEmitter();

  constructor(
    public listingService: ListingService
  ) { }

  ngOnInit() {
    this.listingService.getAll().subscribe(listings => {
      this.allListings = listings;
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
    return val ? this.allListings.filter(s =>
      s.title.toLowerCase().indexOf(val.toLowerCase()) !== -1) : this.allListings;
  }

  displayFn(listing: Listing) {
    const k = listing ? listing.title : listing;
    return k;
  }

  filterListingList(event) {
    const listings = event.source.value;
    if (!listings) {
      this.listingService.searchOption = [];
    } else {
      this.listingService.searchOption.push(listings);
      this.onSelectedOption.emit(this.listingService.searchOption);
    }
    this.focusOnPlaceInput();
  }

  removeOption(option) {

    const index = this.listingService.searchOption.indexOf(option);
    if (index >= 0) {
      this.listingService.searchOption.splice(index, 1);
    }
    this.focusOnPlaceInput();

    this.onSelectedOption.emit(this.listingService.searchOption);
  }

  focusOnPlaceInput() {
    this.autocompleteInput.nativeElement.focus();
    this.autocompleteInput.nativeElement.value = '';
  }
}
