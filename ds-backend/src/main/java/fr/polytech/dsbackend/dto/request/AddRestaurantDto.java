package fr.polytech.dsbackend.dto.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import fr.polytech.dsbackend.dto.response.TagDto;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddRestaurantDto {

    @JsonProperty("nom")
    @Size(max = 90)
    private String nom;

    @JsonProperty("adresse")
    @Size(max = 255)
    private String adresse;

    @JsonProperty("tags")
    private List<TagDto> tags;

}
