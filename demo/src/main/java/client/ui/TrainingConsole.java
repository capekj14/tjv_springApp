package client.ui;

import client.data.TrainingClient;
import client.dto.TrainingDto;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.web.reactive.function.client.WebClientException;

@ShellComponent
public class TrainingConsole {

    public final TrainingClient trainingClient;
    public final TrainingView trainingView;

    public TrainingConsole(TrainingClient trainingClient, TrainingView trainingView) {
        this.trainingClient = trainingClient;
        this.trainingView = trainingView;
    }

    @ShellMethod("Get list of all trainings")
    public void getListOfAllTrainings() {
        var trainings = trainingClient.readAll();
        trainingView.printAllTrainings(trainings);
    }

    @ShellMethod("Place new training")
    public void createTraining(Integer trainingId) {
        try {
            trainingView.printTraining(this.trainingClient.create(new TrainingDto(trainingId)));
        } catch (WebClientException e) {
            System.out.println("Error");
        }
    }

    @ShellMethod("Set current training")
    public void setTraining (Integer id) {
        trainingClient.setCurrentTrainingId(id);
    }

    @ShellMethod("Unset current training (go back to scope of all trainings)")
    @ShellMethodAvailability ("currentTrainingNeededAvailability")
    public void unsetTraining () {
        trainingClient.setCurrentTrainingId(null);
    }

    public Availability currentTrainingNeededAvailability() {
        return trainingClient.getCurrentTrainingId() == null ? Availability.unavailable("Current" +
                " training needs to be set first") : Availability.available();
    }


    @ShellMethod("Retrieve details about selected training")
    @ShellMethodAvailability("currentTrainingNeededAvailability")
    public void printTraining() {
        var train = trainingClient.findById();
        trainingView.printTraining(train);
    }

    @ShellMethod("Update training")
    @ShellMethodAvailability("currentTrainingNeededAvailability")
    public void updateTraining(Integer newId) {
        var o = new TrainingDto(newId);
        trainingClient.update(o);
        System.out.println("Updated training");
    }

    @ShellMethod("Delete Training")
    @ShellMethodAvailability("currentTrainingNeededAvailability")
    public void deleteTraining() {
        trainingClient.delete();
        System.out.println("training deleted");
    }

}
