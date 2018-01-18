package commands;

import dao.DBOperation;
import entities.Activity;
import entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddTimeCommand implements controller.ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String action = request.getParameter("action");
        int actid = Integer.parseInt(request.getParameter("actid"));
        String timeString[] = request.getParameterValues("time");
        for (String str : timeString) {
            System.out.println(str);
        }
//                request.getParameter("time");

        Activity activity = Activity.getActByID(actid);
        User loggedUser = (User) request.getServletContext().getAttribute("loggedUser");

        if (action.equalsIgnoreCase("addtime")) {
            if (activity != null) {
                response.getWriter().write("Add time");
                // TODO addtime to activity
//                DBOperation.updateActivityDB(activity);
//                response.getWriter().print(Display.showPage(loggedUser));
            }
        }


        return "";
    }
}
