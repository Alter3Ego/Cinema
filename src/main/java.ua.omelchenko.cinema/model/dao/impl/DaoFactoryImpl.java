package model.dao.impl;

import model.dao.*;
import model.database.DataBaseUtility;
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
