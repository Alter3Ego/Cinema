package model.commands.user;

import Entity.User;
import controller.TemporaryAttributes;
import model.commands.Command;
import model.dao.DaoFactory;
import model.manager.ConfigurationManager;
import model.service.UserService;
import model.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

public class UpdateBalance implements Command {
    private static final Logger LOGGER = Logger.getLogger(UpdateBalance.class);
    private static final String PARAM_NAME_SUM = "sum";
    private static final String REGEX_SUM = "\\d{1,8}";
    //"\\d{1,8}[.,]?\\d{0,2}";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse response) {
        String page = ConfigurationManager.getInstance()
                .getProperty(ConfigurationManager.USER_PAGE_PATH);
        String sum = req.getParameter(PARAM_NAME_SUM);
        LOGGER.debug("The sum" + sum);
        if (sum == null || !sum.matches(REGEX_SUM)) {
            TemporaryAttributes tA = (TemporaryAttributes) req.getSession().getAttribute("temp");
            tA.setErrorUpdateSum(true);
            req.getSession().setAttribute("temp", tA);
            LOGGER.debug("Regex fall");
            return page;
        }
        User user = (User) req.getSession().getAttribute("user");
        UserService userService = new UserServiceImpl(DaoFactory.getInstance());

        user = userService.updateBalance(user, new BigDecimal(sum));
        LOGGER.debug("User in session - " + user.getUserId());
        LOGGER.debug("Sum big Decimal - " + new BigDecimal(sum));
        if (user == null){
            LOGGER.debug("User is null");
           page = ConfigurationManager.getInstance()
                    .getProperty(ConfigurationManager.ERROR_PAGE_PATH);
        } else {
            req.getSession().setAttribute("user", user);
            User use2 = (User) req.getSession().getAttribute("user");
            LOGGER.debug("use2" + use2.getEmail());
        }
        return page;
    }
}
