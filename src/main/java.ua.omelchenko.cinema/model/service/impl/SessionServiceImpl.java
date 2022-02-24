package model.service.impl;

import Entity.Session;
import model.dao.DaoFactory;
import model.dao.SessionDao;
import model.service.SessionService;

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
}
