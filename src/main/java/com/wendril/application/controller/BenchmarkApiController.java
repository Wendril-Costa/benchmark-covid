package com.wendril.application.controller;

import com.wendril.application.model.ResultadoBenchmark;
import com.wendril.application.services.BenchmarkApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BenchmarkApiController {
    @Autowired
    private BenchmarkApiService benchmarkApiService;

//    GET http://localhost:8080/api/compare/brazil/canada?from=2020-03-08&to=2020-03-13
    @GetMapping("/compare/{pais1}/{pais2}")
    public List<ResultadoBenchmark> comparePaises(
            @PathVariable String pais1,
            @PathVariable String pais2,
            @RequestParam LocalDate from,
            @RequestParam LocalDate to) {
        return benchmarkApiService.comparePaises(pais1, pais2, from, to, false);
    }
}
