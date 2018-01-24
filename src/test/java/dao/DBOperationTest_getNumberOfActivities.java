package dao;

import org.junit.Test;

import static org.junit.Assert.*;

public class DBOperationTest_getNumberOfActivities {

    @Test
    public void getNumberOfActivities() {
        int num=DBOperation.getNumberOfActivities();
        assertNotNull(num);
    }
}