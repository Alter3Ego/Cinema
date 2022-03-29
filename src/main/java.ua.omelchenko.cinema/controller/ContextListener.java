package controller;

import model.manager.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {
    private static final Logger LOGGER = Logger.getLogger(ContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LOGGER.debug("Servlet has been started");
        int maxHallCapacity = ConfigurationManager.getInstance()
                .getNumberProperty(ConfigurationManager.HALL_CAPACITY);
        sce.getServletContext().setAttribute("maxHallCapacity", maxHallCapacity);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        LOGGER.debug("Servlet has been stopped");
    }
}
