package dao;

import java.sql.*;
import java.util.ArrayList;

import entities.*;

public class DBOperation {

//    public static ArrayList<Activity> activityList = getActListFromDB();
//    private static ArrayList<User> userList = getUserListFromDB();

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
        try {
            ArrayList<User> userList = new ArrayList<User>();
            Connection connection = DBConnection.getConnection();
            Statement statement = DBConnection.getStatement(connection);
            ResultSet resultSet = DBConnection.getResultSet(statement, usersSQLSelect);
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
            DBConnection.closeResultSet(resultSet);
            DBConnection.closeStatement(statement);
            DBConnection.closeConnection(connection);
            return userList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<Activity> getActListFromDB() {
        String actSQLSelect = "select * from activities";
        try {
            Connection connection = DBConnection.getConnection();
            Statement statement = DBConnection.getStatement(connection);
            ResultSet resultSet = DBConnection.getResultSet(statement, actSQLSelect);
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
            DBConnection.closeResultSet(resultSet);
            DBConnection.closeStatement(statement);
            DBConnection.closeConnection(connection);
            return activities;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void updateActivityDB(Activity activity) {
        final String SQL = "UPDATE timetrack.activities SET "
                + "actDuration=?, actMarked=?, userID=? WHERE " + "actID=? ";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL)) {

            statement.setLong(1, activity.getActDuration());
            statement.setInt(2, activity.getActStatus());
            statement.setInt(3, activity.getUserID());
            statement.setInt(4, activity.getActID());
            statement.executeUpdate();
            DBConnection.closeStatement(statement);
            DBConnection.closeConnection(connection);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
