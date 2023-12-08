import {Component, Input} from '@angular/core';
import { CommonModule } from '@angular/common';
import {RestaurantService} from "../../../service/restaurant-service";
import {Restaurant} from "../../../interface/restaurant";
import {Subscription} from "rxjs";
import {AddRestaurant} from "../../../interface/dto/request/add-restaurant";
import {AddRestaurantFormComponent} from "../add-restaurant-form/add-restaurant-form.component";
import {ColorDirective} from "../../../directives/color.directive";
import {RouterLink, RouterOutlet} from "@angular/router";

@Component({
  selector: 'app-restaurants-display',
  standalone: true,
  imports: [CommonModule, AddRestaurantFormComponent, ColorDirective, RouterOutlet, RouterLink],
  templateUrl: './restaurants-display.component.html',
  styleUrl: './restaurants-display.component.css'
})
export class RestaurantsDisplayComponent {

  @Input() restaurants : Restaurant[] = []


  restaurantById(id: number) {

  }
}
