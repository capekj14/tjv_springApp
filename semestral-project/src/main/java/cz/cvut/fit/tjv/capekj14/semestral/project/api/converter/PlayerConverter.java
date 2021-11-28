package cz.cvut.fit.tjv.capekj14.semestral.project.api.converter;

import cz.cvut.fit.tjv.capekj14.semestral.project.api.controller.PlayerDto;
import cz.cvut.fit.tjv.capekj14.semestral.project.domain.Player;

import java.util.ArrayList;
import java.util.Collection;

public class PlayerConverter {
    public static PlayerDto toDto(Player player){
        return new PlayerDto(player.getIdPlayer(), player.getName(), player.getSurname(), player.getRating());
    }

    public static Player fromDto(PlayerDto playerDto){
        return new Player(playerDto.getIdPlayer(), playerDto.getName(), playerDto.getSurname(), playerDto.getRating());
    }

    public static Collection<Player> fromManyDtos (Collection<PlayerDto> playerDtos){
        Collection<Player> players = new ArrayList<>();
        playerDtos.forEach((u)-> players.add(fromDto(u)));
        return players;
    }

    public static Collection<PlayerDto> toManyDtos (Collection<Player> players){
        Collection<PlayerDto> playerDtos = new ArrayList<>();
        players.forEach((u)-> playerDtos.add(toDto(u)));
        return playerDtos;
    }

}
