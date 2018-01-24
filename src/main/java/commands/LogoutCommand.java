package commands;

import controller.ICommand;
import dao.ConnectionPool;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class LogoutCommand implements ICommand {

    private static Logger logger = org.apache.log4j.Logger.getLogger(LogoutCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        request.getSession(false);
        //TODO commit changes to DB
        try {
            ConnectionPool.getInstance().closeConnection(ConnectionPool.getInstance().getConnection());
        } catch (SQLException e) {
            logger.info("Failed to close connection in logout");
            System.out.println("Failed to close connection in logout");
            logger.error(e.getMessage());
        }
        logger.info("LogoutCommand passed!!!");
        return "/index.jsp";
    }
}
