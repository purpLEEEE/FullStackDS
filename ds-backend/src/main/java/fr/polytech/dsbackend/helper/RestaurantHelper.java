package fr.polytech.dsbackend.helper;

import fr.polytech.dsbackend.entity.EvaluationEntity;

import java.util.List;

public class RestaurantHelper {

    public static float GetMoyenne(List<EvaluationEntity> evaluations){
        if(evaluations.size() == 0){
            return -1;
        } else {
            float moyenne = 0;
            for (EvaluationEntity evaluation: evaluations) {
                moyenne += evaluation.getNote();
            }
            return moyenne/evaluations.size();
        }
    }
}
