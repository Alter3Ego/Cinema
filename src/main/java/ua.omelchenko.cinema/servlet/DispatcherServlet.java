package ua.omelchenko.cinema.servlet;


import org.apache.log4j.Logger;
import ua.omelchenko.cinema.util.ControllerRegistry;
import ua.omelchenko.cinema.command.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
public class DispatcherServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(DispatcherServlet.class);

    //an object containing a list of possible commands
    ControllerRegistry requestHelper = ControllerRegistry.getInstance();

    public DispatcherServlet() {
        super();
        LOGGER.debug("Controller start");
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("typeRequest", "GET");
        processRequest(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("typeRequest", "POST");
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page = null;
        req.getSession().setAttribute("temp", new TemporaryAttributes());
        Command command = requestHelper.getCommand(req);

        //passing parameters to a specific command handler class
        page = command.execute(req, resp);

        // method returns response page
        //call the request response page
        if (req.getAttribute("typeRequest") == "POST") {
            resp.sendRedirect(page);

        } else {

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(req, resp);
        }
    }
}
