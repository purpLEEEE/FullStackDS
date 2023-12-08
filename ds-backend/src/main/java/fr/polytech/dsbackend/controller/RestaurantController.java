package fr.polytech.dsbackend.controller;


import fr.polytech.dsbackend.dto.request.AddRestaurantDto;
import fr.polytech.dsbackend.dto.request.UpdateRestaurantDto;
import fr.polytech.dsbackend.dto.response.RestaurantDto;
import fr.polytech.dsbackend.exception.InvalidValueException;
import fr.polytech.dsbackend.service.RestaurantService;
import lombok.extern.slf4j.Slf4j;
import org.simpleframework.xml.Path;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@CrossOrigin(origins = "*")
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("/restaurants")
    public List<RestaurantDto> getRestaurants() {
        log.info("GET /restaurants");

        return this.restaurantService.getAllRestaurants().stream().map(RestaurantEntity -> RestaurantDto.buildFromEntity(RestaurantEntity)).toList();
    }

    @PostMapping("/restaurants")
    public RestaurantDto addRestaurant(@RequestBody AddRestaurantDto restaurantToCreate) {
        log.info("POST /restaurants");

        if(restaurantToCreate.getNom().length() > 90){
            throw new InvalidValueException("Le nom d'une restaurant ne doit pas faire plus de 90 caractères");
        }
        if(restaurantToCreate.getAdresse().length() > 255){
            throw new InvalidValueException("L'adresse d'un restaurant ne peut pas contenir plus de 255 caractères");
        }

        return RestaurantDto.buildFromEntity(restaurantService.addRestaurant(restaurantToCreate));
    }

    @GetMapping("/restaurants/{id}")
    public RestaurantDto getRestaurantById(@PathVariable Integer id) {
        log.info("GET /restaurants/{}", id);
        return RestaurantDto.buildFromEntity(this.restaurantService.getRestaurantById(id));
    }

    /*
    @PutMapping("/Restaurants/{id}")
    public RestaurantDto updateRestaurantId(@PathVariable Integer id, @RequestBody UpdateRestaurantDto dto) {
        log.info("PUT /Restaurants/{}", id);

        return RestaurantDto.buildFromEntity(this.RestaurantService.updateRestaurantById(id, dto));
    }
    */

    @DeleteMapping("/restaurants/{id}")
    public void deleteRestaurant(@PathVariable Integer id) {
        log.info("DEL /restaurants/{}", id);
        this.restaurantService.deleteRestaurant(id);
    }

    @PutMapping("/restaurants/{id}")
    public RestaurantDto updateRestaurantById(@PathVariable Integer id, @RequestBody UpdateRestaurantDto updateRestaurantDto){
        log.info("PUT /restaurants/{}", id);

        if(updateRestaurantDto.getNom().length() > 90){
            throw new InvalidValueException("Le nom d'une restaurant ne doit pas faire plus de 90 caractères");
        }
        if(updateRestaurantDto.getAdresse().length() > 255){
            throw new InvalidValueException("L'adresse d'un restaurant ne peut pas contenir plus de 255 caractères");
        }

        return RestaurantDto.buildFromEntity(this.restaurantService.updateRestaurantById(id, updateRestaurantDto));
    }

    
}
