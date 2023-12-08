package fr.polytech.dsbackend.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "evaluation_finale")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EvaluationFinaleEntity {

    @Id
    @GeneratedValue()
    private Integer id;

    @Column(name = "nom", columnDefinition = "varchar(90)", nullable = false)
    private String nom;

    @Column(name = "note", columnDefinition = "int")
    private Integer note;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @OneToOne()
    @JoinColumn(name = "restaurant")
    private RestaurantEntity restaurant;
}
