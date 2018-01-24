package dao;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static dao.ConfigManager.DBNAME;
import static org.junit.Assert.*;

public class DAOConnectionTest_getConnection_new {

    @Test
    public void getConnection() throws SQLException {
        assertNotNull(ConnectionPool.getInstance().getConnection());
    }
}