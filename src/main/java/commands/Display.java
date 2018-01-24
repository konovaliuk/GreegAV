package commands;

import dao.DBOperation;
import entities.*;

import javax.servlet.http.HttpServletRequest;

import static commands.Paginator.doPagination;

public class Display {
    static final int ITEMS_PER_USERPAGE = 5;
    static final int ITEMS_PER_ADMINPAGE = 7;
    static int itemsInDB = DBOperation.getNumberOfActivities();
    static int numUserActivities = 0;

    public static StringBuffer showPage(User user, HttpServletRequest request, int page2show) {
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("<center>")
                .append("<h2>" + user.getUserName() + ".</h2>")
                .append("<br/>");

        if (user.isAdmin()) {
            formatAdminPage(stringBuffer, page2show);
        } else {
            formatUserPage(user, stringBuffer, request, page2show);
        }

        stringBuffer.append("</table>")
                .append("<br/>")
                .append("<center>")
                .append("<table border=0><tr><td width=25%>")
                .append("<a href='/MainServlet?command=Logout'>")
                .append("<input type='button' name='command' value='Logout'>")
                .append("</a><td>")
                .append("<center>");
        doPagination(stringBuffer, user);
        stringBuffer.append("</td></tr></table>")
                .append("<br/><br/><br/>")
                .append("<div align='center'><br/><br/><br/><font size='-1'> &copy; GreegAV 2018</font></div>")
                .append("<br/></center>");
        return stringBuffer;
    }

    private static StringBuffer formatAdminPage(StringBuffer stringBuffer, int page2show) {
        stringBuffer.append("<table border='1' cellpadding='5' width='60%' align='center'>")
                    .append("<tr><th>Id</th><th>Name</th><th>Duration</th><th>UserName</th><th>Status</th>");
        int displayedActivities = 0;
        for (Activity activity : DBOperation.getActListFromDB()) {
            displayedActivities++;
            if ((displayedActivities > (page2show - 1) * ITEMS_PER_ADMINPAGE) & (displayedActivities <= ITEMS_PER_ADMINPAGE * page2show)) {
                addLine2AdminTable(stringBuffer, activity);
            }
        }
        return stringBuffer;
    }

    private static StringBuffer formatUserPage(User user, StringBuffer stringBuffer, HttpServletRequest request, int page2show) {
        stringBuffer.append("<table border='1' cellpadding='5' width='75%' align='center'>")
                .append("<tr><th>Id</th><th>Name</th><th>Duration</th><th>Status</th><th>Add time</th>");
        numUserActivities = 0;
        for (Activity activity : DBOperation.getActListFromDB()) {
            if (user.getUserID() == activity.getUserID() | activity.getUserID() == 1) {
                numUserActivities++;
                if ((numUserActivities >= (page2show - 1) * ITEMS_PER_USERPAGE) & (numUserActivities <= ITEMS_PER_USERPAGE * page2show)) {
                    addLine2UserTable(stringBuffer, activity, request);
                }
            }
        }
        return stringBuffer;
    }

    private static void addLine2AdminTable(StringBuffer stringBuffer, Activity activity) {
        stringBuffer.append("<tr>")
                    .append("<td>" + activity.getActID() + "</td>")
                    .append("<td>" + activity.getActName() + "</td>")
                    .append("<td>" + activity.getActDuration() + "</td>")
                    .append("<td>" + activity.getUserNameByID(activity.getUserID()) + "</td>")
                    .append("<td align='center'>")
                    .append(" <form method='get' action='MainServlet'>");
        switch (activity.getActStatus()) {
            /* Marked 4 Del*/
            case 1: {
                stringBuffer.append("<a href='/MainServlet?command=changeStatus&action=remove&actid=")
                            .append(activity.getActID())
                            .append("'>")
                            .append("<input type='button' value='Remove'>")
                            .append("</a>");
                break;
            }
            /*Marked 4 Add */
            case 2: {
                stringBuffer.append("<a href='/MainServlet?command=changeStatus&action=approve&actid=")
                            .append(activity.getActID())
                            .append("'>")
                            .append("<input type='button' value='Approve'>")
                            .append("</a>");
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
        stringBuffer.append("</form></td></tr>");
    }

    private static void addLine2UserTable(StringBuffer stringBuffer, Activity activity, HttpServletRequest request) {
        stringBuffer.append("<tr>")
                    .append("<td width=5%>" + activity.getActID() + "</td>")
                    .append("<td width=50%>" + activity.getActName() + "</td>")
                    .append("<td width=10%>" + activity.getActDuration() + "</td>")
                    .append("<td  width=10% align='center'>");
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
                stringBuffer.append("<a href='/MainServlet?command=changeStatus&action=take&actid=")
                            .append(activity.getActID())
                            .append("'>")
                            .append("<input type='button' value='Take'>")
                            .append("</a>");
                break;
            }
            /*Marked as Taken */
            default: {
                stringBuffer.append("<a href='/MainServlet?command=changeStatus&action=drop&actid=")
                            .append(activity.getActID())
                            .append("'>")
                            .append("<input type='button' value='Drop'>")
                            .append("</a>");
                break;
            }
        }
        stringBuffer.append("</td><td align=center width='25%'>");
        if (activity.getUserID() != 1 & activity.getActStatus() == 4) {
            stringBuffer.append("<form method='get' action='MainServlet'>")
                        .append("<input type='hidden' name='command' value='addTime'>")
                        .append("<p><input name='actid=")
                        .append(activity.getActID())
                        .append("&amp;amount' type='number' min='1' max='86400' size='2'>&nbsp;&nbsp;")
                        .append("<input type='submit' value='Добавить время'>")
                        .append("</p>")
                        .append("</form>");
        } else {
            stringBuffer.append("&nbsp;</td></tr>");
        }
    }
}
