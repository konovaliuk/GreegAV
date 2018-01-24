package dao;

import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;

public class ConnectionPoolHolder {
    private static DataSource dataSource;

    public static DataSource getDataSource() {
        ConfigManager configManager=ConfigManager.getInstance();
        if (dataSource == null) {
            synchronized (ConnectionPoolHolder.class) {
                if (dataSource == null) {
                    BasicDataSource ds = new BasicDataSource();
                    ds.setUrl(configManager.getProperty(ConfigManager.URL)
                             +configManager.getProperty(ConfigManager.DBNAME)
                             +configManager.getProperty(ConfigManager.CONNECTIONPARAMS));
                    ds.setDriverClassName(configManager.getProperty(ConfigManager.DRIVER));
                    ds.setUsername(configManager.getProperty(ConfigManager.USERNAME));
                    ds.setPassword(configManager.getProperty(ConfigManager.PASSWORD));
                    ds.setMinIdle(5);
                    ds.setMaxIdle(20);
                    ds.setMaxActive(20);
                    ds.setMaxOpenPreparedStatements(100);
                    dataSource = ds;
                }
            }
        }
        return dataSource;
    }
}
