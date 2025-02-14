package com.wendril.application.views.benchmark;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.wendril.application.model.DadosCovidPais;

public class MasterDetailsDataCovidPais extends Div {
    private Grid<DadosCovidPais> grid;
    private final Span title;
    private final Div divMenu;

    public MasterDetailsDataCovidPais() {
        title = new Span("");
        divMenu = new Div();
        setStyleDiv();
        grid = new Grid<>();
        grid.addColumn(DadosCovidPais::getData).setHeader("Data").setSortable(true);
        grid.addColumn(DadosCovidPais::getTotalCasos).setHeader("Total Casos").setSortable(true);
        grid.addColumn(DadosCovidPais::getNewCasos).setHeader("Novos Casos").setSortable(true);
        grid.addColumn(DadosCovidPais::getTotalMortes).setHeader("Total Mortes").setSortable(true);
        grid.addColumn(DadosCovidPais::getNewMortes).setHeader("Novas Mortes").setSortable(true);
        grid.setMultiSort(true, Grid.MultiSortPriority.APPEND);
        grid.setHeight("300px");
        grid.setWidthFull();

        divMenu.add(title);
        add(divMenu, grid);
    }

    private void setStyleDiv() {
        title.getStyle()
                .set("font-size", "var(--lumo-font-size-s)")
                .set("font-weight", "600")
                .set("color", "var(--lumo-secondary-text-color)");
        divMenu.getStyle()
                .set("align-items", "center")
                .set("padding-top", ".2")
                .set("grid-template-columns", "1fr auto")
                .set("display", "grid");

    }

    public void setTitle(String pais) {
        title.setText(pais);
        getUI().ifPresent(ui -> ui.access(() -> {
            title.getElement().executeJs("this.textContent = $0;", pais);
        }));
    }

    public Grid<DadosCovidPais> getGrid() {
        return grid;
    }

    public void setGrid(Grid<DadosCovidPais> grid) {
        this.grid = grid;
    }

}
