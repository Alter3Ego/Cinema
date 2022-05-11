package ua.omelchenko.cinema.model.dao.impl;

import ua.omelchenko.cinema.entity.Session;
import ua.omelchenko.cinema.entity.Ticket;
import ua.omelchenko.cinema.entity.User;
import ua.omelchenko.cinema.model.dao.TicketDao;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketDaoImpl implements TicketDao {
    private static final Logger LOGGER = Logger.getLogger(TicketDaoImpl.class);
    public static final String SELECT_FROM_TICKETS_WHERE_SESSION_ID_ORDER_BY_PLACE =
            "SELECT * FROM tickets WHERE sessionId = ? ORDER BY place";
    public static final String INSERT_INTO_TICKETS_USER_ID_SESSION_ID_PLACE_VALUES =
            "INSERT INTO tickets (userId, sessionId, place) VALUES (?, ?, ?)";
    private final Connection connection;

    public TicketDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean create(Ticket entity) {
        return false;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException ex) {
            LOGGER.error(ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<Ticket> getBySessionId(int sessionId) {
        List<Ticket> tickets = new ArrayList<>();
        try (PreparedStatement st = connection.prepareStatement(SELECT_FROM_TICKETS_WHERE_SESSION_ID_ORDER_BY_PLACE)) {
            st.setInt(1, sessionId);
            st.execute();
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                tickets.add(ticketsResultSet(resultSet));
            }
        } catch (SQLException ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
        return tickets;
    }

    @Override
    public boolean updateTickets(Session session, User user, List<Integer> places) {
        try (PreparedStatement st = connection.prepareStatement(INSERT_INTO_TICKETS_USER_ID_SESSION_ID_PLACE_VALUES)) {
            connection.setAutoCommit(false);
            for (Integer place : places
            ) {
                st.setInt(1, user.getUserId());
                st.setInt(2, session.getSessionId());
                st.setInt(3, place);
                st.execute();
            }
            connection.commit();
            return true;
        } catch (SQLException ex) {
            LOGGER.error(ex.getMessage(), ex);
            try {
                connection.rollback();
            } catch (SQLException e) {
                LOGGER.error(ex.getMessage(), ex);
            }
            return false;
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                LOGGER.error(ex.getMessage(), ex);
            }
        }
    }


    private Ticket ticketsResultSet(ResultSet resultSet) throws SQLException {
        if (resultSet == null) {
            return null;
        }
        Ticket ticket = new Ticket();
        ticket.setTicketId(resultSet.getInt("ticketId"));
        ticket.setPlace(resultSet.getInt("place"));
        ticket.setSessionId(resultSet.getInt("sessionId"));
        ticket.setUser(resultSet.getInt("userId"));
        return ticket;
    }

    @Override
    public Ticket getById(int id) {
        return null;
    }
}
