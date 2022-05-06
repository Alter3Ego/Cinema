package model.service;

import entity.Session;
import entity.Ticket;
import entity.User;

import java.util.List;

public interface TicketService {
    List<Ticket> getTicketsBySessionId(int sessionId);
    boolean addTickets(Session session, User user, List<Integer> places);
}
