package ua.omelchenko.cinema.model.service.impl;

import ua.omelchenko.cinema.entity.Session;

import ua.omelchenko.cinema.model.dao.DaoFactory;
import ua.omelchenko.cinema.model.dao.SessionDao;
import ua.omelchenko.cinema.model.service.SessionService;
import java.util.Date;
import java.util.List;

public class SessionServiceImpl implements SessionService {
    private final DaoFactory daoFactory;

    public SessionServiceImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public List<Session> getServicesOrderByLimits(String orderBy, int start, int end, int ticketLimit) {
        try (SessionDao dao = daoFactory.createSessionDao()) {
            return dao.findAllPage(orderBy, start, end, ticketLimit);
        }
    }

    @Override
    public Session getSessionById(int sessionId) {
        try (SessionDao dao = daoFactory.createSessionDao()) {
            return dao.getById(sessionId);
        }
    }

    @Override
    public Session updateNumberOfTickets(Session session, int number) {
        try (SessionDao dao = daoFactory.createSessionDao()) {
            return dao.updateTickets(session, number);
        }
    }

    @Override
    public boolean addSession(Integer filmId, Date datetime) {
        try (SessionDao dao = daoFactory.createSessionDao()) {
            return dao.createSession(filmId, datetime);
        }
    }

    @Override
    public boolean deleteThisSession(Session session) {
        try (SessionDao dao = daoFactory.createSessionDao()) {
            return dao.deleteSession(session);
        }
    }
}
