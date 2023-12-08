import {Evaluation} from "./evaluation";
import {EvaluationFinale} from "./evaluation-finale";

export interface Restaurant {

  id: number,
  nom: string,
  adresse : string
  moyenne: number;
  commentaires: Evaluation[]
  evaluation_finale : EvaluationFinale
  cover: string
}
