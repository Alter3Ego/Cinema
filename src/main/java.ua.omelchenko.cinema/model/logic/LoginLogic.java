package model.logic;


import model.database.DataBaseUtility;
import model.manager.ConfigurationManager;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;
import java.sql.*;

public class LoginLogic {
    private static final Logger LOGGER = Logger.getLogger(LoginLogic.class);

    public static boolean checkLogin(String login, String password) {
// check login and password
        try {
//create simple database connection
            String driver = ConfigurationManager.getInstance().getProperty(ConfigurationManager.DATABASE_DRIVER_NAME);
            Class.forName(driver);
            Connection connection = null;
//check if the user exists
            try {
                BasicDataSource dataSource = DataBaseUtility.getDataSource();
                connection = dataSource.getConnection();
                try (PreparedStatement st = connection.prepareStatement(
                        "SELECT * FROM users WHERE login = ? AND password = ?")) {
                    st.setString(1, login);
                    st.setString(2, password);
                    try (ResultSet rs = st.executeQuery()) {
                        return rs.next();
                    }
                }
            } finally {
                if (connection != null)
                    connection.close();
            }
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
    }
}