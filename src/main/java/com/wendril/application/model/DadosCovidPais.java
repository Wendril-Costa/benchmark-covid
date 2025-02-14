package com.wendril.application.model;

import com.wendril.application.data.AbstractEntity;

import java.time.LocalDate;

public class DadosCovidPais extends AbstractEntity {
    private LocalDate data;
    private Integer totalMortes;
    private Integer newMortes;
    private Integer totalCasos;
    private Integer newCasos;

    public DadosCovidPais(LocalDate data, Integer totalMortes, Integer newMortes, Integer totalCasos, Integer newCasos) {
        this.data = data;
        this.totalMortes = totalMortes;
        this.newMortes = newMortes;
        this.totalCasos = totalCasos;
        this.newCasos = newCasos;
    }

    public DadosCovidPais() {
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Integer getTotalMortes() {
        return totalMortes;
    }

    public void setTotalMortes(Integer totalMortes) {
        this.totalMortes = totalMortes;
    }

    public Integer getNewMortes() {
        return newMortes;
    }

    public void setNewMortes(Integer newMortes) {
        this.newMortes = newMortes;
    }

    public Integer getTotalCasos() {
        return totalCasos;
    }

    public void setTotalCasos(Integer totalCasos) {
        this.totalCasos = totalCasos;
    }

    public Integer getNewCasos() {
        return newCasos;
    }

    public void setNewCasos(Integer newCasos) {
        this.newCasos = newCasos;
    }
}
