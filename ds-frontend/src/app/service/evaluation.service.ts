import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Restaurant} from "../interface/restaurant";

@Injectable({
  providedIn: 'root'
})
export class EvaluationService {

  constructor(private httpClient: HttpClient) { }

  deleteCommentaire(id: number) {
    return this.httpClient.delete<Restaurant[]>("http://localhost:8080/evaluations/"+id)
  }

}
