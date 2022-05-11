package ua.omelchenko.cinema.command;

import ua.omelchenko.cinema.entity.User;
import ua.omelchenko.cinema.servlet.TemporaryAttributes;
import ua.omelchenko.cinema.dao.DaoFactory;
import ua.omelchenko.cinema.util.PasswordHash;
import ua.omelchenko.cinema.util.ConfigurationManager;
import ua.omelchenko.cinema.service.UserService;
import ua.omelchenko.cinema.service.impl.UserServiceImpl;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Add user to session scope
 */
public class LoginCommand implements Command {
    private static final String PARAM_NAME_EMAIL = "email";
    private static final String PARAM_NAME_PASSWORD = "password";

    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String page;

        String email = req.getParameter(PARAM_NAME_EMAIL);
        String pass = req.getParameter(PARAM_NAME_PASSWORD);

        String hashPass = PasswordHash.encryption(pass);
        UserService userService = new UserServiceImpl(DaoFactory.getInstance());
        User user = userService.getUserByEmailAndPass(email, hashPass);

        if (user != null) {
            req.getSession().setAttribute("user", user);
            page = ConfigurationManager.getInstance()
                    .getProperty(ConfigurationManager.MAIN_PAGE_PATH);
        } else {
            TemporaryAttributes tA = (TemporaryAttributes) req.getSession().getAttribute("temp");
            tA.setAttributes("emailError",true);
            req.getSession().setAttribute("temp", tA);
            page = ConfigurationManager.getInstance()
                    .getProperty(ConfigurationManager.LOGIN_PAGE_PATH);
        }
        return page;
    }
}