package cz.cvut.fit.tjv.capekj14.semestral.project.api.converter;

import cz.cvut.fit.tjv.capekj14.semestral.project.api.controller.TrainingDto;
import cz.cvut.fit.tjv.capekj14.semestral.project.domain.Training;

import java.util.ArrayList;
import java.util.Collection;

public class TrainingConverter {
    public static TrainingDto toDto(Training training){
        return new TrainingDto(training.getIdTraining(), training.getHours(), training.getStart());
    }

    public static Training fromDto(TrainingDto trainingDto){
        return new Training(trainingDto.getIdTraining(), trainingDto.getHours(), trainingDto.getStart());
    }

    public static Collection<Training> fromManyDtos (Collection<TrainingDto> trainingDtos){
        Collection<Training> trainings = new ArrayList<>();
        trainingDtos.forEach((u)-> trainings.add(fromDto(u)));
        return trainings;
    }

    public static Collection<TrainingDto> toManyDtos (Collection<Training> trainings){
        Collection<TrainingDto> trainingDtos = new ArrayList<>();
        trainings.forEach((u)-> trainingDtos.add(toDto(u)));
        return trainingDtos;
    }
}
