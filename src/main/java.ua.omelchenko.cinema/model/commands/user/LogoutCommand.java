package model.commands.user;

import model.commands.Command;
import model.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Invalidate session
 */
public class LogoutCommand implements Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().invalidate();
        return ConfigurationManager.getInstance()
                .getProperty(ConfigurationManager.LOGIN_PAGE_PATH);
    }
}