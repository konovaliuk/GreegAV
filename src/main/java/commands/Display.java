package commands;

import dao.DAOOperation;
import entities.*;

import javax.servlet.http.HttpServletRequest;

import static commands.Paginator.doPagination;

public class Display {
    static final int ITEMS_PER_USERPAGE = 5;
    static final int ITEMS_PER_ADMINPAGE = 10;
    static int itemsInDB = DAOOperation.getNumberOfActivities();
    static int numUserActivities = 0;

    public static StringBuffer showPage(User user, HttpServletRequest request, int page2show) {
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("<center><h2>").append(user.getUserName()).append(".</h2><br/>");

        if (user.isAdmin()) {
            formatAdminPage(stringBuffer, page2show);
        } else {
            formatUserPage(user, stringBuffer, request, page2show);
        }

        stringBuffer.append("<br/><br/>");

        doPagination(stringBuffer, user);

        stringBuffer.append("<br/><br/><center>");

        if (user.isAdmin()) {
            stringBuffer.append("</tr><tr><td>")
                    .append("<br />")
                    .append("<form method='post' action='MainServlet'>")
                    .append("<input type='hidden' name='command' value='addUser'>")
                    .append("<input type='submit' value='Добавить пользователя'>")
                    .append("</form>")
                    .append("</td>");

            stringBuffer.append("<tr><td>")
                    .append("<form method='post' action='MainServlet'>")
                    .append("<input type='hidden' name='command' value='addActivity'>")
                    .append("<input type='submit' value='Добавить Активность'>")
                    .append("</form>")
                    .append("</td>");
        }

        stringBuffer.append("<table border=0><tr><td>")
                .append("<a href='/MainServlet?command=Logout'>")
                .append("<input type='button' name='command' value='Выход'>")
                .append("</a>")
                .append("<br /></td>");


        stringBuffer.append("</tr></table>");

        stringBuffer.append("<br/><br/><br/>")
                .append("<div align='center'><br/><br/><br/><font size='-1'> &copy; GreegAV 2018</font></div>")
                .append("<br/></center>");
        return stringBuffer;
    }

    private static StringBuffer formatAdminPage(StringBuffer stringBuffer, int page2show) {
        stringBuffer.append("<table border='1' cellpadding='5' width='60%' align='center'>")
                .append("<tr><th>№№</th><th>Активность</th><th>Длительность</th><th>Владелец</th><th>Статус</th>");
        int displayedActivities = 0;
        for (Activity activity : DAOOperation.getActListFromDB()) {
            displayedActivities++;
            if ((displayedActivities > (page2show - 1) * ITEMS_PER_ADMINPAGE) & (displayedActivities <= ITEMS_PER_ADMINPAGE * page2show)) {
                addLine2AdminTable(stringBuffer, activity);
            }
        }
        stringBuffer.append("</table>");
        return stringBuffer;
    }

    private static StringBuffer formatUserPage(User user, StringBuffer stringBuffer, HttpServletRequest request, int page2show) {
        stringBuffer.append("<table border='1' cellpadding='5' width='75%' align='center'>")
                .append("<tr><th>№№</th><th>Активность</th><th>Длительность</th><th>Статус</th><th>Добавить время</th>");
        numUserActivities = 0;
        for (Activity activity : DAOOperation.getActListFromDB()) {
            if (user.getUserID() == activity.getUserID() | activity.getUserID() == 1) {
                numUserActivities++;
                if ((numUserActivities >= (page2show - 1) * ITEMS_PER_USERPAGE) & (numUserActivities <= ITEMS_PER_USERPAGE * page2show)) {
                    addLine2UserTable(stringBuffer, activity, request);
                }
            }
        }
        stringBuffer.append("</table>");
        return stringBuffer;
    }

    private static void addLine2AdminTable(StringBuffer stringBuffer, Activity activity) {
        stringBuffer.append("<tr>")
                .append("<td>").append(activity.getActID()).append("</td>")
                .append("<td>").append(activity.getActName()).append("</td>")
                .append("<td>").append(activity.getActDuration()).append("</td>")
                .append("<td>").append(activity.getUserNameByID(activity.getUserID())).append("</td>")
                .append(" <form method='get' action='MainServlet'>");
        switch (activity.getActStatus()) {
            /* Marked 4 Del*/
            case 1: {
                stringBuffer.append("<td align='center'>");
                stringBuffer.append("<a href='/MainServlet?command=changeStatus&action=remove&actid=")
                        .append(activity.getActID())
                        .append("'>")
                        .append("<input type='button' value='Удалить'>")
                        .append("</a>");
                stringBuffer.append("</td>");
                break;
            }
            /*Marked 4 Add */
            case 2: {
                stringBuffer.append("<td align='center'>");
                stringBuffer.append("<a href='/MainServlet?command=changeStatus&action=approve&actid=")
                        .append(activity.getActID())
                        .append("'>")
                        .append("<input type='button' value='Подтвердить'>")
                        .append("</a>");
                stringBuffer.append("</td>");
                break;
            }
            /*Marked as Free */
            case 3: {
                stringBuffer.append("<td align='center'>");
                stringBuffer.append("<input type='button' value='Свободна'>");
                stringBuffer.append("</td>");
                break;
            }
            /*Marked as Taken */
            default: {
                stringBuffer.append("<td align='center'>");
                stringBuffer.append("<input type='button' value='Занята'>");
                stringBuffer.append("</td>");
                break;
            }
        }

        stringBuffer.append("</form></tr>");
    }

    private static void addLine2UserTable(StringBuffer stringBuffer, Activity activity, HttpServletRequest request) {
        stringBuffer.append("<tr>")
                .append("<td width=5%>").append(activity.getActID()).append("</td>")
                .append("<td width=50%>").append(activity.getActName()).append("</td>")
                .append("<td width=10%>").append(activity.getActDuration()).append("</td>")
                .append("<td  width=10% align='center'>");
        switch (activity.getActStatus()) {
            /* Marked 4 Del*/
            case 1: {
                stringBuffer.append("<input type='button' value='На подтверждении'>");
                break;
            }
            /* Marked 4 Add*/
            case 2: {
                stringBuffer.append("<input type='button' value='На подтверждении'>");
                break;
            }
            /*Marked as Free */
            case 3: {
                stringBuffer.append("<a href='/MainServlet?command=changeStatus&action=take&actid=")
                        .append(activity.getActID())
                        .append("'>")
                        .append("<input type='button' value='Получить'>")
                        .append("</a>");
                break;
            }
            /*Marked as Taken */
            default: {
                stringBuffer.append("<a href='/MainServlet?command=changeStatus&action=drop&actid=")
                        .append(activity.getActID())
                        .append("'>")
                        .append("<input type='button' value='Отказаться'>")
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
