package model.commands;

import model.logic.LoginLogic;
import model.manager.ConfigurationManager;
import model.manager.MessageManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginCommand implements Command {

    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String page = null;
//get login and password from request
        String login = req.getParameter(PARAM_NAME_LOGIN);
        System.out.println(login);
        String pass = req.getParameter(PARAM_NAME_PASSWORD);
        req.setAttribute("type","Com");
        System.out.println(pass);
//check login and password
        if (LoginLogic.checkLogin(login, pass)) {
            req.getSession().setAttribute("user", login);
//defining the path to main.jsp
            page = ConfigurationManager.getInstance()
                    .getProperty(ConfigurationManager.MAIN_PAGE_PATH);
        } else {
            req.setAttribute("errorMessage",
                    MessageManager.getInstance()
                            .getProperty(MessageManager.LOGIN_ERROR_MESSAGE));
            page = ConfigurationManager.getInstance()
                    .getProperty(ConfigurationManager.ERROR_PAGE_PATH);
        }
        return page;
    }
}