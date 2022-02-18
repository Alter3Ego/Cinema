package controller.filters;

import org.apache.log4j.Logger;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
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
        LOGGER.debug("This is SecurityFilter " + currentUri);
        String path = null;
        if (currentUri.equals("/index.jsp") || currentUri.equals("/")) {
            path = "/controller";
        }
        if (currentUri.equals("/login.jpg")||currentUri.equals("/signUp.jpg")) {
            path = "/controller?command=move&URI=" + currentUri;
        }
        if (path != null) {
            RequestDispatcher requestDispatcher = servletRequest.getRequestDispatcher(path);
            requestDispatcher.forward(servletRequest, servletResponse);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
    }
}

