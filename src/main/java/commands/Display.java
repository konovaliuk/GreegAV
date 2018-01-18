package commands;

import dao.DBOperation;
import entities.*;

public class Display {
    public static StringBuffer showPage(User user) {
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("Hello, " + user.getUserName() + ".");
        stringBuffer.append("<br/>");

        if (user.isAdmin()) {
            formatAdminPage(stringBuffer);
        } else {
            formatUserPage(user, stringBuffer);
        }

        stringBuffer.append("</table>");
        stringBuffer.append("<br/><br/><br/>");
        stringBuffer.append("<a href='/MainServlet?command=Logout'>");
        stringBuffer.append("<input type='button' name='command' value='Logout'>");
        stringBuffer.append("</a>");
        stringBuffer.append("</form>");
        return stringBuffer;
    }

    private static StringBuffer formatAdminPage(StringBuffer stringBuffer) {
        stringBuffer.append("<table border='1' cellpadding='5' width='60%' align='center'>");
        stringBuffer.append("<tr><th>Id</th><th>Name</th><th>Duration</th><th>UserName</th><th>Status</th>");
///////////////////
        for (Activity activity : DBOperation.getActListFromDB()) {
            stringBuffer.append("<tr>");
            stringBuffer.append("<td>" + activity.getActID() + "</td>");
            stringBuffer.append("<td>" + activity.getActName() + "</td>");
            stringBuffer.append("<td>" + activity.getActDuration() + "</td>");
            stringBuffer.append("<td>" + activity.getUserNameByID(activity.getUserID()) + "</td>");
            stringBuffer.append("<td align='center'>");
            stringBuffer.append(" <form method='get' action='MainServlet'>");
            switch (activity.getActStatus()) {
                /* Marked 4 Del*/
                case 1: {
                    stringBuffer.append("<a href='/MainServlet?command=changeStatus&action=remove&actid=");
                    stringBuffer.append(activity.getActID());
                    stringBuffer.append("'>");
                    stringBuffer.append("<input type='button' value='Remove'>");
                    stringBuffer.append("</a>");
                    break;
                }
                /*Marked 4 Add */
                case 2: {
                    stringBuffer.append("<a href='/MainServlet?command=changeStatus&action=approve&actid=");
                    stringBuffer.append(activity.getActID());
                    stringBuffer.append("'>");
                    stringBuffer.append("<input type='button' value='Approve'>");
                    stringBuffer.append("</a>");
                    break;
                }
                /*Marked as Free */
                case 3: {
                    stringBuffer.append("<input type='button' value='Free'>");
                    break;
                }
                /*Marked as Taken */
                default: {
                    stringBuffer.append("<input type='button' value='Taken'>");
                    break;
                }
            }
            stringBuffer.append("</td>");
            stringBuffer.append("</tr>");
        }
//////////////////


        return stringBuffer;
    }

    private static StringBuffer formatUserPage(User user, StringBuffer stringBuffer) {
        stringBuffer.append("<table border='1' cellpadding='4' width='60%' align='center'>");
        stringBuffer.append("<tr><th>Id</th><th>Name</th><th>Duration</th><th>Status</th>");
        for (Activity activity : DBOperation.getActListFromDB()) {
            if (user.getUserID() == activity.getUserID() | activity.getUserID()==1) {
                stringBuffer.append("<tr>");
                stringBuffer.append("<td>" + activity.getActID() + "</td>");
                stringBuffer.append("<td>" + activity.getActName() + "</td>");
                stringBuffer.append("<td>" + activity.getActDuration() + "</td>");
                stringBuffer.append("<td align='center'>");
                stringBuffer.append(" <form method='get' action='MainServlet'>");
                switch (activity.getActStatus()) {
                    /* Marked 4 Del*/
                    case 1: {
                        stringBuffer.append("<input type='button' value='Pending deletion'>");
                        break;
                    }
                    /* Marked 4 Add*/
                    case 2: {
                        stringBuffer.append("<input type='button' value='Pending addition'>");
                        break;
                    }
                    /*Marked as Free */
                    case 3: {
                        stringBuffer.append("<a href='/MainServlet?command=changeStatus&action=take&actid=");
                        stringBuffer.append(activity.getActID());
                        stringBuffer.append("'>");
                        stringBuffer.append("<input type='button' value='Take'>");
                        stringBuffer.append("</a>");
                        break;
                    }
                    /*Marked as Taken */
                    default: {
                        stringBuffer.append("<a href='/MainServlet?command=changeStatus&action=drop&actid=");
                        stringBuffer.append(activity.getActID());
                        stringBuffer.append("'>");
                        stringBuffer.append("<input type='button' value='Drop'>");
                        stringBuffer.append("</a>");
                        break;
                    }
                }
                stringBuffer.append("</td>");
                stringBuffer.append("</tr>");
            }
        }
        return stringBuffer;
    }
}
