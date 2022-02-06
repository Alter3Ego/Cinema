package model.logic;

import controller.Controller;
import model.manager.ConfigurationManager;
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
            Connection cn = null;
//check if the user exists
            try {
                String url = ConfigurationManager.getInstance()
                        .getProperty(ConfigurationManager.DATABASE_URL);
                cn = DriverManager.getConnection(url);
                try (PreparedStatement st = cn.prepareStatement(
                        "SELECT * FROM users WHERE login = ? AND password = ?")) {
                    st.setString(1, login);
                    st.setString(2, password);
                    try (ResultSet rs = st.executeQuery()) {
                        return rs.next();
                    }
                }
            } finally {
                if (cn != null)
                    cn.close();
            }
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
    }
}