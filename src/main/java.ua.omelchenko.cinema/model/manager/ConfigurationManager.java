package model.manager;

import java.util.ResourceBundle;

public class ConfigurationManager {

    private static ConfigurationManager instance;
    private ResourceBundle resourceBundle;


    private static final String BUNDLE_NAME = "config";
    public static final String DATABASE_DRIVER_NAME = "DATABASE_DRIVER_NAME";
    public static final String DATABASE_URL = "DATABASE_URL";
    public static final String DATABASE_USER = "DATABASE_USER";
    public static final String DATABASE_PASSWORD = "DATABASE_PASSWORD";
    public static final String DATABASE_MIN_IDLE = "DATABASE_MIN_IDLE";
    public static final String DATABASE_MAX_IDLE = "DATABASE_MAX_IDLE";
    public static final String DATABASE_MAX_OPEN_PREPARED_STATEMENTS = "DATABASE_MAX_OPEN_PREPARED_STATEMENTS";


    public static final String ERROR_PAGE_PATH = "ERROR_PAGE_PATH";
    public static final String LOGIN_PAGE_PATH = "LOGIN_PAGE_PATH";
    public static final String SIGN_UP_PAGE_PATH = "SIGN_UP_PAGE_PATH";
    public static final String MAIN_PAGE_PATH = "MAIN_PAGE_PATH";

    public static ConfigurationManager getInstance() {
        if (instance == null) {
            instance = new ConfigurationManager();
            instance.resourceBundle =
                    ResourceBundle.getBundle(BUNDLE_NAME);
        }
        return instance;
    }

    public String getProperty(String key) {
        return (String) resourceBundle.getObject(key);
    }

}
