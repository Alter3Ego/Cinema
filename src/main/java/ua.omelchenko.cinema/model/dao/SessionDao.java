package ua.omelchenko.cinema.model.dao;

import ua.omelchenko.cinema.entity.Session;

import java.util.Date;
import java.util.List;

public interface SessionDao extends EntityDao<Session> {
    List<Session> findAllPage(String orderBy, int start, int end, int ticketLimit);

    Session getById(int id);

    Session updateTickets(Session session, int number);

    boolean createSession(Integer filmId, Date datetime);

    boolean deleteSession(Session session);
}
