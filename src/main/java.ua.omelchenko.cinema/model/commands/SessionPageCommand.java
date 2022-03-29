package model.commands;

import Entity.Session;
import Entity.Ticket;
import Entity.User;
import controller.TemporaryAttributes;
import exception.DBException;
import model.dao.DaoFactory;
import model.manager.ConfigurationManager;
import model.service.SessionService;
import model.service.TicketService;
import model.service.UserService;
import model.service.impl.SessionServiceImpl;
import model.service.impl.TicketServiceImpl;
import model.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.*;

/**
 * Load sessionPage.jsp page
 */
public class SessionPageCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(SessionPageCommand.class);
    private static final String SESSION_ID = "sessionId";
    public static final int NUMBER_OF_PLACES = ConfigurationManager.getInstance()
            .getNumberProperty(ConfigurationManager.HALL_CAPACITY);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter(SESSION_ID);
        request.setAttribute("typeRequest", "POST");
        Integer sessionId = null;

        if (id != null) {
            try {
                sessionId = Integer.valueOf(id);
            } catch (NumberFormatException ex) {
                LOGGER.error("NumberFormatException");
            }
        }

        if (sessionId != null) {

            SessionService sessionService = new SessionServiceImpl(DaoFactory.getInstance());
            TicketService ticketService = new TicketServiceImpl(DaoFactory.getInstance());
            Session session = sessionService.getSessionById(sessionId);
            TemporaryAttributes tA = (TemporaryAttributes) request.getSession().getAttribute("temp");
            User user = ((User) request.getSession().getAttribute("user"));
            String ticketsRequest = request.getParameter("tickets");

            if (ticketsRequest != null && !ticketsRequest.equals("")) {
                //Check login user
                if (user != null) {

                    ticketsRequest = ticketsRequest.replaceAll("place", "");
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
                            tA.setOperationError(true);
                        }
                        if (user != null) {
                            request.getSession().setAttribute("user", user);
                        }
                    } catch (DBException e) {
                        LOGGER.error("DBException" + e);
                        tA.setErrorBalance(true);
                    }
                } else {
                    tA.setErrorLogInSession(true);
                }
            }
            request.getSession().setAttribute("currentSession", session);

            //Get tickets
            List<Ticket> tickets = ticketService.getTicketsBySessionId(sessionId);
            HashMap<Integer, Integer> places = new HashMap<>();
            for (int i = 1; i <= NUMBER_OF_PLACES; i++) {
                places.put(i, null);
            }
            for (Ticket ticket : tickets) {
                places.put(ticket.getPlace(), ticket.getUser());
            }
            tA.setPlaces(places);

            return ConfigurationManager.getInstance()
                    .getProperty(ConfigurationManager.SESSION_PAGE_PATH);

        }
        return ConfigurationManager.getInstance()
                .getProperty(ConfigurationManager.ERROR_404_PAGE_PATH);
    }
}
