package fr.polytech.dsbackend.controller;


import fr.polytech.dsbackend.dto.request.AddEvaluationDto;
import fr.polytech.dsbackend.dto.request.AddEvaluationFinaleDto;
import fr.polytech.dsbackend.dto.request.UpdateEvaluationDto;
import fr.polytech.dsbackend.dto.response.EvaluationDto;
import fr.polytech.dsbackend.entity.EvaluationEntity;
import fr.polytech.dsbackend.exception.InvalidValueException;
import fr.polytech.dsbackend.service.EvaluationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController()
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Slf4j
public class EvaluationController {

    private final EvaluationService evaluationService;

    @GetMapping("/restaurants/{id}/commentaires")
    public List<EvaluationDto> getCommentaires(@PathVariable Integer id){
        log.info("GET /restaurants/{}/commentaires", id);
        List<EvaluationDto> commentaireDtos = new ArrayList<>();
        for (EvaluationEntity commentaireEntity: evaluationService.getCommentaires(id)) {
            commentaireDtos.add(EvaluationDto.buildFromEntity(commentaireEntity));
        }
        return commentaireDtos;
    }
    @GetMapping("/evaluations/{id}")
    public EvaluationDto getCommentaire(@Valid @PathVariable Integer id){
        log.info("GET /evaluations/{}", id);

        return EvaluationDto.buildFromEntity(evaluationService.getCommentaire(id));
    }

    @GetMapping("/evaluations")
    public List<EvaluationDto> getAllCommentaires(){
        log.info("GET /evaluations");
        List<EvaluationDto> evaluationDtoDtos = new ArrayList<>();
        for (EvaluationEntity commentaireEntity: evaluationService.getAllCommentaires()
        ) {
            evaluationDtoDtos.add(
                    EvaluationDto.builder()
                            .nom(commentaireEntity.getNom())
                            .commentaire(commentaireEntity.getCommentaire())
                            .date_MAJ(commentaireEntity.getDateMAJ())
                            .date_publication(commentaireEntity.getDateSortie())
                            .id(commentaireEntity.getId())
                            .note(commentaireEntity.getNote())
                            .build()
            );
        }
        return evaluationDtoDtos;
    }

    @PutMapping("/restaurants/{id}/evaluations")
    public void editCommentaire(@Valid @PathVariable Integer id, @RequestBody EvaluationDto evaluationDto){
        log.info("PUT /restaurants/{id}/evaluations", id);

        if(evaluationDto.getNom().length() > 50){
            throw new InvalidValueException("Le nom ne doit pas faire plus de 50 caractères");
        }
        if(evaluationDto.getCommentaire().length() > 255 ){
            throw new InvalidValueException("Le commentaire ne doit pas faire plus de 255 caractères");
        }
        if(evaluationDto.getNote() > 3 || evaluationDto.getNote() < 0) {
            throw new InvalidValueException("La note doit être comprise entre 0 et 3");
        }

        evaluationService.editCommentaire(id, evaluationDto);
    }

    @PostMapping("/restaurants/{id}/evaluations")
    public void addCommentaire(@Valid @PathVariable Integer id, @Valid @RequestBody AddEvaluationDto evaluationDto){
        log.info("POST /restaurants/{id}/evaluations", id);

        if(evaluationDto.getNom().length() > 50){
            throw new InvalidValueException("Le nom ne doit pas faire plus de 50 caractères");
        }
        if(evaluationDto.getCommentaire().length() > 255 ){
            throw new InvalidValueException("Le commentaire ne doit pas faire plus de 255 caractères");
        }
        if(evaluationDto.getNote() > 3 || evaluationDto.getNote() < 0) {
            throw new InvalidValueException("La note doit être comprise entre 0 et 3");
        }

        evaluationService.addCommentaire(id, evaluationDto);
    }

    @PostMapping("/restaurants/{id}/evaluation_finale")
    public void addEvaluationFinale(@Valid @PathVariable Integer id, @Valid @RequestBody AddEvaluationFinaleDto evaluationDto){
        log.info("POST /restaurants/{id}/evaluation_finale", id);

        if(evaluationDto.getNom().length() > 50){
            throw new InvalidValueException("Le nom ne doit pas faire plus de 50 caractères");
        }
        if(evaluationDto.getCommentaire().length() > 255 ){
            throw new InvalidValueException("Le commentaire ne doit pas faire plus de 255 caractères");
        }
        if(evaluationDto.getNote() > 3 || evaluationDto.getNote() < 0) {
            throw new InvalidValueException("La note doit être comprise entre 0 et 3");
        }

        evaluationService.addEvaluationFinale(id, evaluationDto);
    }

    @DeleteMapping("/restaurants/{id}/evaluation_finale")
    public void delEvaluationFinale(@Valid @PathVariable Integer id){
        log.info("DEL /restaurants/{}/evaluation_finale", id);

        evaluationService.delEvaluationFinale(id);
    }

    @DeleteMapping("/restaurants/{id}/evaluations/{idCommentaire}")
    public void delCommentaire(@Valid @PathVariable Integer id, @Valid @PathVariable Integer idCommentaire){
        log.info("DEL /restaurants/{}/evaluations/{}", id, idCommentaire);

        evaluationService.delCommentaire(idCommentaire);
    }


    @DeleteMapping("/evaluations/{id}")
    public void delCommentaire2(@Valid @PathVariable Integer id){
        log.info("DEL /evaluations/{}", id);

        evaluationService.delCommentaire(id);
    }

    @PutMapping("/restaurants/{id}/evaluations/{idEvaluation}")
    public EvaluationDto updateEvaluation(@PathVariable Integer id, @PathVariable Integer idEvaluation, @RequestBody UpdateEvaluationDto updateEvaluationDto){
        log.info("PUT /restaurants/{}/evaluations/{}", id, idEvaluation);

        if(updateEvaluationDto.getNom().length() > 50){
            throw new InvalidValueException("Le nom ne doit pas faire plus de 50 caractères");
        }
        if(updateEvaluationDto.getCommentaire().length() > 255 ){
            throw new InvalidValueException("Le commentaire ne doit pas faire plus de 255 caractères");
        }
        if(updateEvaluationDto.getNote() > 3 || updateEvaluationDto.getNote() < 0) {
            throw new InvalidValueException("La note doit être comprise entre 0 et 3");
        }

        return EvaluationDto.buildFromEntity(evaluationService.updateEvaluation(idEvaluation, updateEvaluationDto));
    }

    @PutMapping("/evaluations/{idEvaluation}")
    public EvaluationDto updateEvaluation(@PathVariable Integer idEvaluation, @RequestBody UpdateEvaluationDto updateEvaluationDto){
        log.info("PUT /evaluations/{}", idEvaluation);

        if(updateEvaluationDto.getNom().length() > 50){
            throw new InvalidValueException("Le nom ne doit pas faire plus de 50 caractères");
        }
        if(updateEvaluationDto.getCommentaire().length() > 255 ){
            throw new InvalidValueException("Le commentaire ne doit pas faire plus de 255 caractères");
        }
        if(updateEvaluationDto.getNote() > 3 || updateEvaluationDto.getNote() < 0) {
            throw new InvalidValueException("La note doit être comprise entre 0 et 3");
        }

        return EvaluationDto.buildFromEntity(evaluationService.updateEvaluation(idEvaluation, updateEvaluationDto));
    }



}
