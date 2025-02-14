package com.wendril.application.views.register;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.binder.ValidationResult;
import com.vaadin.flow.data.binder.ValueContext;
import com.wendril.application.controller.UserController;
import com.wendril.application.model.User;
import com.wendril.application.security.AuthenticatedUser;
import com.wendril.application.utils.NotificationComponent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.IOException;
import java.io.InputStream;

public class RegistrationFormBinder {
    private BCryptPasswordEncoder passwordEncoder;
    private final RegistrationForm registrationForm;
    private final UserController userController;
    private byte[] uploadedFileData;
    private NotificationComponent notification;
    private final AuthenticatedUser authenticatedUser;
    private boolean enablePasswordValidation;

    public RegistrationFormBinder(UserController controller, RegistrationForm registrationForm, AuthenticatedUser authenticatedUser) {
        this.userController = controller;
        this.registrationForm = registrationForm;
        this.authenticatedUser = authenticatedUser;
    }

    public void addBindingAndValidation() {
        BeanValidationBinder<User> binder = new BeanValidationBinder<>(User.class);
        binder.bindInstanceFields(registrationForm);

        binder.forField(registrationForm.getPasswordField())
                .withValidator(this::passwordValidator).bind("password");

        registrationForm.getPasswordConfirmField().addValueChangeListener(e -> {
            enablePasswordValidation = true;
            binder.validate();
        });

        binder.setStatusLabel(registrationForm.getErrorMessageField());
        registrationForm.getUpload().addSucceededListener(event -> {
            String fileName = event.getFileName();
            try (InputStream inputStream = registrationForm.getBuffer().getInputStream(fileName)) {
                uploadedFileData = inputStream.readAllBytes();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        registrationForm.getSubmitButton().addClickListener(event -> {
            try {
                User userBean = new User();
                userBean.setProfilePicture(uploadedFileData);

                binder.writeBean(userBean);

                passwordEncoder = new BCryptPasswordEncoder();
                userBean.setPassword(passwordEncoder.encode(userBean.getPassword()));
                userController.save(userBean);

                authenticatedUser.login(userBean.getUsername(), userBean.getPassword());
                if (authenticatedUser.get().isPresent()) {
                    UI.getCurrent().navigate("");
                }
            } catch (ValidationException exception) {
                notification = new NotificationComponent("Erro ao validar o formulário", "error",
                        "top_end");
                registrationForm.add(notification);
            } catch (Exception e) {
                notification = new NotificationComponent("Erro ao salvar usuário: " + e.getMessage(), "error",
                        "top_end");
                registrationForm.add(notification);
                e.printStackTrace();
            }
        });
    }

    private ValidationResult passwordValidator(String pass1, ValueContext ctx) {
        if (!enablePasswordValidation) {
            enablePasswordValidation = true;
            return ValidationResult.ok();
        }

        String pass2 = registrationForm.getPasswordConfirmField().getValue();

        if (pass1 != null && pass1.equals(pass2)) {
            return ValidationResult.ok();
        }

        return ValidationResult.error("Passwords do not match");
    }
}
