package model.commands;

import Entity.User;
import controller.TemporaryAttributes;
import model.dao.DaoFactory;
import model.logic.PasswordHash;
import model.manager.ConfigurationManager;
import model.service.UserService;
import model.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(LoginCommand.class);
    private static final String PARAM_NAME_EMAIL = "email";
    private static final String PARAM_NAME_PASSWORD = "password";

    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String page = null;

//get email and password from request
        String email = req.getParameter(PARAM_NAME_EMAIL);
        String pass = req.getParameter(PARAM_NAME_PASSWORD);

        String hashPass = PasswordHash.encryption(pass);
        UserService userService = new UserServiceImpl(DaoFactory.getInstance());
        User user = userService.getUserByEmailAndPass(email, hashPass);

        if (user != null) {
            req.getSession().setAttribute("user", user);
//defining the path to index.jsp
            page = ConfigurationManager.getInstance()
                    .getProperty(ConfigurationManager.MAIN_PAGE_PATH);
        } else {
            TemporaryAttributes tA = (TemporaryAttributes) req.getSession().getAttribute("temp");
            tA.setEmailError(true);
            req.getSession().setAttribute("temp", tA);
            LOGGER.debug("This is emailError " + req.getAttribute("temp.emailError"));
            page = ConfigurationManager.getInstance()
                    .getProperty(ConfigurationManager.LOGIN_PAGE_PATH);
        }
        return page;
    }
}