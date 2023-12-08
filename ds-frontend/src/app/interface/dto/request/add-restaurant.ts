import {Tag} from "../response/tag";

export interface AddRestaurant {
  nom: string
  adresse : string
  tags : Tag[]
}
