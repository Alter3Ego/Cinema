package ua.omelchenko.cinema.util;


import ua.omelchenko.cinema.command.Command;
import ua.omelchenko.cinema.command.LoginCommand;
import ua.omelchenko.cinema.command.MainPageCommand;
import ua.omelchenko.cinema.command.SessionPageCommand;
import ua.omelchenko.cinema.command.SignUpCommand;
import ua.omelchenko.cinema.command.admin.AddSessionActionCommand;
import ua.omelchenko.cinema.command.admin.RemoveSessionCommand;
import ua.omelchenko.cinema.command.admin.SessionAddPageCommand;
import ua.omelchenko.cinema.command.user.LogoutCommand;
import ua.omelchenko.cinema.command.user.UpdateBalanceCommand;
import ua.omelchenko.cinema.command.user.UserPageCommand;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * Command pool management
 */
public class ControllerRegistry {

    private static ControllerRegistry instance = null;
    HashMap<String, Command> commands = new HashMap<>();

    private ControllerRegistry() {
        //filling the map with commands
        commands.put("login", new LoginCommand());
        commands.put("logOut", new LogoutCommand());
        commands.put("signUp", new SignUpCommand());
        commands.put("updateBalance", new UpdateBalanceCommand());
        commands.put("sessionPage", new SessionPageCommand());
        commands.put("sessionAddPage", new SessionAddPageCommand());
        commands.put("addSessionAction", new AddSessionActionCommand());
        commands.put("removeSession", new RemoveSessionCommand());
        commands.put("userPage", new UserPageCommand());
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
    public static ControllerRegistry getInstance() {
        if (instance == null) {
            instance = new ControllerRegistry();
        }
        return instance;
    }
}