package ua.omelchenko.cinema.model.commands;

import ua.omelchenko.cinema.entity.Session;
import ua.omelchenko.cinema.entity.Ticket;
import ua.omelchenko.cinema.entity.User;
import ua.omelchenko.cinema.controller.TemporaryAttributes;
import ua.omelchenko.cinema.model.dao.DaoFactory;
import ua.omelchenko.cinema.model.dao.impl.exception.DBException;

import org.apache.log4j.Logger;
import ua.omelchenko.cinema.model.manager.ConfigurationManager;
import ua.omelchenko.cinema.model.service.SessionService;
import ua.omelchenko.cinema.model.service.TicketService;
import ua.omelchenko.cinema.model.service.UserService;
import ua.omelchenko.cinema.model.service.impl.SessionServiceImpl;
import ua.omelchenko.cinema.model.service.impl.TicketServiceImpl;
import ua.omelchenko.cinema.model.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Load sessionPage.jsp page
 */
public class SessionPageCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(SessionPageCommand.class);
    private static final String SESSION_ID = "sessionId";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("typeRequest", "POST");
        int sessionId = Integer.parseInt(request.getParameter(SESSION_ID));

        SessionService sessionService = new SessionServiceImpl(DaoFactory.getInstance());
        TicketService ticketService = new TicketServiceImpl(DaoFactory.getInstance());
        Session session = sessionService.getSessionById(sessionId);
        TemporaryAttributes tA = (TemporaryAttributes) request.getSession().getAttribute("temp");
        User user = ((User) request.getSession().getAttribute("user"));
        String ticketsRequest = request.getParameter("tickets");
        try {
            ticketsRequest = request.getReader().lines().collect(Collectors.joining());
        } catch (IOException e) {
            LOGGER.error(e);
        }

        if (ticketsRequest != null && !ticketsRequest.equals("")) {
            //Check login user
            if (user != null) {

                ticketsRequest = ticketsRequest.replaceAll("[\\[\\]]", "");
                String[] items = ticketsRequest.split("\\s*,\\s*");

                List<Integer> ticketInt = new ArrayList<>();
                for (String s : items) {
                    ticketInt.add(Integer.valueOf(s));
                }
                UserService userService = new UserServiceImpl(DaoFactory.getInstance());
                user = userService.getUserById(user.getUserId());
                BigDecimal price = session.getFilm().getPrice().multiply(BigDecimal.valueOf(ticketInt.size()));

                //Transaction
                try {

                    user = userService.updateBalance(user, price.multiply(BigDecimal.valueOf(-1)));
                    if (ticketService.addTickets(session, user, ticketInt)) {
                        session = sessionService.updateNumberOfTickets(session, ticketInt.size());
                    } else {
                        user = userService.updateBalance(user, price);
                        tA.setAttributes("operationError", true);
                    }
                    request.getSession().setAttribute("user", user);

                } catch (DBException e) {
                    LOGGER.error("DBException" + e);
                    tA.setAttributes("errorBalance", true);
                }
            } else {
                tA.setAttributes("errorLogInSession", true);
            }
        }
        request.getSession().setAttribute("currentSession", session);

        //Get tickets
        List<Ticket> tickets = ticketService.getTicketsBySessionId(sessionId);
        HashMap<Integer, Integer> places = new HashMap<>();

        for (Ticket ticket : tickets) {
            places.put(ticket.getPlace(), ticket.getUser());
        }
        tA.setAttributes("places", places);
        return ConfigurationManager.getInstance()
                .getProperty(ConfigurationManager.SESSION_PAGE_PATH);
    }
}
