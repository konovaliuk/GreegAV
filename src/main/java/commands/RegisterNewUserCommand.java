package commands;

import dao.DAOUser;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterNewUserCommand implements controller.ICommand {
    private static Logger logger = Logger.getLogger(RegisterNewUserCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String newUserName = request.getParameter("nameInput");
        String newUserLogin = request.getParameter("loginInput");
        String newUserPass = request.getParameter("passInput");

        if (DAOUser.isUserLoginExists(newUserLogin)) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("<center>")
                    .append("<img src='error.png'><br /><br />")
                    .append("Логин ").append(newUserLogin).append(" уже существует в базе!")
                    .append("<br /><br />")
                    .append("<form method='post' action='MainServlet'>")
                    .append("<input type='hidden' name='command' value='addUser'>")
                    .append("<input type='submit' value='Попробовать снова'>")
                    .append("</form>");
            response.getWriter().print(stringBuffer);
        } else {
            DAOUser.addNewUser(newUserLogin, newUserName, newUserPass);
            response.getWriter().print("Пользователь создан успешно!");
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                logger.info("Pause fault!");
                logger.info(e.getMessage());
            }
        }
        return "/index.jsp";
    }
}
