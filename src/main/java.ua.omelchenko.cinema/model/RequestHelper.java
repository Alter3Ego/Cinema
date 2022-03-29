package model;

import model.commands.*;
import model.commands.admin.AddSessionActionCommand;
import model.commands.admin.SessionAddPageCommand;
import model.commands.user.LogoutCommand;
import model.commands.user.UpdateBalance;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * Command pool management
 */
public class RequestHelper {

    private static RequestHelper instance = null;
    HashMap<String, Command> commands = new HashMap<>();

    private RequestHelper() {
        //filling the map with commands
        commands.put("login", new LoginCommand());
        commands.put("logOut", new LogoutCommand());
        commands.put("signUp", new SignUpCommand());
        commands.put("updateBalance", new UpdateBalance());
        commands.put("sessionPage", new SessionPageCommand());
        commands.put("sessionAddPage", new SessionAddPageCommand());
        commands.put("addSessionAction", new AddSessionActionCommand());

    }

    public Command getCommand(HttpServletRequest request) {
        //extract command from request
        String action = request.getParameter("command");
        //getting the object corresponding to the command
        Command command = commands.get(action);
        if (command == null) {
            //if the command does not exist in the current object
            command = new MainPageCommand();
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