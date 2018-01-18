package entities;

import dao.DBOperation;

import java.io.Serializable;

public class Activity {
    private int actID;
    private String actName;
    private long actDuration;
    private int actStatus;
    private int userID;
    private String userName;

    public Activity() {
    }

    public Activity(int actID, String actName, long actDuration, int actStatus, int userID) {
        this.actID = actID;
        this.actName = actName;
        this.actDuration = actDuration;
        this.actStatus = actStatus;
        this.userID = userID;
        this.userName ="" ;
    }

    public static Activity getActByID(int actID) {
        for (Activity activity : DBOperation.getActListFromDB()) {
            if (activity.actID == actID) return activity;
        }
        return null;
    }
    public String getUserNameByID(int userID) {
        for (User user : DBOperation.getUserListFromDB()) {
            if (user.getUserID() == userID) return user.getUserName();
        }
        return null;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getActStatus() {
        return actStatus;
    }

    public void setActStatus(int actStatus) {
        this.actStatus = actStatus;
    }

    public int getActID() {
        return actID;
    }

    public void setActID(int actID) {
        this.actID = actID;
    }

    public String getActName() {
        return actName;
    }

    public void setActName(String actName) {
        this.actName = actName;
    }

    public long getActDuration() {
        return actDuration;
    }

    public void setActDuration(long actDuration) {
        this.actDuration = actDuration;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getActNameByID(int actID) {
        for (Activity activity : DBOperation.getActListFromDB()) {
            if (activity.actID == actID) return activity.actName;
        }
        return "";
    }

    public void addDuration(long time) {
        this.actDuration += time;
    }

    @Override
    public String toString() {
        String result = "\n\r";
        result += "ID: " + this.actID + " ";
        result += "Name: " + this.actName + " ";
        result += "Time: " + this.actDuration + " ";
        result += "Status: " + this.actStatus + " ";
        result += "User: " + this.userName;
        return result;
    }
}
