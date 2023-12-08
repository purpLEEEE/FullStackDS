package fr.polytech.dsbackend.service;

import fr.polytech.dsbackend.dto.request.AddEvaluationDto;
import fr.polytech.dsbackend.dto.request.AddEvaluationFinaleDto;
import fr.polytech.dsbackend.dto.request.UpdateEvaluationDto;
import fr.polytech.dsbackend.dto.response.EvaluationDto;
import fr.polytech.dsbackend.entity.EvaluationEntity;
import fr.polytech.dsbackend.entity.EvaluationFinaleEntity;
import fr.polytech.dsbackend.exception.InvalidValueException;
import fr.polytech.dsbackend.exception.ResourceNotFoundException;
import fr.polytech.dsbackend.repository.EvaluationFinaleRepository;
import fr.polytech.dsbackend.repository.EvaluationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service
@RequiredArgsConstructor
public class EvaluationService {

    private final EvaluationRepository evaluationRepository;
    private final EvaluationFinaleRepository evaluationFinaleRepository;
    private final RestaurantService restaurantService;
    public List<EvaluationEntity> getCommentaires(Integer idRestaurant) {
        return restaurantService.getRestaurantById(idRestaurant).getEvaluations();
    }

    public EvaluationEntity getCommentaire(Integer id) {
        return evaluationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Le commentaire d'id " + id + " n'existe pas"));
    }

    public void editCommentaire(Integer id, EvaluationDto evaluationDto) {
    }

    public void addCommentaire(Integer id, AddEvaluationDto commentaireDto) {


        EvaluationEntity commentaireEntity = new EvaluationEntity();
        commentaireEntity.setNom(commentaireDto.getNom());
        commentaireEntity.setCommentaire(commentaireDto.getCommentaire());
        commentaireEntity.setRestaurant(this.restaurantService.getRestaurantById(id));
        commentaireEntity.setDateSortie(java.time.LocalDate.now());
        commentaireEntity.setNote(commentaireDto.getNote());
        evaluationRepository.save(commentaireEntity);
    }

    public void delCommentaire(Integer id) {
        evaluationRepository.deleteById(id);
    }

    public List<EvaluationEntity> getAllCommentaires() {
        return evaluationRepository.findAll();
    }

    public void addEvaluationFinale(Integer id, AddEvaluationFinaleDto evaluationDto) {
        EvaluationFinaleEntity evaluationFinaleEntity = new EvaluationFinaleEntity();
        evaluationFinaleEntity.setRestaurant(this.restaurantService.getRestaurantById(id));
        evaluationFinaleEntity.setNom(evaluationDto.getNom());
        evaluationFinaleEntity.setNote(evaluationDto.getNote());
        evaluationFinaleEntity.setDescription(evaluationDto.getCommentaire());
        evaluationFinaleRepository.save(evaluationFinaleEntity);
    }

    public void delEvaluationFinale(Integer id) {
        try {
            Integer idEvaluationFinale = restaurantService.getRestaurantById(id).getEvaluationFinale().getId();
            evaluationFinaleRepository.deleteById(idEvaluationFinale);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public EvaluationEntity updateEvaluation(Integer idEvaluation, UpdateEvaluationDto updateEvaluationDto) {
        if(updateEvaluationDto == null) {
            throw new InvalidValueException("Les informations de l'évaluation à mettre à jour ne peuvent pas être null");
        }

        EvaluationEntity evaluationEntity = evaluationRepository.findById(idEvaluation).orElseThrow(() -> new ResourceNotFoundException("L'évaluation à modifier n'existe pas"));

        if(updateEvaluationDto.getNom() != null) {
            if(!updateEvaluationDto.getNom().isEmpty()){
                evaluationEntity.setNom(updateEvaluationDto.getNom());
            }
        }
        if(updateEvaluationDto.getCommentaire() != null) {
            if(!updateEvaluationDto.getCommentaire().isEmpty()){
                evaluationEntity.setCommentaire(updateEvaluationDto.getCommentaire());
            }
        }
        if(updateEvaluationDto.getNote() != null){
            evaluationEntity.setNote(updateEvaluationDto.getNote());
        }
        evaluationEntity.setDateMAJ(java.time.LocalDate.now());
        evaluationRepository.save(evaluationEntity);
        return evaluationEntity;

    }
}
