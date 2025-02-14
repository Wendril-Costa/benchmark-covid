package com.wendril.application.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.wendril.application.model.DadosCovidPais;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ConverterJsonToDadosCovidPais {
    public static List<DadosCovidPais> convert(JsonNode casesNode, JsonNode deathsNode, String dataInicial, String dataFinal) {
        List<DadosCovidPais> dadosCovidPaisList = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        Set<String> allDates = new HashSet<>();
        casesNode.fieldNames().forEachRemaining(allDates::add);
        deathsNode.fieldNames().forEachRemaining(allDates::add);

        for (String date : allDates) {
            if (date.compareTo(dataInicial) >= 0 && date.compareTo(dataFinal) <= 0) {
                int totalCasos = 0;
                int newCasos = 0;
                if (casesNode.has(date)) {
                    JsonNode caseData = casesNode.get(date);
                    totalCasos = caseData.get("total").asInt();
                    newCasos = caseData.get("new").asInt();
                }

                int totalMortes = 0;
                int newMortes = 0;
                if (deathsNode.has(date)) {
                    JsonNode deathData = deathsNode.get(date);
                    totalMortes = deathData.get("total").asInt();
                    newMortes = deathData.get("new").asInt();
                }

                DadosCovidPais dados = new DadosCovidPais(
                        LocalDate.parse(date, formatter),
                        totalMortes,
                        newMortes,
                        totalCasos,
                        newCasos
                );
                dadosCovidPaisList.add(dados);
            }
        }

        return dadosCovidPaisList;
    }
}
