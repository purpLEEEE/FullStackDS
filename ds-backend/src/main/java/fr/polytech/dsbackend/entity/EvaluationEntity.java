package fr.polytech.dsbackend.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity(name = "evaluations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EvaluationEntity {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "nom", columnDefinition = "varchar(50)", nullable = false)
    private String nom;

    @Column(name = "commentaire", columnDefinition = "varchar(255)", nullable = false)
    private String commentaire;

    @Column(name = "note", columnDefinition = "int", nullable = false)
    private Integer note;

    @Column(
            name = "date_publication",
            columnDefinition = "date",
            nullable =  false
    )
    private LocalDate dateSortie;

    @Column(
            name = "date_maj",
            columnDefinition = "date"
    )
    private LocalDate dateMAJ;

    @ManyToOne()
    @JoinColumn(name = "restaurant")
    private RestaurantEntity restaurant;






}
