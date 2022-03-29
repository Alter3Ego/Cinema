package model.service;

import Entity.Session;
import Entity.Ticket;
import Entity.User;

import java.util.List;

public interface TicketService {
    List<Ticket> getTicketsBySessionId(int sessionId);
    boolean addTickets(Session session, User user, List<Integer> places);
}
