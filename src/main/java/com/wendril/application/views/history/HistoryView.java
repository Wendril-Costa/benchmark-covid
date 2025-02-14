package com.wendril.application.views.history;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.wendril.application.controller.BenchmarkController;
import com.wendril.application.security.AuthenticatedUser;
import com.wendril.application.views.MainLayout;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

import java.io.Serial;

@PageTitle("History")
@Route(value = "/history", layout = MainLayout.class)
@Menu(order = 2, icon = LineAwesomeIconUrl.LIST_OL_SOLID)
@PermitAll
public class HistoryView extends VerticalLayout {
    @Serial
    private static final long serialVersionUID = 1L;

    public HistoryView(@Autowired AuthenticatedUser authenticatedUser, BenchmarkController benchmarkService) {
        HistoryMaster historyMaster = new HistoryMaster(authenticatedUser, benchmarkService);
        setHorizontalComponentAlignment(Alignment.CENTER, historyMaster);
        setSizeFull();
        setAlignItems(Alignment.STRETCH);
        setJustifyContentMode(JustifyContentMode.CENTER);
        add(historyMaster);
    }

}
