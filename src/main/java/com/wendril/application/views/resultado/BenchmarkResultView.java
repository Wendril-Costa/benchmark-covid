package com.wendril.application.views.resultado;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.wendril.application.controller.ResultadoBenchmarkController;
import com.wendril.application.model.ResultadoBenchmark;
import com.wendril.application.services.BenchmarkStateService;
import com.wendril.application.utils.NotificationComponent;
import com.wendril.application.views.MainLayout;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

import java.io.Serial;
import java.util.List;

@PageTitle("Benchmark Result")
@Route(value = "benchmark-result", layout = MainLayout.class)
@Menu(order = 1, icon = LineAwesomeIconUrl.CLIPBOARD_LIST_SOLID)
@PermitAll
public class BenchmarkResultView extends VerticalLayout implements BeforeEnterObserver, HasUrlParameter<String> {
    @Serial
    private static final long serialVersionUID = 1L;
    private final ResultadoBenchmarkController resultadoBenchmarkController;
    private final BenchmarkStateService benchmarkStateService;
    private Long idBenchmark;
    private NotificationComponent notification;
    private BenchmarkResultForm benchmarkResultForm;

    public BenchmarkResultView(@Autowired ResultadoBenchmarkController resultadoBenchmarkController,
                               BenchmarkStateService benchmarkStateService) {
        this.resultadoBenchmarkController = resultadoBenchmarkController;
        this.benchmarkStateService = benchmarkStateService;
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        List<ResultadoBenchmark> currentResultados = benchmarkStateService.getCurrentResultados();
        if (currentResultados != null) {
            loadBenchmarkResultForm();
        }

        notification = new NotificationComponent("Gerado resultado",
                "success",
                "top_end");

        add(notification);
    }

    @Override
    public void setParameter(BeforeEvent event, @OptionalParameter String parameter) {
        if (parameter != null) {
            this.idBenchmark = Long.parseLong(parameter);
            List<ResultadoBenchmark> resultados = resultadoBenchmarkController.loadByBenchmark(idBenchmark);

            benchmarkStateService.setCurrentResultados(resultados);

            loadBenchmarkResultForm();
        }
    }

    private void loadBenchmarkResultForm() {
        getChildren().filter(child -> child instanceof BenchmarkResultForm).forEach(this::remove);

        benchmarkResultForm = new BenchmarkResultForm(resultadoBenchmarkController, idBenchmark,
                benchmarkStateService);
        setHorizontalComponentAlignment(Alignment.CENTER, benchmarkResultForm);
        add(benchmarkResultForm);
    }
}
