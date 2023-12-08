import { Routes } from '@angular/router';
import {RestaurantsComponent} from "./components/restaurants/restaurants.component";
import {RestaurantDetailComponent} from "./components/restaurant-detail/restaurant-detail.component";


export const routes: Routes = [
  {path: "restaurants", component: RestaurantsComponent},
  {path: "restaurants/:id", component: RestaurantDetailComponent}
];
