package fr.polytech.dsbackend.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "tags")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TagEntity {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "tag", columnDefinition = "VARCHAR(20)")
    private String tag;

    @ManyToMany(mappedBy = "tags")
    private List<RestaurantEntity> restaurantEntities;

}
