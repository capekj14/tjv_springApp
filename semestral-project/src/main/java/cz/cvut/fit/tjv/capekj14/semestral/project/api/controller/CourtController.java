package cz.cvut.fit.tjv.capekj14.semestral.project.api.controller;

import cz.cvut.fit.tjv.capekj14.semestral.project.api.converter.CourtConverter;
import cz.cvut.fit.tjv.capekj14.semestral.project.bussines.CourtService;
import cz.cvut.fit.tjv.capekj14.semestral.project.bussines.EntityStateException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@RestController
public class CourtController {

    CourtService service;

    public CourtController(CourtService service) {
        this.service = service;
    }

    @GetMapping("/courts")
    Collection<CourtDto> getAllCourts() {
        return CourtConverter.toManyDtos(service.findAll());
    }

    @GetMapping("/courts/{id}")
    CourtDto getOneCourt(@PathVariable Integer id){

        return CourtConverter.toDto(service.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Court not found")));
    }

    @PutMapping("/courts/{id}")
    CourtDto updateCourt(@RequestBody CourtDto courtDto, @PathVariable Integer id) {
        service.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Court not found"));
        try{
            service.update(CourtConverter.fromDto(courtDto));
        }catch (EntityStateException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "updating error");
            }

        return courtDto;
    }

    @DeleteMapping("/courts/{id}")
    void deleteCourt(@PathVariable Integer id) {
            service.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Court not found"));
            service.deleteById(id);
        return;

    }
    @PostMapping("/courts")
    CourtDto addCourt(@RequestBody CourtDto courtDto){
        try {
            service.create(CourtConverter.fromDto(courtDto));

        }catch (EntityStateException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "bad request");
        }

        return courtDto;
    }
}
