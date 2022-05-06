package controller.filters;

import entity.Film;
import entity.Session;
import entity.User;
import controller.TemporaryAttributes;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Access filter
 */
@WebFilter(filterName = "SecurityFilter", urlPatterns = {"/*"})
public class SecurityFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        //Extracting attributes
        final HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpSession session = req.getSession();
        String currentUri = req.getRequestURI();
        User user = (User) session.getAttribute("user");
        Session currentSession = (Session) req.getSession().getAttribute("currentSession");
        List<Film> sessionAddPage = null;

        if (req.getSession().getAttribute("temp") != null) {
            TemporaryAttributes tA = (TemporaryAttributes) req.getSession().getAttribute("temp");
            sessionAddPage = tA.getSessionAddPage();
        }
        String contextPath = req.getContextPath();
        String userRole = null;

        if (user != null) {
            userRole = user.getRole();
        }

        String path = null;

        if (userRole == null || userRole.equals("")) {
            userRole = "";
        }

        //Role checking
        if (currentUri.contains("/user.jsp") && !userRole.equals("user") && !userRole.equals("admin")) {
            path = "login.jsp";
        }
        if ((currentUri.equals("/session.jsp") && currentSession == null) ||
                (currentUri.contains("admin/addSession.jsp") && !userRole.equals("admin"))
        ) {
            path = contextPath + "/error404.jsp";
        }

        //Page redirect
        if (currentUri.contains("/index.jsp")) {
            path = currentUri.replace("index.jsp", "controller");
        }
        if (currentUri.equals("/")) {
            path = currentUri + "controller";
        }
        if (currentUri.contains("addSession.jsp") && sessionAddPage == null) {

            path = contextPath + "/controller?command=sessionAddPage";
        }
        if (path != null) {
            RequestDispatcher requestDispatcher = servletRequest.getRequestDispatcher(path);
            requestDispatcher.forward(servletRequest, servletResponse);
        } else
            filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}

