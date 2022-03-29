package model.service.impl;

import Entity.Session;
import Entity.Ticket;
import Entity.User;
import model.dao.DaoFactory;
import model.dao.TicketDao;
import model.service.TicketService;

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
