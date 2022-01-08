package client.ui;

import client.dto.TrainingDto;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class TrainingView {

    public void printTraining (TrainingDto o) {
        System.out.println(" " + o.getIdTraining() + ", " + o.getHours() +
                ", " + o.getStart() + " ");
    }

    void printAllTrainings(Collection<TrainingDto> trainings) {
        trainings.forEach(o -> System.out.println(" " + o.getIdTraining() + ", " + o.getHours() +
                ", " + o.getStart() + " "));
    }
}
