package model.commands;

import model.manager.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NoCommand implements Command {
    public String execute(HttpServletRequest request, HttpServletResponse response){
        //return to the registration form
        String page = ConfigurationManager.getInstance()
                .getProperty(ConfigurationManager.LOGIN_PAGE_PATH);
        return page;
    }
}