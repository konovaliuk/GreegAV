package dao;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DAOActivity {
    private static Logger logger = Logger.getLogger(DAOActivity.class);

    public static void addNewActivity(String actName) {
        String SQL = "INSERT INTO activities (actName, actDuration, actMarked,userID)" + " VALUES (?,?,?,?)";

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL)) {
            statement.setString(1, actName);
            statement.setLong(2, 0);
            statement.setInt(3, 3);
            statement.setInt(4, 1);
            statement.executeUpdate();
            logger.info("Activity " + actName + " added successfully!");
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }
}
