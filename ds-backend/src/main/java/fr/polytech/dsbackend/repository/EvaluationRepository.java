package fr.polytech.dsbackend.repository;

import fr.polytech.dsbackend.entity.EvaluationEntity;
import fr.polytech.dsbackend.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluationRepository extends JpaRepository<EvaluationEntity, Integer> {
}
