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

        int actid = Integer.parseInt(request.getQueryString().substring(request.getQueryString().lastIndexOf("actid") + 8, request.getQueryString().lastIndexOf("amount") - 3));
        int amount = Integer.parseInt(request.getQueryString().substring(request.getQueryString().lastIndexOf("amount") + 7, request.getQueryString().length()));

        Activity activity = Activity.getActByID(actid);
        User loggedUser = (User) request.getServletContext().getAttribute("loggedUser");
        if (activity != null) {
            activity.addDuration(amount);
            DBOperation.updateActivityDB(activity);
            response.getWriter().print(Display.showPage(loggedUser, request,1));
        }

        return "";
    }
}
