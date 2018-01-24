package dao;

import java.sql.*;
import java.util.ArrayList;

import entities.*;
import org.apache.log4j.Logger;

public class DAOOperation {
    private static final int ACTID=1;
    private static final int ACTNAME=2;
    private static final int ACTTIME=3;
    private static final int ACTMARKED=4;
    private static final int ACTUSERID=5;

    private static final int USERID=1;
    private static final int USERLOGIN=2;
    private static final int USERPASS=3;
    private static final int USERNAME=4;
    private static final int ISADMIN=5;

    private static Logger logger = Logger.getLogger(DAOOperation.class);

    // Getting userlist with activities from DB
    public static ArrayList<User> getUserListFromDB() {
        ArrayList<User> fullUserList = new ArrayList<>();
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

    // Get userlist without activities from DB
    private static ArrayList<User> getSimpleUserListFromDB() {
        String usersSQLSelect = "select * from users";
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             Statement statement = DAOConnection.getStatement(connection);
             ResultSet resultSet = DAOConnection.getResultSet(statement, usersSQLSelect)
        ) {
            ArrayList<User> userList = new ArrayList<>();
            while (resultSet.next()) {
                int userID = resultSet.getInt(USERID);
                String userLogin = resultSet.getString(USERLOGIN);
                String userPass = resultSet.getString(USERPASS);
                String userName = resultSet.getString(USERNAME);
                boolean isAdm = resultSet.getBoolean(ISADMIN);
                ArrayList<Activity> actList = new ArrayList<>();
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
        }
        return null;
    }

    // Get list of activities from DB
    public static ArrayList<Activity> getActListFromDB() {
        String actSQLSelect = "select * from activities";
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             Statement statement = DAOConnection.getStatement(connection);
             ResultSet resultSet = DAOConnection.getResultSet(statement, actSQLSelect)
        ) {
            ArrayList<Activity> activities = new ArrayList<>();
            while (resultSet.next()) {
                int actID = resultSet.getInt(ACTID);
                String actName = resultSet.getString(ACTNAME);
                long actTime = resultSet.getLong(ACTTIME);
                int actMark = resultSet.getInt(ACTMARKED);
                int actUser = resultSet.getInt(ACTUSERID);
                Activity act = new Activity(actID, actName, actTime, actMark, actUser);
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
            DAOConnection.closeStatement(statement);
            ConnectionPool.closeConnection(connection);

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
