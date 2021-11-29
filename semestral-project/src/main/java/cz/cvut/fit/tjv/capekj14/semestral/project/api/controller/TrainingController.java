package cz.cvut.fit.tjv.capekj14.semestral.project.api.controller;

import cz.cvut.fit.tjv.capekj14.semestral.project.api.converter.TrainingConverter;
import cz.cvut.fit.tjv.capekj14.semestral.project.bussines.EntityStateException;
import cz.cvut.fit.tjv.capekj14.semestral.project.bussines.TrainingService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@RestController
public class TrainingController {

    TrainingService service;

    public TrainingController(TrainingService service) {
        this.service = service;
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

}
