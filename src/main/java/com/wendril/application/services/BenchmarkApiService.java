package com.wendril.application.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.wendril.application.controller.CovidApiController;
import com.wendril.application.controller.TranslateApiGoogleController;
import com.wendril.application.model.DadosCovidPais;
import com.wendril.application.model.ResultadoBenchmark;
import com.wendril.application.utils.ConverterJsonToDadosCovidPais;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class BenchmarkApiService {
    @Autowired
    private CovidApiController covidApiController;

    @Autowired
    private TranslateApiGoogleController translateApiGoogleController;

    private List<DadosCovidPais> dadosCovidPais1List;
    private List<DadosCovidPais> dadosCovidPais2List;

    public List<ResultadoBenchmark> comparePaises(String pais1, String pais2, LocalDate dataInicial, LocalDate dataFinal, boolean bean) {
        String dataInicialStr = dataInicial.toString();
        String dataFinalStr = dataFinal.toString();

        JsonNode casesNodePais1 = getApiCovidPais(dataInicialStr, dataFinalStr, "cases", pais1);
        JsonNode deathsNodePais1 = getApiCovidPais(dataInicialStr, dataFinalStr, "deaths", pais1);

        JsonNode casesNodePais2 = getApiCovidPais(dataInicialStr, dataFinalStr, "cases", pais2);
        JsonNode deathsNodePais2 = getApiCovidPais(dataInicialStr, dataFinalStr, "deaths", pais2);

        if (bean) {
            dadosCovidPais1List = ConverterJsonToDadosCovidPais
                    .convert(casesNodePais1, deathsNodePais1, dataInicialStr, dataFinalStr);
            dadosCovidPais2List = ConverterJsonToDadosCovidPais
                    .convert(casesNodePais2, deathsNodePais2, dataInicialStr, dataFinalStr);
        }

        ResultadoBenchmark resultadoBenchmarkPais1 = new ResultadoBenchmark();
        getDadosJson(casesNodePais1, dataInicialStr, dataFinalStr, resultadoBenchmarkPais1, "cases");
        getDadosJson(deathsNodePais1, dataInicialStr, dataFinalStr, resultadoBenchmarkPais1, "deaths");
        resultadoBenchmarkPais1.setPais(pais1);

        ResultadoBenchmark resultadoBenchmarkPais2 = new ResultadoBenchmark();
        getDadosJson(casesNodePais2, dataInicialStr, dataFinalStr, resultadoBenchmarkPais2, "cases");
        getDadosJson(deathsNodePais2, dataInicialStr, dataFinalStr, resultadoBenchmarkPais2, "deaths");
        resultadoBenchmarkPais2.setPais(pais2);

        List<ResultadoBenchmark> resultados = new ArrayList<>();
        resultados.add(resultadoBenchmarkPais1);
        resultados.add(resultadoBenchmarkPais2);

        return resultados;
    }

    private JsonNode getApiCovidPais(String dataInicial, String dataFinal, String cases, String pais) {
        String paisTranslate = translateApiGoogleController.fechTranslate(pais);
        JsonNode response = covidApiController.fetchCovidData(paisTranslate, cases);
        JsonNode node = response.get(0).get(cases.equals("cases") ? "cases" : "deaths");
        ObjectNode filteredCases = new ObjectMapper().createObjectNode();

        node.fieldNames().forEachRemaining(date -> {
            if (date.compareTo(dataInicial) >= 0 && date.compareTo(dataFinal) <= 0) {
                filteredCases.set(date, node.get(date));
            }
        });
        return node;
    }

    private void getDadosJson(JsonNode node, String dataInicial, String dataFinal, ResultadoBenchmark resultadoFinalBenchmark, String aCase) {
        int somaNew = 0;
        int count = 0;
        int maiorNew = 0;
        int menorNew = Integer.MAX_VALUE;
        String dataMaiorNew = "";
        String dataMenorNew = "";
        int totalFinalCasos = 0;
        boolean todosIguais = true;
        Integer primeiroValor = null;

        for (Iterator<String> it = node.fieldNames(); it.hasNext(); ) {
            String date = it.next();
            if (date.compareTo(dataInicial) >= 0 && date.compareTo(dataFinal) <= 0) {
                JsonNode dailyData = node.get(date);
                int newCases = dailyData.get("new").asInt();
                int totalCases = dailyData.get("total").asInt();

                if (primeiroValor == null) {
                    primeiroValor = newCases;
                } else if (primeiroValor != newCases) {
                    todosIguais = false;
                }

                somaNew += newCases;
                count++;

                if (newCases > maiorNew) {
                    maiorNew = newCases;
                    dataMaiorNew = date;
                }

                if (newCases < menorNew) {
                    menorNew = newCases;
                    dataMenorNew = date;
                }

                totalFinalCasos = totalCases;
            }
        }

        if (todosIguais) {
            dataMaiorNew = dataFinal;
            dataMenorNew = dataInicial;
        }
        Double mediaNew = count > 0 ? (double) somaNew / count : 0;

        if (aCase.equals("cases")) {
            resultadoFinalBenchmark.setTotalCasosPais(totalFinalCasos);
            resultadoFinalBenchmark.setMediaCasosPais(mediaNew);
            resultadoFinalBenchmark.setMaxCasosPais(maiorNew);
            resultadoFinalBenchmark.setMinCasosPais(menorNew);
            resultadoFinalBenchmark.setDataMaxCasosPais(LocalDate.parse(dataMaiorNew));
            resultadoFinalBenchmark.setDataMinCasosPais(LocalDate.parse(dataMenorNew));
        } else {
            resultadoFinalBenchmark.setTotalMortesPais(totalFinalCasos);
            resultadoFinalBenchmark.setMediaMortesPais(mediaNew);
            resultadoFinalBenchmark.setMaxMortesPais(maiorNew);
            resultadoFinalBenchmark.setMinMortesPais(menorNew);
            resultadoFinalBenchmark.setDataMaxMortesPais(LocalDate.parse(dataMaiorNew));
            resultadoFinalBenchmark.setDataMinMortesPais(LocalDate.parse(dataMenorNew));
        }
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
}
