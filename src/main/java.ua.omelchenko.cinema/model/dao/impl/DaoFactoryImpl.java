package model.dao.impl;

import model.dao.DaoFactory;
import model.dao.SessionDao;
import model.dao.UserDao;
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
    public SessionDao createSessionDao() {
        return new SessionDaoImpl(getConnection());
    }

    private Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
