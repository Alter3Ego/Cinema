package model;

import controller.Controller;
import model.commands.Command;
import model.commands.LoginCommand;
import model.commands.NoCommand;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public class RequestHelper {
    private static final Logger LOGGER = Logger.getLogger(RequestHelper.class);

    private static RequestHelper instance = null;
    HashMap<String, Command> commands = new HashMap<>();

    private RequestHelper() {
//filling the map with commands
        commands.put("login", new LoginCommand());
    }

    public Command getCommand(HttpServletRequest request) {
//extract command from request
        String action = request.getParameter("command");
//getting the object corresponding to the command
        Command command = commands.get(action);
        if (command == null) {
//if the command does not exist in the current object
            command = new NoCommand();
        }
        return command;
    }

    //creating a single object from a pattern Singleton
    public static RequestHelper getInstance() {
        if (instance == null) {
            instance = new RequestHelper();
        }
        return instance;
    }
}