package cz.cvut.fit.tjv.capekj14.semestral.project.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Collection;
import java.util.Objects;

@Entity(name = "Player_tjv")
public class Player {

    @Id
    @GeneratedValue
    public Integer idPlayer;

    public String name;
    public String surname;
    public int rating;

    @ManyToMany
    public Collection<Training> trainings;

    public Player(Integer idPlayer, String name, String surname, int rating) {
        this.idPlayer = idPlayer;
        this.name = name;
        this.surname = surname;
        this.rating = rating;
    }

    public Player() {
    }

    public Integer getIdPlayer() {
        return idPlayer;
    }

    public void setIdPlayer(Integer idPlayer) {
        this.idPlayer = idPlayer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return rating == player.rating && Objects.equals(idPlayer, player.idPlayer) && Objects.equals(name, player.name) && Objects.equals(surname, player.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPlayer, name, surname, rating);
    }

    @Override
    public String toString() {
        return "Player{" +
                "idPlayer='" + idPlayer + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", rating=" + rating +
                '}';
    }
}
