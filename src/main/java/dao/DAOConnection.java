package dao;

import org.apache.log4j.Logger;

import java.sql.*;

public class DAOConnection {
    private static Logger logger = Logger.getLogger(DAOConnection.class);

    public static ResultSet getResultSet(Statement statement, String sqlSelect) {
        try {
            if (statement != null) {
                return statement.executeQuery(sqlSelect);
            } else {
                logger.error("Null statement received!");
                System.out.println("Null statement received!");
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public static Statement getStatement(Connection connection) {
        try {
            if (connection != null) {
                return connection.createStatement();
            } else {
                System.out.println("Null connection received!");
                logger.error("Null connection received!");
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public static void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
    }

    public static void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
    }
//    public static void closeConnection(Connection connection) {
//        if (connection != null) {
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                logger.error(e.getMessage());
//            }
//        }
//    }
}
