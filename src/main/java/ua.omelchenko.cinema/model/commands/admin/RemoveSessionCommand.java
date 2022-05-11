package ua.omelchenko.cinema.model.commands.admin;

import ua.omelchenko.cinema.entity.Session;
import ua.omelchenko.cinema.entity.User;
import ua.omelchenko.cinema.model.commands.Command;
import ua.omelchenko.cinema.model.commands.MainPageCommand;

import org.apache.log4j.Logger;
import ua.omelchenko.cinema.model.dao.DaoFactory;
import ua.omelchenko.cinema.model.manager.ConfigurationManager;
import ua.omelchenko.cinema.model.service.SessionService;
import ua.omelchenko.cinema.model.service.impl.SessionServiceImpl;

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
