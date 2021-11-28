package cz.cvut.fit.tjv.capekj14.semestral.project.api.controller;

import java.util.Objects;

public class CourtDto {
    public Integer idCourt;
    public int number;

    public CourtDto() {
    }

    public CourtDto(Integer idCourt, int number) {
        this.idCourt = idCourt;
        this.number = number;
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
}
