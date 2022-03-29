package model.database;

import model.manager.ConfigurationManager;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 * Configure connection pool
 */
public class DataBaseUtility {
    private static final String URL = ConfigurationManager.getInstance().getProperty(ConfigurationManager.DATABASE_URL);
    private static final String USERNAME = ConfigurationManager.getInstance().getProperty(ConfigurationManager.DATABASE_USER);
    private static final String PASSWORD = ConfigurationManager.getInstance().getProperty(ConfigurationManager.DATABASE_PASSWORD);
    private static final int MIN_IDLE = Integer.parseInt(ConfigurationManager.getInstance().getProperty(ConfigurationManager.DATABASE_MIN_IDLE));
    private static final int MAX_IDLE = Integer.parseInt(ConfigurationManager.getInstance().getProperty(ConfigurationManager.DATABASE_MAX_IDLE));
    private static final int MAX_OPEN_PREPARED_STATEMENTS = Integer.parseInt(ConfigurationManager.getInstance()
            .getProperty(ConfigurationManager.DATABASE_MAX_OPEN_PREPARED_STATEMENTS));

    private static BasicDataSource dataSource;

    public static BasicDataSource getDataSource() {
        if (dataSource == null) {
            BasicDataSource ds = new BasicDataSource();
            ds.setUrl(URL);
            ds.setUsername(USERNAME);
            ds.setPassword(PASSWORD);
            ds.setMinIdle(MIN_IDLE);
            ds.setMaxIdle(MAX_IDLE);
            ds.setMaxOpenPreparedStatements(MAX_OPEN_PREPARED_STATEMENTS);
            String driver = ConfigurationManager.getInstance().getProperty(ConfigurationManager.DATABASE_DRIVER_NAME);
            try {
                Class.forName(driver);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            dataSource = ds;
        }
        return dataSource;
    }
}
