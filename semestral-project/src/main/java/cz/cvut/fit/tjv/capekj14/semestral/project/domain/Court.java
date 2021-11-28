package cz.cvut.fit.tjv.capekj14.semestral.project.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;
import java.util.Objects;

@Entity(name = "Court_tjv")
public class Court {

    @Id
    @GeneratedValue
    public Integer idCourt;
    public int number;

    @OneToMany(mappedBy = "court")
    private Collection<Training> trainings;

    public Court(Integer idCourt, int number) {
        this.idCourt = idCourt;
        this.number = number;
    }

    public Court() {
    }

    public Integer getIdCourt() {
        return idCourt;
    }

    public void setIdCourt(Integer idCourt) {
        this.idCourt = idCourt;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    /*
    public Collection<Training> getTrainings() {
        return trainings;
    }

    public void setTrainings(Collection<Training> trainings) {
        this.trainings = trainings;
    }
    */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Court court = (Court) o;
        return number == court.number && Objects.equals(idCourt, court.idCourt) && Objects.equals(trainings, court.trainings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCourt, number, trainings);
    }

    @Override
    public String toString() {
        return "Court{" +
                "idCourt='" + idCourt + '\'' +
                ", number=" + number +
                ", trainings=" + trainings +
                '}';
    }
}
