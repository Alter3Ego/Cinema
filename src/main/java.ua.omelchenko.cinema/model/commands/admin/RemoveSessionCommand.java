package model.commands.admin;

import Entity.Session;
import Entity.User;
import model.commands.Command;
import model.commands.MainPageCommand;
import model.dao.DaoFactory;
import model.manager.ConfigurationManager;
import model.service.SessionService;
import model.service.impl.SessionServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RemoveSessionCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(RemoveSessionCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Session session = null;
        User user = null;
        try {
            session = (Session) request.getSession().getAttribute("currentSession");
            user = (User) request.getSession().getAttribute("user");
        } catch (ClassCastException e){
            LOGGER.error(e);
        }
        if( session != null && user != null && user.getRole().equals("admin")){
            SessionService sessionService = new SessionServiceImpl(DaoFactory.getInstance());
            sessionService.deleteThisSession(session);
        } else {
            return ConfigurationManager.getInstance()
                    .getProperty(ConfigurationManager.ERROR_404_PAGE_PATH);
        }

        MainPageCommand mainPageCommand = new MainPageCommand();
        return mainPageCommand.execute(request, response);
    }
}
