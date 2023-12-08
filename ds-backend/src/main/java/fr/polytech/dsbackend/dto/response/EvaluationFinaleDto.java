package fr.polytech.dsbackend.dto.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import fr.polytech.dsbackend.entity.EvaluationFinaleEntity;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvaluationFinaleDto {

    @JsonProperty("id")
    @Min(0)
    private Integer id;


    @JsonProperty("nom")
    @Size(max = 50)
    private String nom;

    @JsonProperty("commentaire")
    @Size(max = 255)
    private String commentaire;

    @JsonProperty("note")
    @Min(0)
    @Max(3)
    private Integer note;


    public static EvaluationFinaleDto buildFromEntity(EvaluationFinaleEntity evaluationFinale) {
        return EvaluationFinaleDto.builder()
                .id(evaluationFinale.getId())
                .nom(evaluationFinale.getNom())
                .commentaire(evaluationFinale.getDescription())
                .note(evaluationFinale.getNote())
                .build();
    }
}
