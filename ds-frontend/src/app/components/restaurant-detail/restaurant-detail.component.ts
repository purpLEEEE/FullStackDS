import {Component, EventEmitter, Output} from '@angular/core';
import { CommonModule } from '@angular/common';
import {Restaurant} from "../../interface/restaurant";
import {ActivatedRoute, RouterLink} from "@angular/router";
import {RestaurantService} from "../../service/restaurant-service";
import {RestaurantsComponent} from "../restaurants/restaurants.component";
import {ColorDirective} from "../../directives/color.directive";
import {EvaluationService} from "../../service/evaluation.service";
import {AddRestaurantFormData} from "../restaurants/add-restaurant-form/add-restaurant-form.component";
import {AddEvaluation} from "../../interface/dto/request/add-evaluation";
import {AddEvaluationFormComponent} from "./add-evaluation-form/add-evaluation-form.component";

@Component({
  selector: 'app-restaurant-detail',
  standalone: true,
  imports: [CommonModule, ColorDirective, RouterLink, AddEvaluationFormComponent],
  templateUrl: './restaurant-detail.component.html',
  styleUrl: './restaurant-detail.component.css'
})
export class RestaurantDetailComponent {

  @Output("evaluationSubmitted") evaluationSubmittedEmiter = new EventEmitter<AddRestaurantFormData>();

  public restaurant : Restaurant = {
    id : 0,
    adresse : "",
    nom : "",
    moyenne : -1,
    commentaires : [],
    evaluation_finale : {
      id : 0,
      nom : "",
      commentaire : ""
    },
    cover : ""
  };

  constructor(private routes: ActivatedRoute, private readonly restaurantService: RestaurantService, private readonly evaluationService : EvaluationService) {
    let id : number = this.routes.snapshot.params['id']
    this.getDetail(id);

  }

  public getDetail(restaurantId : number) : void{
    let restaurant : Restaurant;
    this.restaurantService.getRestaurant(restaurantId).subscribe(value => {
      this.restaurant = value;
    }, error => {
      console.log(error)
    })

  }

  supprimerCommentaire(id: number) {
    this.evaluationService.deleteCommentaire(id).subscribe(value => {
      this.getDetail(this.routes.snapshot.params['id'])
    })
  }

  public onEvaluationSubmitted(newEvaluationData: AddEvaluation): void {
    this.restaurantService.addEvaluation(newEvaluationData, this.restaurant.id).subscribe(value => {
      this.restaurant = value
    })
  }
}
