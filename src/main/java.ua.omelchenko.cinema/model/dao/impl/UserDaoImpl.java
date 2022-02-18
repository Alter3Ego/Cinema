package model.dao.impl;

import Entity.User;
import model.dao.UserDao;
import org.apache.log4j.Logger;
import java.sql.*;

public class UserDaoImpl implements UserDao {
    private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);
    public static final String SELECT_USERS_WHERE_EMAIL_AND_PASSWORD =
            "SELECT * FROM users JOIN roles ON users.roleId = roles.roleId WHERE email = ? AND password = ?";
    public static final String SELECT_FROM_USERS_WHERE_EMAIL = "SELECT * FROM users WHERE email = ?";
    public static final String INSERT_VALUES_INTO_USERS = "INSERT into users(firstName, lastName, email, password) values(?,?,?,?)";

    private final Connection connection;

    public UserDaoImpl(Connection connection) {
        this.connection = connection;
    }

    public User findUserByEmailAndPass(String email, String password) {
        User user = null;
        try (PreparedStatement st = connection.prepareStatement(SELECT_USERS_WHERE_EMAIL_AND_PASSWORD)) {
            st.setString(1, email);
            st.setString(2, password);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                user = new User();
                user.setUserId(resultSet.getInt("userId"));
                user.setEmail(resultSet.getString("email"));
                user.setFirstName(resultSet.getString("firstName"));
                user.setLastName(resultSet.getString("lastName"));
                user.setPassword(resultSet.getString("password"));
                user.setBalance(resultSet.getBigDecimal("balance"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(resultSet.getString("role"));
            }
            return user;
        } catch (SQLException ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
        return user;
    }

    @Override
    public boolean findEmail(String email) {
        try (PreparedStatement st = connection.prepareStatement(SELECT_FROM_USERS_WHERE_EMAIL)) {
            st.setString(1, email);
            ResultSet resultSet = st.executeQuery();
            return resultSet.next();
        } catch (SQLException ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
        return false;
    }

    @Override
    public boolean create(User user) {
        try (PreparedStatement st = connection.prepareStatement(INSERT_VALUES_INTO_USERS)) {
            connection.setAutoCommit(false);
            st.setString(1, user.getFirstName());
            st.setString(2, user.getLastName());
            st.setString(3, user.getEmail());
            st.setString(4, user.getPassword());
            st.execute();
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


    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException ex) {
            LOGGER.error(ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }
    }
}