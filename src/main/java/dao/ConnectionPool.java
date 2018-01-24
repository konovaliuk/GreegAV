package dao;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

public class ConnectionPool {

    private static final Logger logger = Logger.getLogger(ConnectionPool.class);
    private static ConnectionPool instance = null;
    private DataSource pool;

    private ConnectionPool() {
        try{
            pool = ConnectionPoolHolder.getDataSource();
        } catch(Exception ex){
            logger.error("Problem getting DataSource object:", ex);
        }
    }

    public static synchronized ConnectionPool getInstance() {
        logger.info("Trying to get instance of ConnectionPool.");
        if (instance == null) {
            logger.info("Trying to create ConnectionPool instance.");
            instance = new ConnectionPool();
            logger.info("ConnectioPool instance created.");
        }
        if (instance == null) {
            logger.warn("ConnectionPool instance is still null!");
        }
        logger.info("The getInstance method finished successfully.");
        return instance;
    }

    public Connection getConnection() throws SQLException {
        logger.info("Trying to get a connection from pool.");
        return pool.getConnection();
    }

    public void closeConnection(Connection connection) throws SQLException {
        logger.info("Trying to close connection.");
        if(connection != null){
            connection.close();
        } else {
            logger.warn("Connection is null!");
        }
        logger.info("The closeConnection method finished successfully.");
    }
}
