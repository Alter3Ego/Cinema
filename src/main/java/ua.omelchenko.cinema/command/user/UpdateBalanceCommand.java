package ua.omelchenko.cinema.command.user;

import ua.omelchenko.cinema.entity.User;
import ua.omelchenko.cinema.servlet.TemporaryAttributes;
import ua.omelchenko.cinema.command.Command;

import org.apache.log4j.Logger;
import ua.omelchenko.cinema.dao.DaoFactory;
import ua.omelchenko.cinema.util.ConfigurationManager;
import ua.omelchenko.cinema.service.UserService;
import ua.omelchenko.cinema.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

public class UpdateBalanceCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(UpdateBalanceCommand.class);
    private static final String PARAM_NAME_SUM = "sum";
    private static final String REGEX_SUM = "\\d{1,8}";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse response) {
        String page = ConfigurationManager.getInstance()
                .getProperty(ConfigurationManager.USER_PAGE_PATH);
        String sum = req.getParameter(PARAM_NAME_SUM);
        if (sum == null || !sum.matches(REGEX_SUM)) {
            TemporaryAttributes tA = (TemporaryAttributes) req.getSession().getAttribute("temp");
            tA.setAttributes("errorUpdateSum", true);
            req.getSession().setAttribute("temp", tA);
            return page;
        }
        User user = (User) req.getSession().getAttribute("user");
        UserService userService = new UserServiceImpl(DaoFactory.getInstance());

        user = userService.updateBalance(user, new BigDecimal(sum));

        if (user == null) {
            page = ConfigurationManager.getInstance()
                    .getProperty(ConfigurationManager.ERROR_PAGE_PATH);
        } else {
            req.getSession().setAttribute("user", user);
        }
        return page;
    }
}
