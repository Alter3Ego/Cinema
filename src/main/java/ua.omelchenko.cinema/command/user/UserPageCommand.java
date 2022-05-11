package ua.omelchenko.cinema.command.user;

import ua.omelchenko.cinema.entity.User;
import ua.omelchenko.cinema.command.Command;
import ua.omelchenko.cinema.dao.DaoFactory;
import ua.omelchenko.cinema.util.ConfigurationManager;
import ua.omelchenko.cinema.service.UserService;
import ua.omelchenko.cinema.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        if(request.getSession().getAttribute("user") != null){
            User user = (User) request.getSession().getAttribute("user");
            UserService userService = new UserServiceImpl(DaoFactory.getInstance());
            user = userService.getUserById(user.getUserId());
            request.getSession().setAttribute("user", user);
            return ConfigurationManager.getInstance()
                    .getProperty(ConfigurationManager.USER_PAGE_PATH);
        }
        return ConfigurationManager.getInstance()
                .getProperty(ConfigurationManager.LOGIN_PAGE_PATH);
    }
}
