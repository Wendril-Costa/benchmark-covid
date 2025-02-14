package com.wendril.application.views.history;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.wendril.application.controller.BenchmarkController;
import com.wendril.application.model.Benchmark;
import com.wendril.application.model.ResultadoBenchmark;
import com.wendril.application.model.User;
import com.wendril.application.security.AuthenticatedUser;

import java.util.List;
import java.util.Optional;

public class HistoryMaster extends VerticalLayout {
    private final AuthenticatedUser authenticatedUser;
    private final BenchmarkController benchmarkController;
    private final Grid<ResultadoBenchmark> grid;
    private final Button btnDeletar;
    private ResultadoBenchmark resultadoSelecionado;
    private final Optional<User> maybeUser;

    public HistoryMaster(AuthenticatedUser authenticated, BenchmarkController benchmarkController) {
        this.authenticatedUser = authenticated;
        this.benchmarkController = benchmarkController;
        grid = new Grid<>();
        grid.setSizeFull();

        grid.addColumn(resultado -> resultado.getBenchmark().getId())
                .setHeader("ID Benchmark")
                .setWidth("150px");
        grid.addColumn(ResultadoBenchmark::getPais).setHeader("País").setWidth("150px");
        grid.addColumn(ResultadoBenchmark::getTotalCasosPais).setHeader("Total de Casos").setWidth("150px");
        grid.addColumn(ResultadoBenchmark::getMediaCasosPais).setHeader("Média de Casos").setWidth("150px");
        grid.addColumn(ResultadoBenchmark::getMaxCasosPais).setHeader("Máximo de Casos").setWidth("150px");
        grid.addColumn(ResultadoBenchmark::getMinCasosPais).setHeader("Mínimo de Casos").setWidth("150px");
        grid.addColumn(ResultadoBenchmark::getDataMaxCasosPais).setHeader("Data Máx. Casos").setWidth("150px");
        grid.addColumn(ResultadoBenchmark::getDataMinCasosPais).setHeader("Data Mín. Casos").setWidth("150px");
        grid.addColumn(ResultadoBenchmark::getTotalMortesPais).setHeader("Total de Mortes").setWidth("150px");
        grid.addColumn(ResultadoBenchmark::getMediaMortesPais).setHeader("Média de Mortes").setWidth("150px");
        grid.addColumn(ResultadoBenchmark::getMaxMortesPais).setHeader("Máximo de Mortes").setWidth("150px");
        grid.addColumn(ResultadoBenchmark::getMinMortesPais).setHeader("Mínimo de Mortes").setWidth("150px");
        grid.addColumn(ResultadoBenchmark::getDataMaxMortesPais).setHeader("Data Máx. Mortes").setWidth("150px");
        grid.addColumn(ResultadoBenchmark::getDataMinMortesPais).setHeader("Data Mín. Mortes").setWidth("150px");

        btnDeletar = new Button("Deletar");
        btnDeletar.addThemeVariants(ButtonVariant.LUMO_ERROR);
        btnDeletar.setEnabled(false);

        grid.asSingleSelect().addValueChangeListener(event -> {
            resultadoSelecionado = event.getValue();
            boolean itemSelecionado = resultadoSelecionado != null;
            btnDeletar.setEnabled(itemSelecionado);
        });

        btnDeletar.addClickListener(event -> {
            if (resultadoSelecionado != null) {
                deletarResultado(resultadoSelecionado);
            }
        });
        HorizontalLayout buttonLayout = new HorizontalLayout(btnDeletar);
        buttonLayout.setJustifyContentMode(JustifyContentMode.START);
        buttonLayout.setWidthFull();

        setSizeFull();
        setAlignItems(Alignment.STRETCH);
        setJustifyContentMode(JustifyContentMode.CENTER);
        setFlexGrow(1, grid);

        maybeUser = authenticatedUser.get();
        maybeUser.ifPresent(this::loadBenchmarksByUser);

        add(buttonLayout, grid);
    }

    private void loadBenchmarksByUser(User user) {
        List<Benchmark> benchmarks = benchmarkController.findBenchmarksByUser(user);
        List<ResultadoBenchmark> resultados = benchmarks.stream()
                .flatMap(b -> b.getResultados().stream())
                .toList();
        grid.setItems(resultados);
    }

    private void deletarResultado(ResultadoBenchmark resultado) {
        try {
            benchmarkController.deleteResultadoBenchmark(resultado);
            maybeUser.ifPresent(this::loadBenchmarksByUser);
            Notification.show("Item deletado com sucesso!", 2000, Notification.Position.MIDDLE);
        } catch (Exception e) {
            Notification.show("Erro ao deletar item!", 2000, Notification.Position.MIDDLE);
        }
    }
}
