package model.commands.admin;

import controller.TemporaryAttributes;
import model.commands.Command;
import model.dao.DaoFactory;
import model.manager.ConfigurationManager;
import model.service.FilmService;
import model.service.impl.FilmServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Load the sessionAdd page
 */
public class SessionAddPageCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        FilmService filmService = new FilmServiceImpl(DaoFactory.getInstance());

        TemporaryAttributes tA = (TemporaryAttributes) request.getSession().getAttribute("temp");
        tA.setSessionAddPage(filmService.getAllFilms());
        request.getSession().setAttribute("temp", tA);
        return ConfigurationManager.getInstance()
                .getProperty(ConfigurationManager.ADD_SESSION_PATH);
    }
}
