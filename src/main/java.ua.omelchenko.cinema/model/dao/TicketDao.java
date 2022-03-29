package model.dao;

import Entity.Session;
import Entity.Ticket;
import Entity.User;


import java.util.List;

public interface TicketDao extends EntityDao<Ticket>  {
    List<Ticket> getBySessionId(int sessionId);
    boolean updateTickets(Session session, User user, List<Integer> places);
}
