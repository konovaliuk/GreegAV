package dao;

import entities.User;
import org.apache.log4j.Logger;

import java.sql.*;

public class DAOUser {
    private static Logger logger = Logger.getLogger(DAOUser.class);

    public static String getUserNameByID(int userID) {
        for (User user : DAOOperation.getUserListFromDB()) {
            if (user.getUserID() == userID)
                return user.getUserName();
        }
        return null;
    }

    public static boolean isUserLoginExists(String newUserLogin) {
        String usersSQLSelect = "select * from users";
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             Statement statement = DAOConnection.getStatement(connection);
             ResultSet resultSet = DAOConnection.getResultSet(statement, usersSQLSelect)
        ) {
            while (resultSet.next()) {
                if (resultSet.getString(2).equalsIgnoreCase(newUserLogin)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return false;
    }

    public static void addNewUser(String newUserLogin, String newUserName, String newUserPass) {
        String SQL = "INSERT INTO users (userLogin, userPassword, userName, isAdmin)" + " VALUES (?, ?, ?, ?)";

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL)) {
            statement.setString(1, newUserLogin);
            statement.setString(2, newUserPass);
            statement.setString(3, newUserName);
            statement.setBoolean(4, false);
            statement.executeUpdate();
            logger.info("User " + newUserName + " added successfully!");
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }
}
