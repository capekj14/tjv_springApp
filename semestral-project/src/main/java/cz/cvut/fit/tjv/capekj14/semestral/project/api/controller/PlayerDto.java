package cz.cvut.fit.tjv.capekj14.semestral.project.api.controller;

import java.util.Objects;

public class PlayerDto {
    public String idPlayer;
    public String name;
    public String surname;
    public int rating;

    public PlayerDto(String idPlayer, String name, String surname, int rating) {
        this.idPlayer = idPlayer;
        this.name = name;
        this.surname = surname;
        this.rating = rating;
    }

    public String  getIdPlayer() {
        return idPlayer;
    }

    public void setIdPlayer(String idPlayer) {
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
        PlayerDto playerDto = (PlayerDto) o;
        return idPlayer == playerDto.idPlayer && rating == playerDto.rating && Objects.equals(name, playerDto.name) && Objects.equals(surname, playerDto.surname);
    }

    @Override
    public String toString() {
        return "PlayerDto{" +
                "idPlayer=" + idPlayer +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", rating=" + rating +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPlayer, name, surname, rating);
    }
}
