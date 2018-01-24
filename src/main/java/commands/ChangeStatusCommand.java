package commands;

import dao.DBOperation;
import entities.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeStatusCommand implements controller.ICommand {
    private static final int FORDEL = 1;
    private static final int FORADD = 2;
    private static final int FREE = 3;
    private static final int TAKEN = 4;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String action = request.getParameter("action");
        Activity activity = Activity.getActByID(Integer.parseInt(request.getParameter("actid")));
        User loggedUser = (User) request.getServletContext().getAttribute("loggedUser");

        if (action.equalsIgnoreCase("remove")) {
            if (activity != null) {
                activity.setActStatus(FREE);
                activity.setUserID(1);
                activity.setUserName(User.getUserNameByID(1));
                DBOperation.updateActivityDB(activity);
                response.getWriter().print(Display.showPage(loggedUser, request,1));
            }
        }

        if (action.equalsIgnoreCase("approve")) {
            if (activity != null) {
                activity.setActStatus(TAKEN);
                DBOperation.updateActivityDB(activity);
                response.getWriter().print(Display.showPage(loggedUser, request,1));
            }
        }

        if (action.equalsIgnoreCase("take")) {
            if (activity != null) {
                activity.setActStatus(FORADD);
                activity.setUserID(loggedUser.getUserID());
                activity.setUserName(loggedUser.getUserName());
                DBOperation.updateActivityDB(activity);
                response.getWriter().print(Display.showPage(loggedUser, request,1));
            }
        }

        if (action.equalsIgnoreCase("drop")) {
            if (activity != null) {
                activity.setActStatus(FORDEL);
                DBOperation.updateActivityDB(activity);
                response.getWriter().print(Display.showPage(loggedUser, request,1));
            }
        }

        return "";
    }
}
