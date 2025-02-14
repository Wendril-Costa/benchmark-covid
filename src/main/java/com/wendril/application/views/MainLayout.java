package com.wendril.application.views;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.SvgIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.server.auth.AccessAnnotationChecker;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.server.menu.MenuConfiguration;
import com.vaadin.flow.server.menu.MenuEntry;
import com.vaadin.flow.theme.lumo.LumoUtility;
import com.wendril.application.controller.UserController;
import com.wendril.application.model.User;
import com.wendril.application.security.AuthenticatedUser;
import com.wendril.application.utils.Titles;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.ByteArrayInputStream;
import java.io.Serial;
import java.util.List;
import java.util.Optional;

@AnonymousAllowed
public class MainLayout extends AppLayout {
    @Serial
    private static final long serialVersionUID = 1L;
    private final AuthenticatedUser authenticatedUser;
    private final AccessAnnotationChecker accessChecker;
    private final UserController userController;
    private H1 viewTitle;

    public MainLayout(AuthenticatedUser authenticatedUser, AccessAnnotationChecker accessChecker, UserController userController) {
        this.authenticatedUser = authenticatedUser;
        this.accessChecker = accessChecker;
        this.userController = userController;
        setPrimarySection(Section.DRAWER);
        addDrawerContent();
        addHeaderContent();
    }

    private void addHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.setAriaLabel("Menu toggle");

        viewTitle = new H1();
        viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);

        addToNavbar(true, toggle, viewTitle);
    }

    private void addDrawerContent() {
        Span appName = new Span(Titles.TITULO_APLICATION);
        appName.addClassNames(LumoUtility.FontWeight.SEMIBOLD, LumoUtility.FontSize.LARGE);
        Header header = new Header(appName);

        Scroller scroller = new Scroller(createNavigation());

        addToDrawer(header, scroller, createFooter());
    }

    private SideNav createNavigation() {
        SideNav nav = new SideNav();

        List<MenuEntry> menuEntries = MenuConfiguration.getMenuEntries();
        menuEntries.forEach(entry -> {
            if (entry.icon() != null) {
                nav.addItem(new SideNavItem(entry.title(), entry.path(), new SvgIcon(entry.icon())));
            } else {
                nav.addItem(new SideNavItem(entry.title(), entry.path()));
            }
        });

        return nav;
    }

    private Footer createFooter() {
        Footer layout = new Footer();

        Optional<User> maybeUser = authenticatedUser.get();
        if (maybeUser.isPresent()) {
            User user = maybeUser.get();

            Avatar avatar = new Avatar(user.getName());
            StreamResource resource = new StreamResource("profile-pic",
                    () -> new ByteArrayInputStream(user.getProfilePicture()));
            avatar.setImageResource(resource);
            avatar.setThemeName("xsmall");
            avatar.getElement().setAttribute("tabindex", "-1");

            MenuBar userMenu = new MenuBar();
            userMenu.setThemeName("tertiary-inline contrast");

            MenuItem userName = userMenu.addItem("");
            Div div = new Div();
            div.add(avatar);
            div.add(user.getName());
            div.add(new Icon("lumo", "dropdown"));
            div.addClassNames(LumoUtility.Display.FLEX, LumoUtility.AlignItems.CENTER, LumoUtility.Gap.SMALL);
            userName.add(div);
            userName.getSubMenu().addItem("Sign out", e -> authenticatedUser.logout());
            userName.getSubMenu().addItem("Edit", e -> editarUsuario());

            layout.add(userMenu);
        } else {
            Anchor loginLink = new Anchor("login", "Sign in");
            layout.add(loginLink);
        }

        return layout;
    }

    public void editarUsuario() {
        Optional<User> maybeUser = authenticatedUser.get();
        if (maybeUser.isEmpty()) {
            Notification.show("Usuário não encontrado!", 3000, Notification.Position.MIDDLE);
            return;
        }

        User usuario = maybeUser.get();

        Dialog dialog = new Dialog();
        dialog.setWidth("400px");
        TextField txtUsername = new TextField("Username");
        txtUsername.setValue(usuario.getUsername());
        TextField txtNome = new TextField("Name");
        txtNome.setValue(usuario.getName());

        EmailField txtEmail = new EmailField("Email");
        txtEmail.setValue(usuario.getEmail());

        PasswordField txtSenha = new PasswordField("Nova Senha");

        Button btnSalvar = new Button("Salvar", e -> {
            usuario.setName(txtNome.getValue());
            usuario.setEmail(txtEmail.getValue());
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if (!txtSenha.getValue().isEmpty()) {
                usuario.setPassword(passwordEncoder.encode(txtSenha.getValue()));
            }

            try {
                userController.updateUser(usuario);
                Notification.show("Usuário atualizado com sucesso!", 3000, Notification.Position.TOP_CENTER);
                dialog.close();
            } catch (Exception ex) {
                Notification.show("Erro ao atualizar usuário!" + ex, 3000, Notification.Position.MIDDLE);
            }
        });

        Button btnCancelar = new Button("Cancelar", e -> dialog.close());

        HorizontalLayout buttons = new HorizontalLayout(btnSalvar, btnCancelar);
        FormLayout form = new FormLayout(txtNome, txtEmail, txtSenha);
        VerticalLayout layout = new VerticalLayout(form, buttons);

        dialog.add(layout);
        dialog.open();
    }


    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        viewTitle.setText(getCurrentPageTitle());
    }

    private String getCurrentPageTitle() {
        return MenuConfiguration.getPageHeader(getContent()).orElse("");
    }
}
