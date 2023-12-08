package fr.polytech.dsbackend.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "restaurants")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantEntity {


    @Id
    @GeneratedValue()
    private Integer id;

    @Column(name = "nom", columnDefinition = "varchar(90)", nullable = false)
    private String nom;

    @Column(name = "adresse", columnDefinition = "varchar(255)", nullable = false)
    private String adresse;

    @Column(name="illustration", columnDefinition = "TEXT")
    private String illustration;

    @ManyToMany
    @JoinTable(
            name="restau_jn_tags",
            joinColumns = @JoinColumn(name = "id_restaurant"),
            inverseJoinColumns = @JoinColumn(name = "id_tags"))
    private List<TagEntity> tags;

    @OneToMany(mappedBy = "restaurant")
    private List<EvaluationEntity> evaluations;


    @OneToOne(mappedBy = "restaurant")
    private EvaluationFinaleEntity evaluationFinale;

}
