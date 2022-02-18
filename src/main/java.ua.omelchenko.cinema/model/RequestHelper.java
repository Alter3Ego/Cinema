package model;

import model.commands.*;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;

public class RequestHelper {
    private static final Logger LOGGER = Logger.getLogger(RequestHelper.class);

    private static RequestHelper instance = null;
    HashMap<String, Command> commands = new HashMap<>();
    ArrayList<String> temporaryAttributes = new ArrayList<>();

    private RequestHelper() {
//filling the map with commands
        commands.put("login", new LoginCommand());
        commands.put("logOut", new LogoutCommand());
        commands.put("signUp", new SignUpCommand());
        commands.put("move", new MoveCommand());
    }

    public Command getCommand(HttpServletRequest request) {
//extract command from request
        String action = request.getParameter("command");
        LOGGER.debug("Command =" + request.getParameter("command"));
//getting the object corresponding to the command
        Command command = commands.get(action);
        if (command == null) {
//if the command does not exist in the current object
            command = new MainPage();
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