package com.wendril.application.views.login;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.internal.RouteUtil;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.lumo.LumoUtility;
import com.wendril.application.security.AuthenticatedUser;
import com.wendril.application.utils.Message;
import com.wendril.application.utils.Titles;

import java.io.Serial;

@AnonymousAllowed
@PageTitle("Login")
@Route(value = "login")
public class LoginView extends Div implements BeforeEnterObserver {
    @Serial
    private static final long serialVersionUID = 1L;
    private final AuthenticatedUser authenticatedUser;
    private final LoginOverlay loginOverlay;
    private Button registerButton;
    private Paragraph text;

    public LoginView(AuthenticatedUser authenticatedUser) {
        loginOverlay = new LoginOverlay();
        this.authenticatedUser = authenticatedUser;
        loginOverlay.setAction(RouteUtil.getRoutePath(VaadinService.getCurrent().getContext(), getClass()));

        LoginI18n login = LoginI18n.createDefault();
        login.setHeader(new LoginI18n.Header());
        login.getHeader().setTitle(Titles.TITULO_APLICATION);
        login.setAdditionalInformation(null);

        setLoginForm(login);
        loginOverlay.setI18n(login);
        loginOverlay.setForgotPasswordButtonVisible(false);

        registerButton();
        registerParagraph();

        add(loginOverlay);
        loginOverlay.setOpened(true);
        registerButton.addClickListener(e -> UI.getCurrent().navigate("register"));
    }

    private void setLoginForm(LoginI18n login) {
        login.getForm().setTitle(Titles.TITULO_LOGIN);
        login.getForm().setSubmit(Titles.TITULO_LOGIN);
        login.getErrorMessage().setTitle(Message.ERRO_TITLE_LOGIN);
        login.getErrorMessage().setMessage(Message.ERRO_LOGIN_USERNAME_PASSWORD);
    }

    private void registerParagraph() {
        text = new Paragraph("Nunca conte sua senha a ninguém");
        text.addClassName(LumoUtility.TextAlignment.CENTER);
        loginOverlay.getFooter().add(text);
    }

    private void registerButton() {
        registerButton = new Button(Titles.TITULO_REGISTER);
        registerButton.setWidthFull();
        loginOverlay.getFooter().add(registerButton);

    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        if (authenticatedUser.get().isPresent()) {
            loginOverlay.setOpened(false);

            event.forwardTo("");
        }

        loginOverlay.setError(event.getLocation().getQueryParameters().getParameters().containsKey("error"));
    }

}
