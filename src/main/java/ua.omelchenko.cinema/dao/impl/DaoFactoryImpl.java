package ua.omelchenko.cinema.dao.impl;


import ua.omelchenko.cinema.dao.DaoFactory;
import ua.omelchenko.cinema.dao.FilmDao;
import ua.omelchenko.cinema.dao.SessionDao;
import ua.omelchenko.cinema.dao.TicketDao;
import ua.omelchenko.cinema.dao.UserDao;
import ua.omelchenko.cinema.util.DataBaseUtility;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DaoFactoryImpl extends DaoFactory {

    private final DataSource dataSource = DataBaseUtility.getDataSource();

    @Override
    public UserDao createUserDao() {
        return new UserDaoImpl(getConnection());
    }

    @Override
    public TicketDao createTicketDao() {
        return new TicketDaoImpl(getConnection());
    }

    @Override
    public SessionDao createSessionDao() {
        return new SessionDaoImpl(getConnection());
    }

    @Override
    public FilmDao createFilmDao() {
        return new FilmDaoImpl(getConnection());
    }

    private Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
