package controller.filters;

import model.manager.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Catch all Exceptions that go to the user
 */
@WebFilter(filterName = "ExceptionFilter", urlPatterns = {"/*"})
public class ExceptionFilter implements Filter {
    private static final Logger LOGGER = Logger.getLogger(ExceptionFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) {
        try {
            filterChain.doFilter(request, response);
        } catch (Throwable e) {
            LOGGER.error(e.getMessage(), e);
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            try {
                httpResponse.sendRedirect(ConfigurationManager.getInstance().getProperty("ERROR_PAGE_PATH"));
            } catch (IOException ex) {
                LOGGER.error(ex.getMessage(), ex);
            }
        }

    }

    @Override
    public void destroy() {
    }
}