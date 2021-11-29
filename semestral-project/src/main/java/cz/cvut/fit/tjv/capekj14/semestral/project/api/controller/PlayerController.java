package cz.cvut.fit.tjv.capekj14.semestral.project.api.controller;

import cz.cvut.fit.tjv.capekj14.semestral.project.api.converter.PlayerConverter;
import cz.cvut.fit.tjv.capekj14.semestral.project.bussines.EntityStateException;
import cz.cvut.fit.tjv.capekj14.semestral.project.bussines.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@RestController
public class PlayerController {

    PlayerService service;

    public PlayerController(PlayerService service) {
        this.service = service;
    }

    @GetMapping("/players")
    Collection<PlayerDto> getAllPlayers() {
        return PlayerConverter.toManyDtos(service.findAll());
    }

    @GetMapping("/players/{id}")
    PlayerDto getOnePlayer(@PathVariable Integer id){

        return PlayerConverter.toDto(service.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Player not found")));
    }

    @PutMapping("/players/{id}")
    PlayerDto updatePlayer(@RequestBody PlayerDto playerDto, @PathVariable Integer id) {
        service.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Player not found"));
        try{
            service.update(PlayerConverter.fromDto(playerDto));
        }catch (EntityStateException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "updating error");
        }

        return playerDto;
    }

    @DeleteMapping("/players/{id}")
    void deletePlayer(@PathVariable Integer id) {
        service.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Player not found"));
        service.deleteById(id);
        return;
    }

    @PostMapping("/players")
    PlayerDto addPlayer(@RequestBody PlayerDto playerDto) {
        try{
            service.create(PlayerConverter.fromDto(playerDto));
        }catch (EntityStateException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "bad request");
        }
        return playerDto;
    }
}
