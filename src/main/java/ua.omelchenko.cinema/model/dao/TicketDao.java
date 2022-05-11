package ua.omelchenko.cinema.model.dao;

import ua.omelchenko.cinema.entity.Session;
import ua.omelchenko.cinema.entity.Ticket;
import ua.omelchenko.cinema.entity.User;


import java.util.List;

public interface TicketDao extends EntityDao<Ticket>  {
    List<Ticket> getBySessionId(int sessionId);
    boolean updateTickets(Session session, User user, List<Integer> places);
}
