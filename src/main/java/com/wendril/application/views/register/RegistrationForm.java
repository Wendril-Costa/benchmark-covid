package com.wendril.application.views.register;

import com.vaadin.flow.component.HasValueAndElement;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import com.wendril.application.utils.Titles;

import java.io.Serial;
import java.util.stream.Stream;

public class RegistrationForm extends FormLayout {
    @Serial
    private static final long serialVersionUID = 1L;
    private H3 title;
    private TextField username;
    private TextField name;
    private EmailField email;
    private PasswordField password;
    private PasswordField passwordConfirm;
    private MultiFileMemoryBuffer buffer;
    private Upload upload;
    private Span errorMessageField;
    private Button submitButton;

    public RegistrationForm() {

        title = new H3("Signup form");

        username = new TextField("Username");
        name = new TextField("name");
        email = new EmailField("Email");

        password = new PasswordField("Password");
        passwordConfirm = new PasswordField("Confirm password");

        buffer = new MultiFileMemoryBuffer();
        upload = new Upload(buffer);

        setRequiredIndicatorVisible(username, name, email, password,
                passwordConfirm);

        errorMessageField = new Span();

        submitButton = new Button(Titles.TITULO_REGISTER);
        submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        add(title, username, name, email, password,
                passwordConfirm, upload, errorMessageField, submitButton);
        setMaxWidth("500px");

        setResponsiveSteps(
                new ResponsiveStep("0", 1, ResponsiveStep.LabelsPosition.TOP),
                new ResponsiveStep("490px", 2, ResponsiveStep.LabelsPosition.TOP));


        setColspan(title, 2);
        setColspan(email, 2);
        setColspan(upload, 2);
        setColspan(errorMessageField, 2);
        setColspan(submitButton, 2);
    }

    public PasswordField getPasswordField() { return password; }

    public PasswordField getPasswordConfirmField() { return passwordConfirm; }

    public Span getErrorMessageField() { return errorMessageField; }

    public Button getSubmitButton() { return submitButton; }

    public MultiFileMemoryBuffer getBuffer() {
        return buffer;
    }

    public Upload getUpload() {
        return upload;
    }

    private void setRequiredIndicatorVisible(HasValueAndElement<?, ?>... components) {
        Stream.of(components).forEach(comp -> comp.setRequiredIndicatorVisible(true));
    }


}
