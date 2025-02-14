package com.wendril.application.views.benchmark;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.wendril.application.utils.DatePickerPT;

import java.io.Serial;

public class BenchmarkForm extends VerticalLayout {
    @Serial
    private static final long serialVersionUID = 1L;
    private final TextField pais1;
    private final TextField pais2;
    private final DatePickerPT dataInicial;
    private final DatePickerPT dataFinal;
    private final TextField comentario;
    private final Button pesquisar;
    private final Button resultados;
    private MasterDetailsDataCovidPais masterDetailsDataCovidPais1;
    private MasterDetailsDataCovidPais masterDetailsDataCovidPais2;

    public BenchmarkForm() {
        pais1 = new TextField("País 1");
        pais2 = new TextField("País 2");
        dataInicial = new DatePickerPT("Data Inicial");
        dataFinal = new DatePickerPT("Data Final");
        comentario = new TextField("Comentario");
        pesquisar = new Button("Pesquisar");
        pesquisar.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        resultados = new Button("Resultados");
        resultados.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        pesquisar.addClickShortcut(Key.ENTER);
        setWidthFull();

        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        masterDetailsDataCovidPais1 = new MasterDetailsDataCovidPais();
        masterDetailsDataCovidPais2 = new MasterDetailsDataCovidPais();
        masterDetailsDataCovidPais1.setWidthFull();
        masterDetailsDataCovidPais2.setWidthFull();
        FormLayout formLayout = new FormLayout();
        formLayout.add(pais1, pais2, dataInicial, dataFinal, comentario, pesquisar, resultados);
        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 2, FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("890px", 7, FormLayout.ResponsiveStep.LabelsPosition.TOP));
        formLayout.setColspan(pais1, 1);
        formLayout.setColspan(pais2, 1);
        formLayout.setColspan(dataInicial, 1);
        formLayout.setColspan(dataFinal, 1);
        formLayout.setColspan(comentario, 1);
        formLayout.setColspan(pesquisar, 1);
        formLayout.setColspan(resultados, 1);

        FormLayout formLayoutMasterPais1 = new FormLayout();
        formLayoutMasterPais1.add(masterDetailsDataCovidPais1);
        formLayoutMasterPais1.setColspan(masterDetailsDataCovidPais1, 6);
        formLayoutMasterPais1.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1, FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("890px", 6, FormLayout.ResponsiveStep.LabelsPosition.TOP));

        FormLayout formLayoutMasterPais2 = new FormLayout();
        formLayoutMasterPais2.add(masterDetailsDataCovidPais2);
        formLayoutMasterPais2.setColspan(masterDetailsDataCovidPais2, 6);
        formLayoutMasterPais2.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1, FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("890px", 6, FormLayout.ResponsiveStep.LabelsPosition.TOP));

        add(formLayout, formLayoutMasterPais1, formLayoutMasterPais2);
    }

    public MasterDetailsDataCovidPais getMasterDetailsDataCovidPais1() {
        return masterDetailsDataCovidPais1;
    }

    public void setMasterDetailsDataCovidPais1(MasterDetailsDataCovidPais masterDetailsDataCovidPais1) {
        this.masterDetailsDataCovidPais1 = masterDetailsDataCovidPais1;
    }

    public MasterDetailsDataCovidPais getMasterDetailsDataCovidPais2() {
        return masterDetailsDataCovidPais2;
    }

    public void setMasterDetailsDataCovidPais2(MasterDetailsDataCovidPais masterDetailsDataCovidPais2) {
        this.masterDetailsDataCovidPais2 = masterDetailsDataCovidPais2;
    }

    public TextField getPais1() {
        return pais1;
    }

    public TextField getPais2() {
        return pais2;
    }

    public DatePicker getDataInicial() {
        return dataInicial;
    }

    public DatePicker getDataFinal() {
        return dataFinal;
    }

    public TextField getComentario() {
        return comentario;
    }

    public Button getPesquisar() {
        return pesquisar;
    }

    public Button getResultados() {
        return resultados;
    }
}
