package com.wendril.application.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.wendril.application.services.CovidApiService;
import org.springframework.stereotype.Controller;

@Controller
public class CovidApiController {
    private final CovidApiService covid19Service;

    public CovidApiController(CovidApiService covid19Service) {
        this.covid19Service = covid19Service;
    }

    public JsonNode fetchCovidData(String country, String cases) {
        return covid19Service.getCovidData(country, cases);
    }

}
