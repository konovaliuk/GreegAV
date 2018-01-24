package dao;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static dao.ConfigManager.DBNAME;
import static org.junit.Assert.*;

public class DBConnectionTest_getConnection_new {

    @Test
    public void getConnection() throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
//        assertNotNull(DBConnection.getConnection());
        assertNotNull(ConnectionPool.getInstance().getConnection());
    }
}