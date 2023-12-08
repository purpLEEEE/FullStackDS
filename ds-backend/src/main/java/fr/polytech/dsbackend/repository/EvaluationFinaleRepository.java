package fr.polytech.dsbackend.repository;

import fr.polytech.dsbackend.entity.EvaluationFinaleEntity;
import fr.polytech.dsbackend.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluationFinaleRepository extends JpaRepository<EvaluationFinaleEntity, Integer> {
}
