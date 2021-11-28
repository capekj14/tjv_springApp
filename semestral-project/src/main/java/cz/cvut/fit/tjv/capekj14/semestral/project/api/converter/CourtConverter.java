package cz.cvut.fit.tjv.capekj14.semestral.project.api.converter;

import cz.cvut.fit.tjv.capekj14.semestral.project.api.controller.CourtDto;
import cz.cvut.fit.tjv.capekj14.semestral.project.domain.Court;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class CourtConverter {
    public static CourtDto toDto (Court court){
        return new CourtDto(court.getIdCourt(), court.getNumber());
    }

    public static Court fromDto(CourtDto courtDto){
        return new Court(courtDto.getIdCourt(), courtDto.getNumber());
    }

    public static Collection<Court> fromManyDtos (Collection<CourtDto> courtDtos) {
        Collection<Court> courts = new ArrayList<>();
        courtDtos.forEach((u) -> courts.add(fromDto(u)));
        return courts;
    }

    public static Collection<CourtDto> toManyDtos(Collection<Court> courts){
        Collection<CourtDto> courtDtos = new ArrayList<>();
        courts.forEach(court -> courtDtos.add(toDto(court)));
        return courtDtos;
    }
}
