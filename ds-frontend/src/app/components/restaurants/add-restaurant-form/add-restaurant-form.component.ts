import {Component, EventEmitter, Output, OnInit} from '@angular/core';
import { CommonModule } from '@angular/common';
import {Tag} from "../../../interface/dto/response/tag";
import {FormsModule, NgForm} from "@angular/forms";
import {AppErrorDirective} from "../../../directives/app-error.directive";
import {NgMultiSelectDropDownModule} from "ng-multiselect-dropdown";
import { IDropdownSettings } from 'ng-multiselect-dropdown';

@Component({
  selector: 'app-add-restaurant-form',
  standalone: true,
  imports: [CommonModule, FormsModule, AppErrorDirective, NgMultiSelectDropDownModule],
  templateUrl: './add-restaurant-form.component.html',
  styleUrl: './add-restaurant-form.component.css'
})
export class AddRestaurantFormComponent implements OnInit{

  @Output("restaurantSubmitted") restaurantSubmittedEmiter = new EventEmitter<AddRestaurantFormData>();
  dropdownList : Tag[] = [];
  selectedItems : Tag[] = [];
  dropdownSettings : IDropdownSettings = {};

  ngOnInit(){
    this.dropdownList = [
      {id: 1, tag : "Gastronomie"},
      {id: 2, tag : "Bistrot"},
      {id: 3, tag : "Bistronomie"},
      {id: 4, tag : "Brasserie"},
      {id: 5, tag : "Fastfood"},
    ];
    this.selectedItems = [

    ];
    this.dropdownSettings  = {
      idField: 'id',
      textField: 'tag',
    };
  }

  onItemSelect(item: any) {
    let tag : Tag = {id: item.id, tag: item.tag}
    this.selectedItems.push(tag)
  }

  onItemDeselect(item : any){
    let tag : Tag = {id: item.id, tag: item.tag}
    let tags : Tag[] = []
    this.selectedItems.forEach(value => {
      if(tag.id !== value.id) {
        tags.push(value)
      }
    })
    this.selectedItems = tags
  }
  onSelectAll(items: any) {
    console.log(items);
  }

  public formData: AddRestaurantFormData = {
    nom: "",
    adresse: "",
    tags : []
  }

  public submit(form: NgForm): void {
    if (form.valid) {
      this.formData.tags = this.selectedItems
      this.restaurantSubmittedEmiter.emit(this.formData);
      form.reset()
    } else {
      alert("Le formulaire n'est pas correctement rempli, le restaurant n'a pas été ajouté")
    }
  }

}

export interface AddRestaurantFormData {
  nom: string
  adresse : string
  tags : Tag[]
}
