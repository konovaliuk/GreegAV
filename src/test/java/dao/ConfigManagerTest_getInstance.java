package dao;

import org.junit.Test;

import static org.junit.Assert.*;

public class ConfigManagerTest_getInstance {

    @Test
    public void getInstance() {
        assertNotNull(ConfigManager.getInstance());
    }


}