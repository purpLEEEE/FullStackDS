package fr.polytech.dsbackend.repository;


import fr.polytech.dsbackend.entity.RestaurantEntity;
import fr.polytech.dsbackend.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<TagEntity, Integer> {
}
