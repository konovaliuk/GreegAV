package dao;

import entities.User;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DBConnectionTest_getResultSet_getUserByID {

    @Test
    public void getResultSet() {
        assertEquals("The Admin",User.getUserNameByID(1));
    }
}