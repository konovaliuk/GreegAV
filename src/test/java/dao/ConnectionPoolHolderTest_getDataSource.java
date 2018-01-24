package dao;

import org.junit.Test;

import static org.junit.Assert.*;

public class ConnectionPoolHolderTest_getDataSource {

    @Test
    public void getDataSource() {
        assertNotNull(ConnectionPoolHolder.getDataSource());
    }
}