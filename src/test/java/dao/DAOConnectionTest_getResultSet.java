package dao;

import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class DAOConnectionTest_getResultSet {

    @Test
    public void getResultSet() throws SQLException {
//        ResultSet resultSet= DAOConnection.getStatement(ConnectionPool.getInstance().getConnection()).executeQuery("SELECT * FROM users");
        assertNotNull(DAOConnection.getStatement(ConnectionPool.getInstance().getConnection()).executeQuery("SELECT * FROM users"));
    }
}