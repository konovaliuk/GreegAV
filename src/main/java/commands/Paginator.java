package commands;

import entities.User;

import static commands.Display.*;

class Paginator {
    static void doPagination(StringBuffer stringBuffer, User user) {
        int userPages;
        if (!user.isAdmin()) {
            userPages = (numUserActivities % ITEMS_PER_USERPAGE > 0) ?
                    ((numUserActivities / ITEMS_PER_USERPAGE) + 1) :
                    (numUserActivities / ITEMS_PER_USERPAGE);
        } else {
            userPages = (itemsInDB % ITEMS_PER_ADMINPAGE > 0) ?
                    ((itemsInDB / ITEMS_PER_ADMINPAGE) + 1) :
                    (itemsInDB / ITEMS_PER_ADMINPAGE);
        }
        if (userPages > 1) {
            stringBuffer.append(" | ");
            for (int i = 0; i < userPages; i++) {
                stringBuffer.append("<a href='/MainServlet?command=ChangePage&Page=")
                            .append(i + 1)
                            .append("'>")
                            .append(i + 1)
                            .append("</a>")
                            .append(" | ");
            }
        }
    }
}
