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
        } catch(Exception e){
            logger.error(e.getMessage());
        }
    }

    public static synchronized ConnectionPool getInstance() {
        if (instance == null) {
            logger.info("ConnectioPool instance creation.");
            instance = new ConnectionPool();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        logger.info("Trying to get a connection from pool.");
        return pool.getConnection();
    }

    public static void closeConnection(Connection connection) throws SQLException {
        logger.info("Trying to close connection.");
        if(connection != null){
            connection.close();
            logger.info("The closeConnection method finished successfully.");
        } else {
            logger.warn("Connection is null!");
        }
    }
}
