package fr.polytech.dsbackend.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.polytech.dsbackend.entity.RestaurantEntity;
import fr.polytech.dsbackend.entity.TagEntity;
import fr.polytech.dsbackend.helper.RestaurantHelper;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDto {

    @JsonProperty("id")
    @Min(0)
    private Integer id;

    @JsonProperty("nom")
    @Size(max = 90)
    private String nom;

    @JsonProperty("adresse")
    private String adresse;

    @JsonProperty("moyenne")
    private float moyenne;

    @JsonProperty("commentaires")
    private List<EvaluationDto> commentaires;

    @JsonProperty("tags")
    private List<TagDto> tags;

    @JsonProperty("evaluation")
    private EvaluationFinaleDto evaluationFinaleDto;

    public static RestaurantDto buildFromEntity(RestaurantEntity restaurantEntity) {
        return RestaurantDto.builder()
                .id(restaurantEntity.getId())
                .nom(restaurantEntity.getNom())
                .adresse(restaurantEntity.getAdresse())
                .commentaires(restaurantEntity.getEvaluations() != null ? restaurantEntity.getEvaluations().stream().map(evaluationEntity -> EvaluationDto.buildFromEntity(evaluationEntity)).toList() : new ArrayList<>())
                .tags(restaurantEntity.getTags() != null ? restaurantEntity.getTags().stream().map(tagEntity -> TagDto.buildFromEntity(tagEntity)).toList() : new ArrayList<>())
                .evaluationFinaleDto(restaurantEntity.getEvaluationFinale() == null ? null : EvaluationFinaleDto.buildFromEntity(restaurantEntity.getEvaluationFinale()))
                .moyenne(RestaurantHelper.GetMoyenne(restaurantEntity.getEvaluations()))
                .build();
    }
}
