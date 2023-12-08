import {Component, EventEmitter, Output} from '@angular/core';
import { CommonModule } from '@angular/common';
import {AppErrorDirective} from "../../../directives/app-error.directive";
import {FormsModule, NgForm} from "@angular/forms";
import {NgMultiSelectDropDownModule} from "ng-multiselect-dropdown";
import {AddRestaurantFormData} from "../../restaurants/add-restaurant-form/add-restaurant-form.component";
import {AddEvaluation} from "../../../interface/dto/request/add-evaluation";

@Component({
  selector: 'app-add-evaluation-form',
  standalone: true,
    imports: [CommonModule, AppErrorDirective, FormsModule, NgMultiSelectDropDownModule],
  templateUrl: './add-evaluation-form.component.html',
  styleUrl: './add-evaluation-form.component.css'
})
export class AddEvaluationFormComponent {

  @Output("evaluationSubmitted") evaluationSubmitEmitter = new EventEmitter<AddEvaluation>();

  public formData: AddEvaluation = {
    "nom": "",
    "commentaire": "",
    "note" : 0
  }

  public submit(form: NgForm): void {
    if (form.valid) {
      this.evaluationSubmitEmitter.emit(this.formData);
      form.reset()
    } else {
      alert("Le formulaire n'est pas correctement rempli, le commentaire n'a pas été ajouté")
    }
  }

}
