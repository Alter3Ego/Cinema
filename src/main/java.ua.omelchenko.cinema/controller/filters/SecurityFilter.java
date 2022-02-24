package controller.filters;

import Entity.User;
import org.apache.log4j.Logger;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "SecurityFilter", urlPatterns = {"/*"})
public class SecurityFilter implements Filter {
    private static final Logger LOGGER = Logger.getLogger(SecurityFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        final HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpSession session = req.getSession();
        String currentUri = req.getRequestURI();
        LOGGER.debug("InputFilterURI" + currentUri);
        User user = (User) session.getAttribute("user");
        String userRole = null;
        if (user != null) {
            userRole = user.getRole();
        }
        LOGGER.debug("ATTRIBUTE ROLE " + userRole);
        String path = null;
        if (userRole == null || userRole.equals("")) {
            userRole = "";
        }
        if (currentUri.contains("/index.jsp")) {
            path = currentUri.replace("index.jsp", "controller");
        }
        if (currentUri.equals("/")) {
            path = currentUri + "controller";
        }
        if (currentUri.contains("/user.jsp") && !userRole.equals("user") && !userRole.equals("admin")) {
            path = "login.jsp";
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

