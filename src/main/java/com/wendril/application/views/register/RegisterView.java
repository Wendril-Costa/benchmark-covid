package com.wendril.application.views.register;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.wendril.application.controller.UserController;
import com.wendril.application.security.AuthenticatedUser;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serial;

@PageTitle("Register")
@Route("register")
@AnonymousAllowed
public class RegisterView extends VerticalLayout {
    @Serial
    private static final long serialVersionUID = 1L;
    public RegisterView(@Autowired UserController controller, @Autowired AuthenticatedUser authenticatedUser) {
        RegistrationForm registrationForm = new RegistrationForm();
        setHorizontalComponentAlignment(Alignment.CENTER, registrationForm);

        add(registrationForm);

        RegistrationFormBinder registrationFormBinder = new RegistrationFormBinder(controller, registrationForm,authenticatedUser);
        registrationFormBinder.addBindingAndValidation();
    }
}
