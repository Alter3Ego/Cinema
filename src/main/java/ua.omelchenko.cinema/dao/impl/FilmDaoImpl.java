package ua.omelchenko.cinema.dao.impl;

import ua.omelchenko.cinema.entity.Film;
import ua.omelchenko.cinema.dao.FilmDao;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FilmDaoImpl implements FilmDao {
    private static final Logger LOGGER = Logger.getLogger(FilmDaoImpl.class);
    public static final String SELECT_ALL_FILMS = "SELECT * FROM films order by title";
    private final Connection connection;

    public FilmDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean create(Film entity) {
        return false;
    }

    @Override
    public Film getById(int id) {
        return null;
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
    public List<Film> findAllFilms() {
        List<Film> films = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(SELECT_ALL_FILMS)) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                films.add(filmsResultSet(resultSet));
            }
        } catch (SQLException ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
        return films;
    }

   static public Film filmsResultSet(ResultSet resultSet) throws SQLException {
        if (resultSet == null) {
            return null;
        }
        Film film = new Film();
        film.setFilmId(resultSet.getInt("filmId"));
        film.setTitle(resultSet.getString("title"));
        film.setGenre(resultSet.getString("genre"));
        film.setProducer(resultSet.getString("producer"));
        film.setPrice(resultSet.getBigDecimal("price"));
        film.setReleaseYear(resultSet.getInt("releaseYear"));
        return film;
    }
}
