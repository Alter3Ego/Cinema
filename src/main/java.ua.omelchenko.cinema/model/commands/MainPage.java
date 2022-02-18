package model.commands;

import Entity.Session;
import controller.TemporaryAttributes;
import model.dao.DaoFactory;
import model.manager.ConfigurationManager;
import model.service.SessionService;
import model.service.impl.SessionServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class MainPage implements Command {
    private static final Logger LOGGER = Logger.getLogger(MainPage.class);
    public static final int NUMBERS_OF_OBJECTS = 4;
    public static final int ONE_MORE_OBJECT = NUMBERS_OF_OBJECTS + 1;


    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Integer number = null;
        if (request.getParameter("mainPage") != null) {
            number = Integer.valueOf(request.getParameter("mainPage"));
        }
        LOGGER.debug("This is NoCommand " + number);
        pagination(request, number);
        return ConfigurationManager.getInstance()
                .getProperty(ConfigurationManager.MAIN_PAGE_PATH);
    }

    private Session extract(List<Session> sessionList) {
        if (sessionList.iterator().hasNext()) {
            Session session = sessionList.iterator().next();
            sessionList.remove(0);
            return session;
        }
        return null;

    }

    private void pagination(HttpServletRequest request, Integer previousPage) {
        SessionService sessionService = new SessionServiceImpl(DaoFactory.getInstance());
        int start = 0;
        if (previousPage != null) {
            start = previousPage - 1;
        }
        int end = start + ONE_MORE_OBJECT;
        List<Session> sessionList = sessionService.getServicesOrderByLimits("dataTime", start, end);
        TemporaryAttributes tA = (TemporaryAttributes) request.getSession().getAttribute("temp");
        tA.setMainPreviousPage(end - NUMBERS_OF_OBJECTS * 2);
        if (sessionList.size() >= ONE_MORE_OBJECT) {
            tA.setMainNextPage(end);
            LOGGER.debug("This is NoCommand GO " + tA.getMainNextPage());
        }

        LOGGER.debug("This is NoCommand Back " + tA.getMainPreviousPage());
        request.getSession().setAttribute("temp", tA);

        Session session1 = extract(sessionList);
        Session session2 = extract(sessionList);
        Session session3 = extract(sessionList);
        Session session4 = extract(sessionList);

        request.getSession().setAttribute("mainSession1", session1);
        request.getSession().setAttribute("mainSession2", session2);
        request.getSession().setAttribute("mainSession3", session3);
        request.getSession().setAttribute("mainSession4", session4);
    }
}