package fr.polytech.dsbackend.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.polytech.dsbackend.entity.EvaluationEntity;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvaluationDto {

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

    @JsonProperty("date_publication")
    private LocalDate date_publication;

    @JsonProperty("date_maj")
    private LocalDate date_MAJ;

    public static EvaluationDto buildFromEntity(EvaluationEntity commentaireEntity) {
        return EvaluationDto.builder()
                .id(commentaireEntity.getId())
                .nom(commentaireEntity.getNom())
                .commentaire(commentaireEntity.getCommentaire())
                .note(commentaireEntity.getNote())
                .date_publication(commentaireEntity.getDateSortie())
                .date_MAJ(commentaireEntity.getDateMAJ())
                .build();
    }

}
