package model.service;

import Entity.Session;

import java.util.List;

public interface SessionService {
    List <Session> getServicesOrderByLimits(String orderBy, int start, int end, int ticketLimit);
    Session getSessionById(int sessionId);
    Session updateNumberOfTickets(Session session, int number);
    boolean addSession(Integer filmId, java.util.Date datetime);
    boolean deleteThisSession(Session session);
}
