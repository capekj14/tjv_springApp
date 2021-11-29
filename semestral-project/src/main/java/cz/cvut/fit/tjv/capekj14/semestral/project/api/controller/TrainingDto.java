package cz.cvut.fit.tjv.capekj14.semestral.project.api.controller;

import java.time.LocalDateTime;
import java.util.Objects;

public class TrainingDto {

    public Integer idTraining;
    public int hours;
    public LocalDateTime start;

    public TrainingDto(Integer idTraining, int hours, LocalDateTime start) {
        this.idTraining = idTraining;
        this.hours = hours;
        this.start = start;
    }

    public TrainingDto() {
    }

    public Integer getIdTraining() {
        return idTraining;
    }

    public void setIdTraining(Integer idTraining) {
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
        TrainingDto that = (TrainingDto) o;
        return hours == that.hours && Objects.equals(idTraining, that.idTraining) && Objects.equals(start, that.start);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTraining, hours, start);
    }

    @Override
    public String toString() {
        return "TrainingDto{" +
                "idTraining='" + idTraining + '\'' +
                ", hours=" + hours +
                ", start=" + start +
                '}';
    }
}
