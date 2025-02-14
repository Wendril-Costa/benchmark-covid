package com.wendril.application.services;

import com.wendril.application.model.Benchmark;
import com.wendril.application.model.DadosCovidPais;
import com.wendril.application.model.ResultadoBenchmark;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BenchmarkStateService {
    private Benchmark currentBenchmark;
    private List<DadosCovidPais> dadosCovidPais1List;
    private List<DadosCovidPais> dadosCovidPais2List;
    private List<ResultadoBenchmark> currentResultados;

    public Benchmark getCurrentBenchmark() {
        return currentBenchmark;
    }

    public void setCurrentBenchmark(Benchmark benchmark) {
        this.currentBenchmark = benchmark;
    }

    public List<DadosCovidPais> getDadosCovidPais1List() {
        return dadosCovidPais1List;
    }

    public void setDadosCovidPais1List(List<DadosCovidPais> dadosCovidPais1List) {
        this.dadosCovidPais1List = dadosCovidPais1List;
    }

    public List<DadosCovidPais> getDadosCovidPais2List() {
        return dadosCovidPais2List;
    }

    public void setDadosCovidPais2List(List<DadosCovidPais> dadosCovidPais2List) {
        this.dadosCovidPais2List = dadosCovidPais2List;
    }

    public List<ResultadoBenchmark> getCurrentResultados() {
        return currentResultados;
    }

    public void setCurrentResultados(List<ResultadoBenchmark> resultados) {
        this.currentResultados = resultados;
    }

    public void clearCurrentBenchmark() {
        this.currentBenchmark = null;
        this.dadosCovidPais1List = null;
        this.dadosCovidPais2List = null;
        this.currentResultados = null;
    }
}
