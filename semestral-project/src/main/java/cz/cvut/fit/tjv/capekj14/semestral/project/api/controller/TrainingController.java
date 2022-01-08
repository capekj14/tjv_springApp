package cz.cvut.fit.tjv.capekj14.semestral.project.api.controller;

import cz.cvut.fit.tjv.capekj14.semestral.project.api.converter.CourtConverter;
import cz.cvut.fit.tjv.capekj14.semestral.project.api.converter.PlayerConverter;
import cz.cvut.fit.tjv.capekj14.semestral.project.api.converter.TrainingConverter;
import cz.cvut.fit.tjv.capekj14.semestral.project.bussines.CourtService;
import cz.cvut.fit.tjv.capekj14.semestral.project.bussines.EntityStateException;
import cz.cvut.fit.tjv.capekj14.semestral.project.bussines.PlayerService;
import cz.cvut.fit.tjv.capekj14.semestral.project.bussines.TrainingService;
import cz.cvut.fit.tjv.capekj14.semestral.project.domain.Court;
import cz.cvut.fit.tjv.capekj14.semestral.project.domain.Player;
import cz.cvut.fit.tjv.capekj14.semestral.project.domain.Training;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@RestController
public class TrainingController {

    TrainingService service;
    PlayerService serviceP;
    CourtService serviceC;


    public TrainingController(TrainingService service, PlayerService serviceP, CourtService serviceC) {
        this.service = service;
        this.serviceP = serviceP;
        this.serviceC = serviceC;
    }

    @GetMapping("/trainings")
    Collection<TrainingDto> getAllTrainings() {

        return TrainingConverter.toManyDtos(service.findAll());
    }

    @GetMapping("/trainings/{id}")
    TrainingDto getOneTraining(@PathVariable Integer id) {

        return TrainingConverter.toDto(service.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Training not found")));
    }

    @PutMapping("/trainings/{id}")
    TrainingDto updateTraining(@RequestBody TrainingDto trainingDto, @PathVariable Integer id){
        service.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Training not found"));
        try{
            service.update(TrainingConverter.fromDto(trainingDto));
        }catch (EntityStateException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "updating error");

        }
        return trainingDto;
    }

    @DeleteMapping("/trainings/{id}")
    void deleteTraining(@PathVariable Integer id) {
        service.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Training not found"));
        return;
    }

    @PostMapping("/trainings")
    TrainingDto addTraining(@RequestBody TrainingDto trainingDto) {
        try{
            service.create(TrainingConverter.fromDto(trainingDto));
        }catch (EntityStateException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "bad request");
        }
        return trainingDto;
    }

    // PUT/TRAININGS/ID/COURTS/ID
    @PutMapping("/trainings/{idT}/courts/{idC}")
    void createRelationTrainingCourt(@PathVariable Integer idT, @PathVariable Integer idC) {
        Training training = service.findById(idT).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Training not found"));
        Court court = serviceC.findById(idC).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Court not found"));

        training.court = court;
        try{
            service.update(training);
        }
        catch (EntityStateException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error in creating relation");
        }
    }

    // PUT/TRAININGS/ID/PLAYERS/ID
    @PutMapping("/trainings/{idT}/players/{idP}")
    void createRelationTrainingPlayer(@PathVariable Integer idT, @PathVariable Integer idP) {
        Training training = service.findById(idT).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Training not found"));
        Player player = serviceP.findById(idP).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Court not found"));
        if(training.players.contains(player))
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This player is already assigned to this training");
        }
        training.players.add(player);
        try{
            service.update(training);
        }
        catch (EntityStateException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error in creating relation");
        }
    }

    // GET/TRAININGS/ID/PLAYERS
    @GetMapping("/trainings/{id}/players")
    Collection <PlayerDto> getPlayersOfTrainings (@PathVariable Integer id) {
        Collection<Player> players;

        Training training = service.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
            "Training not found"));
        players = training.players;
        return PlayerConverter.toManyDtos(players);

    }

    // GET/TRAININGS/ID/COURTS
    @GetMapping("/trainings/{id}/courts")
    CourtDto getCourtOfTraining (@PathVariable Integer id) {

        Training training = service.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Training not found"));
        return CourtConverter.toDto(training.court);
    }

}
