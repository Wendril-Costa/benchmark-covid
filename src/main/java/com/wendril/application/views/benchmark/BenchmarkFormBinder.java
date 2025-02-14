package com.wendril.application.views.benchmark;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.wendril.application.controller.BenchmarkController;
import com.wendril.application.controller.CovidApiController;
import com.wendril.application.controller.TranslateApiGoogleController;
import com.wendril.application.model.Benchmark;
import com.wendril.application.model.DadosCovidPais;
import com.wendril.application.model.User;
import com.wendril.application.security.AuthenticatedUser;
import com.wendril.application.services.BenchmarkApiService;
import com.wendril.application.services.BenchmarkStateService;
import com.wendril.application.utils.NotificationComponent;

import java.util.List;
import java.util.Optional;

public class BenchmarkFormBinder {
    private final BenchmarkController benchmarkController;
    private final BenchmarkForm benchmarkForm;
    private final AuthenticatedUser authenticatedUser;
    private final CovidApiController covidApiController;
    private final TranslateApiGoogleController translateApiGoogleController;
    private final BenchmarkStateService benchmarkStateService;
    private NotificationComponent notification;
    private Benchmark benchmarkBean;
    private List<DadosCovidPais> dadosCovidPais1List;
    private List<DadosCovidPais> dadosCovidPais2List;
    private final BenchmarkApiService benchmarkApiService;

    public BenchmarkFormBinder(BenchmarkController benchmarkController, BenchmarkForm benchmarkForm,
                               AuthenticatedUser authenticatedUser, CovidApiController covidApiController,
                               TranslateApiGoogleController translateApiGoogleController,
                               BenchmarkStateService benchmarkStateService,
                               BenchmarkApiService benchmarkApiService) {
        this.benchmarkController = benchmarkController;
        this.benchmarkForm = benchmarkForm;
        this.authenticatedUser = authenticatedUser;
        this.covidApiController = covidApiController;
        this.translateApiGoogleController = translateApiGoogleController;
        this.benchmarkStateService = benchmarkStateService;
        this.benchmarkApiService = benchmarkApiService;
    }

    public void addBindingAndValidation() {
        BeanValidationBinder<Benchmark> binder = new BeanValidationBinder<>(Benchmark.class);
        binder.bindInstanceFields(benchmarkForm);

        benchmarkForm.getPesquisar().addClickListener(event -> {
            try {
                benchmarkBean = new Benchmark();
                Optional<User> maybeUser = authenticatedUser.get();
                maybeUser.ifPresent(user -> benchmarkBean.setUser(user));
                binder.writeBean(benchmarkBean);
                setMasterDetails();
                benchmarkController.save(benchmarkBean);
                benchmarkStateService.setCurrentBenchmark(benchmarkBean);


            } catch (Exception e) {
                notification = new NotificationComponent("Erro ao Comparar: " + e.getMessage(), "error",
                        "top_end");
                benchmarkForm.add(notification);
                e.printStackTrace();
            }
        });

        benchmarkForm.getResultados().addClickListener(event -> {
            UI.getCurrent().navigate("benchmark-result/" + benchmarkBean.getId());
        });
    }

    private void setMasterDetails() {
        benchmarkBean.setResultados(benchmarkApiService
                .comparePaises(
                        benchmarkBean.getPais1(),
                        benchmarkBean.getPais2(),
                        benchmarkBean.getDataInicial(),
                        benchmarkBean.getDataFinal(),
                        true));
        benchmarkBean.getResultados().getFirst().setBenchmark(benchmarkBean);
        benchmarkBean.getResultados().getLast().setBenchmark(benchmarkBean);

        dadosCovidPais1List = benchmarkApiService.getDadosCovidPais1List();
        dadosCovidPais2List = benchmarkApiService.getDadosCovidPais2List();

        benchmarkStateService.setDadosCovidPais1List(dadosCovidPais1List);
        benchmarkStateService.setDadosCovidPais2List(dadosCovidPais2List);

        setItemsMasterDetails(dadosCovidPais1List, dadosCovidPais2List);
    }

    private void setItemsMasterDetails(List<DadosCovidPais> dadosCovidPais1List, List<DadosCovidPais> dadosCovidPais2List) {
        benchmarkForm.getMasterDetailsDataCovidPais1().getGrid().setItems(dadosCovidPais1List);
        benchmarkForm.getMasterDetailsDataCovidPais1().setTitle(benchmarkBean.getPais1());
        benchmarkForm.getMasterDetailsDataCovidPais1().getGrid().getListDataView().refreshAll();

        benchmarkForm.getMasterDetailsDataCovidPais2().getGrid().setItems(dadosCovidPais2List);
        benchmarkForm.getMasterDetailsDataCovidPais2().setTitle(benchmarkBean.getPais2());
        benchmarkForm.getMasterDetailsDataCovidPais2().getGrid().getListDataView().refreshAll();
    }
}
