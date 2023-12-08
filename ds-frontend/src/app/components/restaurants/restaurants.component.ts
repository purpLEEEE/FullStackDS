import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RestaurantsDisplayComponent} from "./restaurants-display/restaurants-display.component";
import {Restaurant} from "../../interface/restaurant";
import {RestaurantService} from "../../service/restaurant-service";
import {AddRestaurantFormComponent, AddRestaurantFormData} from "./add-restaurant-form/add-restaurant-form.component";

@Component({
  selector: 'app-restaurants',
  standalone: true,
  imports: [CommonModule, RestaurantsDisplayComponent, AddRestaurantFormComponent],
  templateUrl: './restaurants.component.html',
  styleUrl: './restaurants.component.css'
})
export class RestaurantsComponent {

  public restaurants: Restaurant[] = [];

  constructor(private readonly restaurantService: RestaurantService) {
  }

  ngOnInit(): void {
    this.refreshRestaurants();
  }

  public refreshRestaurants() {
    this.restaurantService.getRestaurants().subscribe(value => {
      this.restaurants = value;
      //this.restaurantService.forEach(restaurant => this.getCover(restaurant));
    })
  }

  /*
  public getCover(restaurant: Restaurant): void {
    this.restaurantService.getCover(restaurant.id).subscribe(urlDto => {
      restaurant.coverUrl = restaurant.url;
    });
  }*/

  public onRestaurantSubmitted(newRestaurantData: AddRestaurantFormData): void {
    this.restaurantService.addRestaurant(newRestaurantData).subscribe(value => {
      this.restaurants.push(value);
      //this.getCover(value);
    })
  }

  public deleteRestaurant(restaurantId : number) : void{
    this.restaurantService.deleteRestaurant(restaurantId).subscribe(value => {
      this.restaurants = value;
    })
  }

}
