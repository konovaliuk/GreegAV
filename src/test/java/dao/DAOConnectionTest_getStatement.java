package dao;

import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class DAOConnectionTest_getStatement {

    @Test
    public void getStatement() throws SQLException {
//        assertNotNull(DAOConnection.getStatement(DAOConnection.getConnection()));
        assertNotNull(DAOConnection.getStatement(ConnectionPool.getInstance().getConnection()));
    }
}