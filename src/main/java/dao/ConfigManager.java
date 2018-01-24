package dao;

import java.util.ResourceBundle;

public class ConfigManager {

    public static final String DRIVER = "DRIVER";
    public static final String URL = "URL";
    public static final String USERNAME = "USERNAME";
    public static final String PASSWORD = "PASSWORD";
    public static final String DBNAME = "DBNAME";
    public static final String CONNECTIONPARAMS = "CONNECTIONPARAMS";

    private static final String BUNDLE_NAME = "config";
    private static ConfigManager instance;
    private ResourceBundle resource;

    private ConfigManager() {

    }

    public static ConfigManager getInstance() {
        if (instance == null) {
            instance = new ConfigManager();
            instance.resource = ResourceBundle.getBundle(BUNDLE_NAME);
        }
        return instance;
    }

    public String getProperty(String key) {
        return (String) resource.getObject(key);
    }
}
