package model.dao;

import Entity.Session;
import Entity.User;

import java.util.List;

public interface SessionDao extends EntityDao<Session> {
    List<Session> findAllPage(String orderBy, int start, int end, int ticketLimit);
    boolean updateTickets();
}
