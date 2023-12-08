package fr.polytech.dsbackend.service;


import fr.polytech.dsbackend.dto.request.AddRestaurantDto;
import fr.polytech.dsbackend.dto.request.UpdateRestaurantDto;
import fr.polytech.dsbackend.dto.response.TagDto;
import fr.polytech.dsbackend.entity.RestaurantEntity;
import fr.polytech.dsbackend.entity.TagEntity;
import fr.polytech.dsbackend.exception.InvalidValueException;
import fr.polytech.dsbackend.exception.ResourceNotFoundException;
import fr.polytech.dsbackend.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;


    public List<RestaurantEntity> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public RestaurantEntity getRestaurantById(Integer serieId) {
        return restaurantRepository.findById(serieId).orElseThrow(() -> new ResourceNotFoundException("Serie with id " + serieId + " + not found"));
    }

    public RestaurantEntity addRestaurant(final AddRestaurantDto restaurantDto) {
        List<TagEntity> tagsToAdd = new ArrayList<>();
        if(restaurantDto.getTags() != null){
            for (TagDto tag: restaurantDto.getTags()
            ) {
                tagsToAdd.add(TagEntity.builder().id(tag.getId()).tag(tag.getTag()).build());
            }
        }

        final RestaurantEntity restaurantToInsert = RestaurantEntity.builder()
                .nom(restaurantDto.getNom())
                .adresse(restaurantDto.getAdresse())
                .evaluations(new ArrayList<>())
                .tags(tagsToAdd)
                .build();

        return this.restaurantRepository.save(restaurantToInsert);
    }

    public void deleteRestaurant(final Integer id) {
        this.restaurantRepository.deleteById(id);
    }




    public RestaurantEntity updateRestaurantById(Integer id, final UpdateRestaurantDto dto) {
        if(dto == null) {
            throw new InvalidValueException("Les informations du restaurant à mettre à jour ne peuvent pas être null");
        }

        RestaurantEntity restaurantEntity = restaurantRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Le restaurant à modifier n'existe pas"));

        if(dto.getNom() != null) {
            if(!dto.getNom().isEmpty()){
                restaurantEntity.setNom(dto.getNom());
            }
        }
        if(dto.getAdresse() != null) {
            if(!dto.getAdresse().isEmpty()){
                restaurantEntity.setAdresse(dto.getAdresse());
            }
        }
        if(dto.getTags() != null){
            List<TagEntity> tagsToUpdate = new ArrayList<>();
            for (TagDto tag: dto.getTags()) {
                tagsToUpdate.add(TagEntity.builder().id(tag.getId()).tag(tag.getTag()).build());
            }
            restaurantEntity.setTags(tagsToUpdate);
        }

        restaurantRepository.save(restaurantEntity);
        return restaurantEntity;
    }

}
