package ua.omelchenko.cinema.service.impl;

import ua.omelchenko.cinema.entity.Session;
import ua.omelchenko.cinema.entity.Ticket;
import ua.omelchenko.cinema.entity.User;

import ua.omelchenko.cinema.dao.DaoFactory;
import ua.omelchenko.cinema.dao.TicketDao;
import ua.omelchenko.cinema.service.TicketService;

import java.util.List;

public class TicketServiceImpl implements TicketService {
    private final DaoFactory daoFactory;

    public TicketServiceImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    @Override
    public List<Ticket> getTicketsBySessionId(int sessionId) {
        try (TicketDao dao = daoFactory.createTicketDao()) {
            return dao.getBySessionId(sessionId);
        }
    }
    @Override
    public boolean addTickets(Session session, User user, List<Integer> places) {
        try (TicketDao dao = daoFactory.createTicketDao()) {
            return dao.updateTickets(session,user, places );
        }
    }

}
