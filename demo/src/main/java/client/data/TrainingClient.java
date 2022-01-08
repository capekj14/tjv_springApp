package client.data;

import client.dto.TrainingDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;

import java.time.Duration;
import java.util.Collection;

@Component
public class TrainingClient {
    public final WebClient trainingWebClient;
    public Integer currentTrainingId;

    public TrainingClient(@Value("${training_database_url}") String backendUrl) {
        this.trainingWebClient = WebClient.create(backendUrl + "/trainings");
    }

    public Integer getCurrentTrainingId() {
        return currentTrainingId;
    }

    public void setCurrentTrainingId(Integer currentId) {
        try {
            this.currentTrainingId = currentId;
            if (currentId != null) {
                findById();
            }
        }   catch (WebClientException e) {
                this.currentTrainingId = null;
                throw  e;
        }
    }

    public TrainingDto create (TrainingDto trainingDto) {
        return trainingWebClient.post()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(trainingDto)
                .retrieve()
                .bodyToMono(TrainingDto.class)
                .block();
    }
    public Collection<TrainingDto> readAll() {
        return trainingWebClient.get() // HTTP GET
                .retrieve() // request specification finished
                .bodyToFlux(TrainingDto.class) // interpret response body as a collection
                .collectList() // collect all elements as list
                .block(Duration.ofSeconds(5)); // call synchronously with timeout
    }

    public TrainingDto findById() {
        if (currentTrainingId == null)
            throw new IllegalStateException("No trainingId set");
        return trainingWebClient.get()
                .uri("/{id}", currentTrainingId).retrieve()
                .bodyToMono(TrainingDto.class)
                .block();
    }

    public void update(TrainingDto training) {
        if(currentTrainingId == null)
            throw new IllegalStateException("current id not set");
        training.setIdTraining(currentTrainingId);
        trainingWebClient.put()
                .uri("/{id}", currentTrainingId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(training)
                .retrieve()
                .toBodilessEntity();

    }

    public void delete() {
        if(currentTrainingId == null)
            throw new IllegalStateException("current id not set");
        trainingWebClient.delete()
                .uri("/{id}", currentTrainingId)
                .retrieve()
                .toBodilessEntity()
                .subscribe(
                        x -> setCurrentTrainingId(null)
                );
    }


}
