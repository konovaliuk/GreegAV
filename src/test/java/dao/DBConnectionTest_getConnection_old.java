package dao;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class DBConnectionTest_getConnection_old {

    @Test
    public void getConnection() throws ClassNotFoundException, SQLException {
        Connection connection = null;
        ConfigManager configManager = ConfigManager.getInstance();

        String dbUser = configManager.getProperty(ConfigManager.USERNAME);
        String dbPassword = configManager.getProperty(ConfigManager.PASSWORD);
        String dbURL = configManager.getProperty(ConfigManager.URL);
        String dbName = configManager.getProperty(ConfigManager.DBNAME);
//        String dbConectionParams = configManager.getProperty(ConfigManager.CONNECTIONPARAMS);
//        String dbConnectionString = dbURL + dbName + dbConectionParams;
        String dbConnectionString = dbURL + dbName;
        //  Get a connection to database
        Class.forName("com.mysql.jdbc.Driver");
        connection=DriverManager.getConnection(dbConnectionString, dbUser, dbPassword);
        assertNotNull(connection);
    }
}