package model.commands.admin;

import controller.TemporaryAttributes;
import model.commands.Command;
import model.dao.DaoFactory;
import model.service.SessionService;
import model.service.impl.SessionServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Create a new film session
 */
public class AddSessionActionCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(AddSessionActionCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Date dateTime = null;
        Integer filmId = null;
        try {
            dateTime = new SimpleDateFormat("dd MMMM yyyy - hh:mm", Locale.ENGLISH).
                    parse(request.getParameter("dateTime"));
            filmId = Integer.valueOf(request.getParameter("filmId"));
        } catch (ParseException | NumberFormatException e) {
            LOGGER.error(e);
        }
        TemporaryAttributes tA = (TemporaryAttributes) request.getSession().getAttribute("temp");
        if (dateTime != null && filmId != null) {
            SessionService sessionService = new SessionServiceImpl(DaoFactory.getInstance());
            boolean e = sessionService.addSession(filmId, dateTime);
            tA.setAttributes("successfulAdd", e);
        } else {
            tA.setAttributes("errorDB", true);
        }
        request.getSession().setAttribute("temp", tA);

        SessionAddPageCommand sessionAddPageCommand = new SessionAddPageCommand();
        return sessionAddPageCommand.execute(request, response);
    }
}
