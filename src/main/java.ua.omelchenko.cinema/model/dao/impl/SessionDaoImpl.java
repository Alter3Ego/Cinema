package model.dao.impl;

import Entity.Film;
import Entity.Session;
import model.dao.DaoFactory;
import model.dao.SessionDao;
import model.service.UserService;
import model.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SessionDaoImpl implements SessionDao {
    private static final Logger LOGGER = Logger.getLogger(SessionDaoImpl.class);
    public static final String SELECT_WHERE_ORDER_ = "SELECT * FROM sessions s JOIN films f ON s.filmId = f.filmId " +
            "WHERE numberOfTickets < ? ORDER BY ";
    public static final String _BY_LIMIT = " LIMIT ?, ?;";
    public static final String SELECT_FROM_SESSIONS_WHERE_SESSION_ID = "SELECT * FROM sessions s JOIN films f ON s.filmId = f.filmId" +
            "  WHERE sessionId = ?";
    public static final String UPDATE_SESSIONS_WHERE_SESSION_ID = "UPDATE sessions SET numberOfTickets = numberOfTickets + ? " +
            "WHERE sessionId = ?";
    public static final String INSERT_INTO_SESSIONS_DATE_TIME_FILM_ID_VALUES = "INSERT INTO sessions (dateTime, filmId)\nVALUES (?, ?);";


    private final Connection connection;

    public SessionDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean create(Session entity) {
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
    public List<Session> findAllPage(String orderBy, int start, int end, int ticketLimit) {
        List<Session> session = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(SELECT_WHERE_ORDER_ +
                orderBy +
                _BY_LIMIT)) {
            ps.setInt(1, ticketLimit);
            ps.setInt(2, start);
            ps.setInt(3, end);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                session.add(sessionResultSet(resultSet));
            }


        } catch (SQLException ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
        return session;
    }

    @Override
    public Session getById(int id) {
        try (PreparedStatement st = connection.prepareStatement(SELECT_FROM_SESSIONS_WHERE_SESSION_ID)) {
            st.setInt(1, id);
            ResultSet resultSet = st.executeQuery();
            if (resultSet.next())
                return sessionResultSet(resultSet);
        } catch (SQLException ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
        return null;
    }

    @Override
    public Session updateTickets(Session session, int number) {

        try (PreparedStatement st = connection.prepareStatement(UPDATE_SESSIONS_WHERE_SESSION_ID)) {
            st.setInt(1, number);
            st.setInt(2, session.getSessionId());
            st.execute();
            return getById(session.getSessionId());
        } catch (SQLException ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
        return session;
    }

    @Override
    public boolean createSession(Integer filmId, java.util.Date datetime) {
        try (PreparedStatement st = connection.prepareStatement(INSERT_INTO_SESSIONS_DATE_TIME_FILM_ID_VALUES)) {
            st.setDate(1, new java.sql.Date(datetime.getTime()));
            st.setInt(2, filmId);
            st.executeUpdate();
            return true;
        } catch (SQLException ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
        return false;
    }

    @Override
    public boolean deleteSession(Session session) {
        try {
            connection.setAutoCommit(false);

            try (PreparedStatement select = connection.prepareStatement("SELECT * FROM tickets where sessionId = ?;");
                 PreparedStatement delete = connection.prepareStatement("DELETE FROM sessions where sessionId = ?");
                 Statement stmt = connection.createStatement()
            ) {
                select.setInt(1, session.getSessionId());
                ResultSet resultSet = select.executeQuery();
                while (resultSet.next()) {
                    UserService userService = new UserServiceImpl(DaoFactory.getInstance());
                    userService.updateBalance(userService.getUserById(resultSet.getInt("userId")),
                            session.getFilm().getPrice(), connection);
                }
                delete.setInt(1, session.getSessionId());

                stmt.execute("SET FOREIGN_KEY_CHECKS=0");
                delete.executeUpdate();
                stmt.execute("SET FOREIGN_KEY_CHECKS=1");
                connection.commit();
                return true;
            }
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


    private Session sessionResultSet(ResultSet resultSet) throws SQLException {
        if (resultSet == null) {
            return null;
        }
        Session session = new Session();
        session.setSessionId(resultSet.getInt("sessionId"));
        session.setDateTime(resultSet.getTimestamp("dateTime"));
        Film film = FilmDaoImpl.filmsResultSet(resultSet);
        session.setFilm(film);
        session.setNumberOfTickets(resultSet.getInt("numberOfTickets"));
        return session;
    }
}
