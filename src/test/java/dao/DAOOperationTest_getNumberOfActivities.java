package dao;

import org.junit.Test;

import static org.junit.Assert.*;

public class DAOOperationTest_getNumberOfActivities {

    @Test
    public void getNumberOfActivities() {
        int num= DAOOperation.getNumberOfActivities();
        assertNotNull(num);
    }
}