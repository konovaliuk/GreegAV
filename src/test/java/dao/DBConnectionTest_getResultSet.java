package dao;

import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class DBConnectionTest_getResultSet {

    @Test
    public void getResultSet() throws SQLException {
//        ResultSet resultSet=DBConnection.getStatement(DBConnection.getConnection()).executeQuery("SELECT * FROM users");
        ResultSet resultSet=DBConnection.getStatement(ConnectionPool.getInstance().getConnection()).executeQuery("SELECT * FROM users");
        assertNotNull(resultSet);
    }
}