package com.wendril.application.views.benchmark;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.wendril.application.controller.BenchmarkController;
import com.wendril.application.controller.CovidApiController;
import com.wendril.application.controller.TranslateApiGoogleController;
import com.wendril.application.model.Benchmark;
import com.wendril.application.model.DadosCovidPais;
import com.wendril.application.security.AuthenticatedUser;
import com.wendril.application.services.BenchmarkApiService;
import com.wendril.application.services.BenchmarkStateService;
import com.wendril.application.utils.NotificationComponent;
import com.wendril.application.views.MainLayout;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

import java.io.Serial;
import java.util.List;

@PageTitle("Benchmark")
@Route(value = "", layout = MainLayout.class)
@Menu(order = 0, icon = LineAwesomeIconUrl.CHART_BAR)
@PermitAll
public class BenchmarkView extends VerticalLayout implements BeforeEnterObserver {
    @Serial
    private static final long serialVersionUID = 1L;
    private NotificationComponent notification;
    private final BenchmarkForm benchmarkForm;
    private final BenchmarkStateService benchmarkStateService;

    public BenchmarkView(@Autowired BenchmarkController controller, @Autowired AuthenticatedUser authenticatedUser,
                         CovidApiController covidApiController, TranslateApiGoogleController translateApiGoogleController,
                         BenchmarkStateService benchmarkStateService, BenchmarkApiService benchmarkApiService) {
        this.benchmarkStateService = benchmarkStateService;
        benchmarkForm = new BenchmarkForm();
        setHorizontalComponentAlignment(Alignment.CENTER, benchmarkForm);
        add(benchmarkForm);
        BenchmarkFormBinder benchmarkFormBinder = new BenchmarkFormBinder(controller, benchmarkForm,
                authenticatedUser, covidApiController, translateApiGoogleController, benchmarkStateService, benchmarkApiService);
        benchmarkFormBinder.addBindingAndValidation();
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        // Verifica se há um benchmarkBean armazenado no serviço de estado
        Benchmark currentBenchmark = benchmarkStateService.getCurrentBenchmark();
        if (currentBenchmark != null) {
            // Preenche os campos do formulário com os dados do benchmarkBean
            benchmarkForm.getPais1().setValue(currentBenchmark.getPais1());
            benchmarkForm.getPais2().setValue(currentBenchmark.getPais2());
            benchmarkForm.getDataInicial().setValue(currentBenchmark.getDataInicial());
            benchmarkForm.getDataFinal().setValue(currentBenchmark.getDataFinal());
            benchmarkForm.getComentario().setValue(currentBenchmark.getComentario());

            // Recupera as listas de DadosCovidPais do serviço de estado
            List<DadosCovidPais> dadosCovidPais1List = benchmarkStateService.getDadosCovidPais1List();
            List<DadosCovidPais> dadosCovidPais2List = benchmarkStateService.getDadosCovidPais2List();

            // Preenche os MasterDetailsDataCovidPais com os dados armazenados
            if (dadosCovidPais1List != null && dadosCovidPais2List != null) {
                benchmarkForm.getMasterDetailsDataCovidPais1().getGrid().setItems(dadosCovidPais1List);
                benchmarkForm.getMasterDetailsDataCovidPais1().setTitle(currentBenchmark.getPais1());
                benchmarkForm.getMasterDetailsDataCovidPais1().getGrid().getListDataView().refreshAll();

                benchmarkForm.getMasterDetailsDataCovidPais2().getGrid().setItems(dadosCovidPais2List);
                benchmarkForm.getMasterDetailsDataCovidPais2().setTitle(currentBenchmark.getPais2());
                benchmarkForm.getMasterDetailsDataCovidPais2().getGrid().getListDataView().refreshAll();
            }
        }

        // Exibe uma notificação de boas-vindas
        notification = new NotificationComponent("Seja Bem-Vindo",
                "success",
                "top_end");

        add(notification);
    }

}
