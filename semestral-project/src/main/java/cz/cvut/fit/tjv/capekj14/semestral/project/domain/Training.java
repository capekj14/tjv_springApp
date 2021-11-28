package cz.cvut.fit.tjv.capekj14.semestral.project.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;

@Entity(name = "Training_tjv")
public class Training {

    @Id
    public String idTraining;

    public int hours;

    public LocalDateTime start;

    @ManyToOne
    public Court court;
    @ManyToMany
    public Collection<Player> players;

    public Training(String idTraining, int hours, LocalDateTime start) {
        this.idTraining = idTraining;
        this.hours = hours;
        this.start = start;
    }

    public Training() {
    }

    public String getIdTraining() {
        return idTraining;
    }

    public void setIdTraining(String idTraining) {
        this.idTraining = idTraining;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Training training = (Training) o;
        return hours == training.hours && Objects.equals(idTraining, training.idTraining) && Objects.equals(start, training.start) && Objects.equals(court, training.court);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTraining, hours, start, court);
    }

    @Override
    public String toString() {
        return "Training{" +
                "idTraining='" + idTraining + '\'' +
                ", hours=" + hours +
                ", start=" + start +
                ", court=" + court +
                '}';
    }
}
