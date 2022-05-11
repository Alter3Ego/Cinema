package ua.omelchenko.cinema.command.admin;

import ua.omelchenko.cinema.servlet.TemporaryAttributes;
import ua.omelchenko.cinema.command.Command;
import ua.omelchenko.cinema.dao.DaoFactory;
import ua.omelchenko.cinema.util.ConfigurationManager;
import ua.omelchenko.cinema.service.FilmService;
import ua.omelchenko.cinema.service.impl.FilmServiceImpl;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Load the sessionAdd page
 */
public class SessionAddPageCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("typeRequest", "POST");
        FilmService filmService = new FilmServiceImpl(DaoFactory.getInstance());

        TemporaryAttributes tA = (TemporaryAttributes) request.getSession().getAttribute("temp");
        tA.setAttributes("sessionAddPage", filmService.getAllFilms());
        request.getSession().setAttribute("temp", tA);
        return ConfigurationManager.getInstance()
                .getProperty(ConfigurationManager.ADD_SESSION_PATH);
    }
}
