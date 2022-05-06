package model.dao;

import entity.Session;
import entity.Ticket;
import entity.User;


import java.util.List;

public interface TicketDao extends EntityDao<Ticket>  {
    List<Ticket> getBySessionId(int sessionId);
    boolean updateTickets(Session session, User user, List<Integer> places);
}
