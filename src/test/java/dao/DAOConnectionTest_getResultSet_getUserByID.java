package dao;

import entities.User;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DAOConnectionTest_getResultSet_getUserByID {

    @Test
    public void getResultSet() {
        assertEquals("The Admin",DAOUser.getUserNameByID(1));
    }
}