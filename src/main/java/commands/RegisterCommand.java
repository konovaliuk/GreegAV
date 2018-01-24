package commands;

import controller.ICommand;
import entities.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterCommand implements ICommand {
    private static Logger logger = Logger.getLogger(RegisterCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        User loggedUser = User.isUserValid(request.getParameter("nameInput"), request.getParameter("passInput"));

        if (loggedUser != null) {
            logger.info(loggedUser.getUserName() + " logged in.");
            request.getServletContext().setAttribute("loggedUser", loggedUser);
            response.getWriter().print(Display.showPage(loggedUser, request, 1));
        } else {
            logger.info("Username/Password error!");
            return "/error.jsp";
        }
        return "";
    }
}
