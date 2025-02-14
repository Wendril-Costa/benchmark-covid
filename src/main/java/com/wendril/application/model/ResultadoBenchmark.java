package com.wendril.application.model;

import com.wendril.application.data.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import java.time.LocalDate;

@Entity
@Table(name = "resultado_benchmark")
public class ResultadoBenchmark extends AbstractEntity {
    @ManyToOne
    @JoinColumn(name = "benchmark_id")
    private Benchmark benchmark;
    @Column(name = "pais")
    private String pais;
    @Column(name = "total_casos_pais")
    private Integer totalCasosPais;
    @Column(name = "media_casos_pais")
    private Double mediaCasosPais;
    @Column(name = "max_casos_pais")
    private Integer maxCasosPais;
    @Column(name = "min_casos_pais")
    private Integer minCasosPais;
    @Column(name = "data_max_casos_pais")
    private LocalDate dataMaxCasosPais;
    @Column(name = "data_min_casos_pais")
    private LocalDate dataMinCasosPais;
    @Column(name = "total_mortes_pais")
    private Integer totalMortesPais;
    @Column(name = "media_mortes_pais")
    private Double mediaMortesPais;
    @Column(name = "max_mortes_pais")
    private Integer maxMortesPais;
    @Column(name = "min_mortes_pais")
    private Integer minMortesPais;
    @Column(name = "data_max_mortes_pais")
    private LocalDate dataMaxMortesPais;
    @Column(name = "data_min_mortes_pais")
    private LocalDate dataMinMortesPais;

    @Transient
    private Long idBenchmark;

    public Benchmark getBenchmark() {
        return benchmark;
    }

    public void setBenchmark(Benchmark benchmark) {
        this.benchmark = benchmark;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Integer getTotalCasosPais() {
        return totalCasosPais;
    }

    public void setTotalCasosPais(Integer totalCasosPais) {
        this.totalCasosPais = totalCasosPais;
    }

    public Double getMediaCasosPais() {
        return mediaCasosPais;
    }

    public void setMediaCasosPais(Double mediaCasosPais) {
        this.mediaCasosPais = mediaCasosPais;
    }

    public Integer getMaxCasosPais() {
        return maxCasosPais;
    }

    public void setMaxCasosPais(Integer maxCasosPais) {
        this.maxCasosPais = maxCasosPais;
    }

    public Integer getMinCasosPais() {
        return minCasosPais;
    }

    public void setMinCasosPais(Integer minCasosPais) {
        this.minCasosPais = minCasosPais;
    }

    public LocalDate getDataMaxCasosPais() {
        return dataMaxCasosPais;
    }

    public void setDataMaxCasosPais(LocalDate dataMaxCasosPais) {
        this.dataMaxCasosPais = dataMaxCasosPais;
    }

    public LocalDate getDataMinCasosPais() {
        return dataMinCasosPais;
    }

    public void setDataMinCasosPais(LocalDate dataMinCasosPais) {
        this.dataMinCasosPais = dataMinCasosPais;
    }

    public Integer getTotalMortesPais() {
        return totalMortesPais;
    }

    public void setTotalMortesPais(Integer totalMortesPais) {
        this.totalMortesPais = totalMortesPais;
    }

    public Double getMediaMortesPais() {
        return mediaMortesPais;
    }

    public void setMediaMortesPais(Double mediaMortesPais) {
        this.mediaMortesPais = mediaMortesPais;
    }

    public Integer getMaxMortesPais() {
        return maxMortesPais;
    }

    public void setMaxMortesPais(Integer maxMortesPais) {
        this.maxMortesPais = maxMortesPais;
    }

    public Integer getMinMortesPais() {
        return minMortesPais;
    }

    public void setMinMortesPais(Integer minMortessPais) {
        this.minMortesPais = minMortessPais;
    }

    public LocalDate getDataMaxMortesPais() {
        return dataMaxMortesPais;
    }

    public void setDataMaxMortesPais(LocalDate dataMaxMortesPais) {
        this.dataMaxMortesPais = dataMaxMortesPais;
    }

    public LocalDate getDataMinMortesPais() {
        return dataMinMortesPais;
    }

    public void setDataMinMortesPais(LocalDate dataMinMortesPais) {
        this.dataMinMortesPais = dataMinMortesPais;
    }

    public Long getIdBenchmark() {
        return idBenchmark;
    }

    public void setIdBenchmark(Long idBenchmark) {
        this.idBenchmark = getBenchmark().getId();
    }
}
