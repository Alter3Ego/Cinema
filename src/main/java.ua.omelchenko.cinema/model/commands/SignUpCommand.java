package model.commands;

import entity.User;
import controller.TemporaryAttributes;
import model.dao.DaoFactory;
import model.logic.PasswordHash;
import model.manager.ConfigurationManager;
import model.service.UserService;
import model.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Register user
 */ 
public class SignUpCommand implements Command {
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String PARAM_NAME_EMAIL = "email";
    private static final String PARAM_NAME_PASSWORD = "password";

    private static final String REGEX_NAME_EN = "[A-Z]([a-z]+){1,50}";
    private static final String REGEX_NAME_UA = "[А-ЩЬЮЯҐІЇЄ]([а-щьюяґіїє']){1,50}";
    private static final String REGEX_EMAIL = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    private static final String REGEX_PASSWORD = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        String page = ConfigurationManager.getInstance()
                .getProperty(ConfigurationManager.SIGN_UP_PAGE_PATH);

        String firstName = req.getParameter(FIRST_NAME);
        String lastName = req.getParameter(LAST_NAME);
        String email = req.getParameter(PARAM_NAME_EMAIL);
        String password = req.getParameter(PARAM_NAME_PASSWORD);

        if (wrongInputCheck(firstName, lastName, email, password)){
            TemporaryAttributes tA = (TemporaryAttributes) req.getSession().getAttribute("temp");
            tA.setSignUpDataError(true);
            req.getSession().setAttribute("temp", tA);
            return page;
        }
        UserService userService = new UserServiceImpl(DaoFactory.getInstance());

        if (userService.checkEmail(email)) {
            TemporaryAttributes tA = (TemporaryAttributes) req.getSession().getAttribute("temp");
            tA.setSignUpEmailError(true);
            req.getSession().setAttribute("temp", tA);
        } else {
            String hashPass = PasswordHash.encryption(password);
            User user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setPassword(hashPass);

            if (userService.addUser(user)) {
                page = ConfigurationManager.getInstance()
                        .getProperty(ConfigurationManager.LOGIN_PAGE_PATH);
            }
        }
        return page;
    }

    protected boolean wrongInputCheck(String firstName, String lastName, String email, String password) {
        if (firstName == null || lastName == null ||
                email == null || password == null) {

            return true;
        }
        return !(firstName.matches(REGEX_NAME_UA) || firstName.matches(REGEX_NAME_EN)) ||
                !(lastName.matches(REGEX_NAME_UA) || lastName.matches(REGEX_NAME_EN)) ||
                !(email.matches(REGEX_EMAIL) && email.length() < 255) ||
                !(password.matches(REGEX_PASSWORD) && password.length() < 50);
    }
}
