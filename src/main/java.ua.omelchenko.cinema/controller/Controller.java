package controller;

import model.RequestHelper;
import model.commands.Command;
import model.manager.ConfigurationManager;
import model.manager.MessageManager;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
public class Controller extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(Controller.class);

    //an object containing a list of possible commands
    RequestHelper requestHelper = RequestHelper.getInstance();

    public Controller() {
        super();
        LOGGER.debug("Controller start");
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("typeRequest","GET");
        processRequest(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("typeRequest","POST");
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page = null;
        try {
//defining a command that came from a JSP
            Command command = requestHelper.getCommand(req);

//passing parameters to a specific command handler class
            page = command.execute(req, resp);
            System.out.println("this is page" + page);

// method returns response page
        } catch (ServletException e) {
            LOGGER.error(e.getMessage(), e);
//error message generation
            req.setAttribute("errorMessage",
                    MessageManager.getInstance().getProperty(
                            MessageManager.SERVLET_EXCEPTION_ERROR_MESSAGE));
//call JSP page with error message
            page = ConfigurationManager.getInstance()
                    .getProperty(ConfigurationManager.ERROR_PAGE_PATH);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            req.setAttribute("errorMessage",
                    MessageManager.getInstance()
                            .getProperty(MessageManager.IO_EXCEPTION_ERROR_MESSAGE));
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
        }
//call the request response page
        if (req.getAttribute("typeRequest")  == "GET") {
            LOGGER.debug("This is disp");
            LOGGER.debug("This is atribute " + req.getAttribute("user") );
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(req, resp);
        } else {
            LOGGER.debug("This is redirect");
            LOGGER.debug("This is atribute " + req.getAttribute("user"));

            resp.sendRedirect(page);
        }

    }
}
