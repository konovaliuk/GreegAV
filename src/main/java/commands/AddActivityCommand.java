package commands;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddActivityCommand implements controller.ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)  {
        return "/newact.jsp";
    }
}
