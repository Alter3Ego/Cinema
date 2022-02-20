package controller;

import model.RequestHelper;
import model.commands.Command;
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
        req.setAttribute("typeRequest", "GET");
        LOGGER.debug("This is GET ");
        processRequest(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("typeRequest", "POST");
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page = null;
        LOGGER.debug("This is controller and reqLo " + req.getSession().getAttribute("lang"));
        req.getSession().setAttribute("temp", new TemporaryAttributes());
        Command command = requestHelper.getCommand(req);

//passing parameters to a specific command handler class
        page = command.execute(req, resp);

// method returns response page
        //call the request response page
        if (req.getAttribute("typeRequest") == "POST") {
            LOGGER.debug("This is POST ");
            resp.sendRedirect(page);

        } else {

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(req, resp);
        }
    }
}
