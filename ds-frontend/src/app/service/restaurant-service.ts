import {Injectable, Output} from '@angular/core';
import {Restaurant} from "../interface/restaurant";
import {HttpClient} from "@angular/common/http";
import {Observable, Subscription} from "rxjs";
import {AddRestaurant} from "../interface/dto/request/add-restaurant";
import {Tag} from "../interface/dto/response/tag";
import {AddEvaluation} from "../interface/dto/request/add-evaluation";

@Injectable({
  providedIn: 'root'
})
export class RestaurantService {

  constructor(private httpClient: HttpClient) { }

  getRestaurants() : Observable<Restaurant[]> {
    return this.httpClient.get<Restaurant[]>("http://localhost:8080/restaurants")
  }

  getRestaurant(idRestaurant : number) : Observable<Restaurant>{
    return this.httpClient.get<Restaurant>(`http://localhost:8080/restaurants/${idRestaurant}`)
  }

  getCover(id : number) : Observable<string> {
    return this.httpClient.get<string>("http://localhost:8080/restaurants/" + id + "/cover", {responseType: 'text' as 'json'})
  }

  addRestaurant(restaurantToAdd : AddRestaurant) : Observable<Restaurant> {
    return this.httpClient.post<Restaurant>("http://localhost:8080/restaurants", restaurantToAdd)
  }

  deleteRestaurant(id: number) {
    return this.httpClient.delete<Restaurant[]>("http://localhost:8080/restaurants/"+id)
  }

  addEvaluation(newEvaluationData: AddEvaluation, id : number) {
    return this.httpClient.post<Restaurant>("http://localhost:8080/restaurants/" + id + "/evaluations", newEvaluationData)

  }
}
