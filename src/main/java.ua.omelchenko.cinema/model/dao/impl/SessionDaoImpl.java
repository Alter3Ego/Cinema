package model.dao.impl;

import Entity.Film;
import Entity.Session;
import model.dao.SessionDao;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SessionDaoImpl implements SessionDao {
    private static final Logger LOGGER = Logger.getLogger(SessionDaoImpl.class);

    private static final String SELECT_ALL_LIMIT_ORDERED_BY_DATA_TIME = "SELECT * FROM sessions s JOIN films f ON" +
            " s.filmId = f.filmId ORDER BY ? LIMIT ?, ?;";

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
    public List<Session> findAllPage(String orderBy, int start, int end) {
        List<Session> session = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(SELECT_ALL_LIMIT_ORDERED_BY_DATA_TIME)) {
            ps.setString(1, orderBy);
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

    private Session sessionResultSet(ResultSet resultSet) throws SQLException {
        if (resultSet == null) {
            return null;
        }
        Session session = new Session();
        session.setSessionId(resultSet.getInt("sessionId"));
        session.setDateTime(resultSet.getTimestamp("dateTime"));
        Film film = new Film();
        film.setFilmId(resultSet.getInt("filmId"));
        film.setTitle(resultSet.getString("title"));
        film.setGenre(resultSet.getString("genre"));
        film.setProducer(resultSet.getString("producer"));
        film.setPrice(resultSet.getBigDecimal("price"));
        film.setReleaseYear(resultSet.getInt("releaseYear"));
        session.setFilm(film);
        return session;
    }
}
