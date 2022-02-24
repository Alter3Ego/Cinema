package model.service;

import Entity.Session;

import java.util.List;

public interface SessionService {
    List <Session> getServicesOrderByLimits(String orderBy, int start, int end, int ticketLimit);
}
