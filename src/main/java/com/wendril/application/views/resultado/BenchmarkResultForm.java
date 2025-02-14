package com.wendril.application.views.resultado;

import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.BigDecimalField;
import com.wendril.application.controller.ResultadoBenchmarkController;
import com.wendril.application.model.ResultadoBenchmark;
import com.wendril.application.services.BenchmarkStateService;
import com.wendril.application.utils.DatePickerPT;

import java.math.BigDecimal;
import java.util.List;

public class BenchmarkResultForm extends VerticalLayout {
    private final ResultadoBenchmarkController controller;
    private final FormLayout div = new FormLayout();
    private final Span space1 = new Span();
    private final Span space2 = new Span();
    private BigDecimalField totalCasosPais1;
    private BigDecimalField mediaCasosPais1;
    private DatePickerPT dataMaxCasosPais1;
    private BigDecimalField maxCasosPais1;
    private DatePickerPT dataMinCasosPais1;
    private BigDecimalField minCasosPais1;
    private BigDecimalField totalMortesPais1;
    private BigDecimalField mediaMortesPais1;
    private DatePickerPT dataMaxMortesPais1;
    private BigDecimalField maxMortesPais1;
    private DatePickerPT dataMinMortesPais1;
    private BigDecimalField minMortesPais1;
    private BigDecimalField totalCasosPais2;
    private BigDecimalField mediaCasosPais2;
    private DatePickerPT dataMaxCasosPais2;
    private BigDecimalField maxCasosPais2;
    private DatePickerPT dataMinCasosPais2;
    private BigDecimalField minCasosPais2;
    private BigDecimalField totalMortesPais2;
    private BigDecimalField mediaMortesPais2;
    private DatePickerPT dataMaxMortesPais2;
    private BigDecimalField maxMortesPais2;
    private DatePickerPT dataMinMortesPais2;
    private BigDecimalField minMortesPais2;
    private FormLayout formLayoutPais2;
    private FormLayout formLayoutPais1;
    private FormLayout formLayout;

    private final List<ResultadoBenchmark> benchmark;
    private ResultadoBenchmark benchmarkPais1;
    private ResultadoBenchmark benchmarkPais2;
    private final BenchmarkStateService benchmarkStateService;

    public BenchmarkResultForm(ResultadoBenchmarkController resultadoBenchmarkController, Long idBenchmark,
                               BenchmarkStateService benchmarkStateService) {
        this.controller = resultadoBenchmarkController;
        this.benchmarkStateService = benchmarkStateService;
        List<ResultadoBenchmark> resultados = benchmarkStateService.getCurrentResultados();
        if (resultados != null) {
            benchmark = resultados;
        } else {
            benchmark = controller.loadByBenchmark(idBenchmark);
        }


        benchmarkPais1 = benchmark.getFirst();
        benchmarkPais2 = benchmark.getLast();

        setDadosPais1();
        setDadosPais2();

        setWidthFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        setSpacing(true);

        setLayoutPais1();
        setLayoutPais2();

        setFormLayout();

        add(formLayout);
    }

    private void setFormLayout() {
        formLayout = new FormLayout();
        formLayout.add(formLayoutPais1, div, formLayoutPais2);
        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1, FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("600px", 9, FormLayout.ResponsiveStep.LabelsPosition.TOP)
        );
        formLayout.setColspan(formLayoutPais1, 3);
        formLayout.setColspan(div, 3);
        formLayout.setColspan(formLayoutPais2, 3);
    }

    private void setLayoutPais1() {
        String formattedName = benchmarkPais1.getPais().toLowerCase();
        formattedName = formattedName.substring(0, 1).toUpperCase() + formattedName.substring(1);

        Span namePais1 = new Span(formattedName);
        formLayoutPais1 = new FormLayout();
        formLayoutPais1.add(namePais1, space1, totalCasosPais1, mediaCasosPais1, dataMaxCasosPais1, maxCasosPais1, dataMinCasosPais1,
                minCasosPais1, totalMortesPais1, mediaMortesPais1, dataMaxMortesPais1, maxMortesPais1, dataMinMortesPais1,
                minMortesPais1);
        formLayoutPais1.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 2, FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("450px", 2, FormLayout.ResponsiveStep.LabelsPosition.TOP)
        );
        formLayoutPais1.setColspan(space1, 1);

        formLayoutPais1.setColspan(totalCasosPais1, 1);
        formLayoutPais1.setColspan(mediaCasosPais1, 1);
        formLayoutPais1.setColspan(dataMaxCasosPais1, 1);
        formLayoutPais1.setColspan(maxCasosPais1, 1);
        formLayoutPais1.setColspan(dataMinCasosPais1, 1);
        formLayoutPais1.setColspan(minCasosPais1, 1);

        formLayoutPais1.setColspan(totalMortesPais1, 1);
        formLayoutPais1.setColspan(mediaMortesPais1, 1);
        formLayoutPais1.setColspan(dataMaxMortesPais1, 1);
        formLayoutPais1.setColspan(maxMortesPais1, 1);
        formLayoutPais1.setColspan(dataMinMortesPais1, 1);
        formLayoutPais1.setColspan(minMortesPais1, 1);
    }

    private void setLayoutPais2() {
        String formattedName = benchmarkPais2.getPais().toLowerCase();
        formattedName = formattedName.substring(0, 1).toUpperCase() + formattedName.substring(1);
        Span namePais2 = new Span(formattedName);
        formLayoutPais2 = new FormLayout();
        formLayoutPais2.add(namePais2, space2, totalCasosPais2, mediaCasosPais2, dataMaxCasosPais2, maxCasosPais2, dataMinCasosPais2,
                minCasosPais2, totalMortesPais2, mediaMortesPais2, dataMaxMortesPais2, maxMortesPais2, dataMinMortesPais2,
                minMortesPais2);
        formLayoutPais2.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 2, FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("450px", 2, FormLayout.ResponsiveStep.LabelsPosition.TOP)
        );

        formLayoutPais2.setColspan(space2, 1);

        formLayoutPais2.setColspan(totalCasosPais2, 1);
        formLayoutPais2.setColspan(mediaCasosPais2, 1);
        formLayoutPais2.setColspan(dataMaxCasosPais2, 1);
        formLayoutPais2.setColspan(maxCasosPais2, 1);
        formLayoutPais2.setColspan(dataMinCasosPais2, 1);
        formLayoutPais2.setColspan(minCasosPais2, 1);

        formLayoutPais2.setColspan(totalMortesPais2, 1);
        formLayoutPais2.setColspan(mediaMortesPais2, 1);
        formLayoutPais2.setColspan(dataMaxMortesPais2, 1);
        formLayoutPais2.setColspan(maxMortesPais2, 1);
        formLayoutPais2.setColspan(dataMinMortesPais2, 1);
        formLayoutPais2.setColspan(minMortesPais2, 1);
    }


    private void setDadosPais1() {
        if (benchmarkPais1 == null) {
            List<ResultadoBenchmark> resultados = benchmarkStateService.getCurrentResultados();
            if (resultados != null && !resultados.isEmpty()) {
                benchmarkPais1 = resultados.getFirst();
            }
        }

        totalCasosPais1 = new BigDecimalField("Total de casos");
        totalCasosPais1.setValue(new BigDecimal(benchmarkPais1.getTotalCasosPais()));
        totalCasosPais1.setReadOnly(true);

        mediaCasosPais1 = new BigDecimalField("Media de casos");
        mediaCasosPais1.setValue(BigDecimal.valueOf(benchmarkPais1.getMediaCasosPais()));
        mediaCasosPais1.setReadOnly(true);

        dataMaxCasosPais1 = new DatePickerPT("Data com maior casos");
        dataMaxCasosPais1.setValue(benchmarkPais1.getDataMaxCasosPais());
        dataMaxCasosPais1.setReadOnly(true);

        maxCasosPais1 = new BigDecimalField("Maior numero de casos");
        maxCasosPais1.setValue(new BigDecimal(benchmarkPais1.getMaxCasosPais()));
        maxCasosPais1.setReadOnly(true);

        dataMinCasosPais1 = new DatePickerPT("Data com menor casos");
        dataMinCasosPais1.setValue(benchmarkPais1.getDataMinCasosPais());
        dataMinCasosPais1.setReadOnly(true);

        minCasosPais1 = new BigDecimalField("Menor numero de casos");
        minCasosPais1.setValue(BigDecimal.valueOf(benchmarkPais1.getMinCasosPais()));
        minCasosPais1.setReadOnly(true);

        totalMortesPais1 = new BigDecimalField("Total de mortes");
        totalMortesPais1.setValue(new BigDecimal(benchmarkPais1.getTotalMortesPais()));
        totalMortesPais1.setReadOnly(true);

        mediaMortesPais1 = new BigDecimalField("Media de mortes");
        mediaMortesPais1.setValue(BigDecimal.valueOf(benchmarkPais1.getMediaMortesPais()));
        mediaMortesPais1.setReadOnly(true);

        dataMaxMortesPais1 = new DatePickerPT("Data com maior mortes");
        dataMaxMortesPais1.setValue(benchmarkPais1.getDataMaxMortesPais());
        dataMaxMortesPais1.setReadOnly(true);

        maxMortesPais1 = new BigDecimalField("Maior numero de mortes");
        maxMortesPais1.setValue(BigDecimal.valueOf(benchmarkPais1.getMaxMortesPais()));
        maxMortesPais1.setReadOnly(true);

        dataMinMortesPais1 = new DatePickerPT("Data com menor mortes");
        dataMinMortesPais1.setValue(benchmarkPais1.getDataMinMortesPais());
        dataMinMortesPais1.setReadOnly(true);

        minMortesPais1 = new BigDecimalField("Menor numero de mortes");
        minMortesPais1.setValue(BigDecimal.valueOf(benchmarkPais1.getMinMortesPais()));
        minMortesPais1.setReadOnly(true);
    }

    private void setDadosPais2() {
        if (benchmarkPais2 == null) {
            List<ResultadoBenchmark> resultados = benchmarkStateService.getCurrentResultados();
            if (resultados != null && !resultados.isEmpty()) {
                benchmarkPais2 = resultados.getLast();
            }
        }
        totalCasosPais2 = new BigDecimalField("Total de casos");
        totalCasosPais2.setValue(new BigDecimal(benchmarkPais2.getTotalCasosPais()));
        totalCasosPais2.setReadOnly(true);

        mediaCasosPais2 = new BigDecimalField("Media de casos");
        mediaCasosPais2.setValue(BigDecimal.valueOf(benchmarkPais2.getMediaCasosPais()));
        mediaCasosPais2.setReadOnly(true);

        dataMaxCasosPais2 = new DatePickerPT("Data com maior casos");
        dataMaxCasosPais2.setValue(benchmarkPais2.getDataMaxCasosPais());
        dataMaxCasosPais2.setReadOnly(true);

        maxCasosPais2 = new BigDecimalField("Maior numero de casos");
        maxCasosPais2.setValue(new BigDecimal(benchmarkPais2.getMaxCasosPais()));
        maxCasosPais2.setReadOnly(true);

        dataMinCasosPais2 = new DatePickerPT("Data com menor casos");
        dataMinCasosPais2.setValue(benchmarkPais2.getDataMinCasosPais());
        dataMinCasosPais2.setReadOnly(true);

        minCasosPais2 = new BigDecimalField("Menor numero de casos");
        minCasosPais2.setValue(BigDecimal.valueOf(benchmarkPais2.getMinCasosPais()));
        minCasosPais2.setReadOnly(true);

        totalMortesPais2 = new BigDecimalField("Total de mortes");
        totalMortesPais2.setValue(new BigDecimal(benchmarkPais2.getTotalMortesPais()));
        totalMortesPais2.setReadOnly(true);

        mediaMortesPais2 = new BigDecimalField("Media de mortes");
        mediaMortesPais2.setValue(BigDecimal.valueOf(benchmarkPais2.getMediaMortesPais()));
        mediaMortesPais2.setReadOnly(true);

        dataMaxMortesPais2 = new DatePickerPT("Data com maior mortes");
        dataMaxMortesPais2.setValue(benchmarkPais2.getDataMaxMortesPais());
        dataMaxMortesPais2.setReadOnly(true);

        maxMortesPais2 = new BigDecimalField("Maior numero de mortes");
        maxMortesPais2.setValue(BigDecimal.valueOf(benchmarkPais2.getMaxMortesPais()));
        maxMortesPais2.setReadOnly(true);

        dataMinMortesPais2 = new DatePickerPT("Data com menor mortes");
        dataMinMortesPais2.setValue(benchmarkPais2.getDataMinMortesPais());
        dataMinMortesPais2.setReadOnly(true);

        minMortesPais2 = new BigDecimalField("Menor numero de mortes");
        minMortesPais2.setValue(BigDecimal.valueOf(benchmarkPais2.getMinMortesPais()));
        minMortesPais2.setReadOnly(true);
    }

}
