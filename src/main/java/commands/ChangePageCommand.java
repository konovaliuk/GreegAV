package commands;

import controller.ICommand;
import entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.apache.log4j.Logger;

public class ChangePageCommand implements ICommand {
    private static Logger logger = Logger.getLogger(ChangePageCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        User loggedUser = (User) request.getServletContext().getAttribute("loggedUser");
        int page2show = Integer.parseInt(request.getParameter("Page"));
        System.out.println("Going to page "+page2show);
        logger.info("Changing page to "+page2show);
        response.getWriter().print(Display.showPage(loggedUser, request, page2show));

        return "";
    }


}