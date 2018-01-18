package commands;

import controller.ICommand;
import entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegisterCommand implements ICommand {
    private static Logger logger = LoggerFactory.getLogger(RegisterCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        User loggedUser = User.isUserValid(request.getParameter("nameInput"), request.getParameter("passInput"));

        if (loggedUser != null) {
            System.out.println(loggedUser.getUserName());
            response.getWriter().print(Display.showPage(loggedUser));
            request.getServletContext().setAttribute("loggedUser", loggedUser);
        } else {
//            response.getWriter().print("Username/Password error!");
            logger.info("Username/Password error!");
            return "/error.jsp";
        }
        return "";
    }


}
