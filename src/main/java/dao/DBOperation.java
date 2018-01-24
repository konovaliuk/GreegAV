package dao;

import java.sql.*;
import java.util.ArrayList;

import entities.*;
import org.apache.log4j.Logger;

public class DBOperation {
    private static Logger logger = Logger.getLogger(DBOperation.class);

    public static ArrayList<User> getUserListFromDB() {
        ArrayList<User> fullUserList = new ArrayList<User>();
        for (User tempUser : getSimpleUserListFromDB()) {
            for (Activity actTemp : getActListFromDB()) {
                if (actTemp.getUserID() == tempUser.getUserID()) {
                    actTemp.setUserName(tempUser.getUserName());
                }
            }
            fullUserList.add(tempUser);
        }
        return fullUserList;
    }

    private static ArrayList<User> getSimpleUserListFromDB() {
        String usersSQLSelect = "select * from users";
//        try (Connection connection = DBConnection.getConnection();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             Statement statement = DBConnection.getStatement(connection);
             ResultSet resultSet = DBConnection.getResultSet(statement, usersSQLSelect)
        ) {
            ArrayList<User> userList = new ArrayList<User>();
            while (resultSet.next()) {
                int userID = resultSet.getInt(1);
                String userLogin = resultSet.getString(2);
                String userPass = resultSet.getString(3);
                String userName = resultSet.getString(4);
                boolean isAdm = resultSet.getBoolean(5);

                ArrayList<Activity> actList = new ArrayList<Activity>();
                for (Activity actTemp : getActListFromDB()) {
                    if (actTemp.getUserID() == userID) {
                        actList.add(actTemp);
                    }
                }
                User user = new User(userID, userLogin, userPass, userName, isAdm, actList);
                userList.add(user);
            }
            return userList;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static ArrayList<Activity> getActListFromDB() {
        String actSQLSelect = "select * from activities";
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             Statement statement = DBConnection.getStatement(connection);
             ResultSet resultSet = DBConnection.getResultSet(statement, actSQLSelect)
        ) {
            ArrayList<Activity> activities = new ArrayList<Activity>();
            while (resultSet.next()) {
                int actID = resultSet.getInt(1);   //actID
                String actName = resultSet.getString(2); //actName
                long actTime = resultSet.getLong(3);   // actDuration
                int actMark = resultSet.getInt(4);    //actMarked
                int actUser = resultSet.getInt(5);   //actUserID
                Activity act = new Activity(actID, actName, actTime, actMark, actUser);   //actUserID
                activities.add(act);
            }
            return activities;
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public static void updateActivityDB(Activity activity) {
        final String SQL = "UPDATE timetrack.activities SET "
                + "actDuration=?, actMarked=?, userID=? WHERE " + "actID=? ";

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL)) {

            statement.setLong(1, activity.getActDuration());
            statement.setInt(2, activity.getActStatus());
            statement.setInt(3, activity.getUserID());
            statement.setInt(4, activity.getActID());
            statement.executeUpdate();
            DBConnection.closeStatement(statement);
            DBConnection.closeConnection(connection);

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

    }

    public static int getNumberOfActivities() {
        int numActivities = 0;
        for (Activity actTemp : getActListFromDB()) {
            if (actTemp.getActID() != 0) {
                numActivities++;
            }
        }
        return numActivities;
    }
}
