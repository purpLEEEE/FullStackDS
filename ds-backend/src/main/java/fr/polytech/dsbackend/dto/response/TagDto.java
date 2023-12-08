package fr.polytech.dsbackend.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.polytech.dsbackend.entity.EvaluationEntity;
import fr.polytech.dsbackend.entity.TagEntity;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TagDto {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("tag")
    @Size(max = 20)
    private String tag;

    public static TagDto buildFromEntity(TagEntity tagEntity) {
        return TagDto.builder()
                .id(tagEntity.getId())
                .tag(tagEntity.getTag())
                .build();
    }
}
