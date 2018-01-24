package commands;

import dao.ConnectionPool;
import dao.DAOActivity;
import entities.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddNewActivityCommand implements controller.ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String newActName = request.getParameter("newActName");
        if (!newActName.isEmpty()){
            DAOActivity.addNewActivity(newActName);
            response.getWriter().print(Display.showPage((User) request.getServletContext().getAttribute("loggedUser"), request, 1));
        } else {
            return "/newact.jsp";
        }
        return "";
    }
}

