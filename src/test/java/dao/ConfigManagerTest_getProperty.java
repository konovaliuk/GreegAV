package dao;

import org.junit.Test;

import static org.junit.Assert.*;

public class ConfigManagerTest_getProperty {

    @Test
    public void getProperty() {
        assertNotNull(ConfigManager.getInstance().getProperty(ConfigManager.DRIVER));
    }
}