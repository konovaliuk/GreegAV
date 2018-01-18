package controller;


import commands.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Helper {

    private static Logger logger = LoggerFactory.getLogger(RegisterCommand.class);
    private static Helper instance = null;
    private HashMap<String, ICommand> commands = new HashMap<>();

    // Adding command to list of available commands
    private Helper() {
        commands.put("Logout", new LogoutCommand());
        commands.put("Login", new RegisterCommand());
        commands.put("changeStatus", new ChangeStatusCommand());
        commands.put("Add time", new AddTimeCommand());
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
            logger.info("Переход на домашнюю страницу.");
        }
        logger.info("Переход на cтраницу " + parsedCommand);
        return command;
    }
}
