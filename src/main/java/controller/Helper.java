package controller;

import commands.*;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public class Helper {

    private static Logger logger = org.apache.log4j.Logger.getLogger(RegisterCommand.class);
    private static Helper instance = null;
    private HashMap<String, ICommand> commands = new HashMap<>();

    // Adding command to list of available commands
    private Helper() {
        commands.put("Logout", new LogoutCommand());
        commands.put("Login", new RegisterCommand());
        commands.put("changeStatus", new ChangeStatusCommand());
        commands.put("addTime", new AddTimeCommand());
        commands.put("ChangePage", new ChangePageCommand());
    }

    public static Helper getInstance() {
        if (instance == null) {
            synchronized (Helper.class) {
                if (instance == null)
                    instance = new Helper();
            }
        }
        return instance;
    }

    // Getting passed command
    public ICommand getCommand(HttpServletRequest request) {
        String parsedCommand = request.getParameter("command");
        ICommand command = commands.get(parsedCommand);
        if (command == null) {
            command = new HomeCommand();
            logger.info("Transfer to homepage");
        }
        logger.info("Transfer to page " + parsedCommand);
        return command;
    }
}
