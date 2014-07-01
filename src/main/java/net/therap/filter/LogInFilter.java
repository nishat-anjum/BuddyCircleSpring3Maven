package net.therap.filter;

import net.therap.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: riffat
 * Date: 12/9/12
 * Time: 10:13 AM
 * To change this template use File | Settings | File Templates.
 */
public class LogInFilter implements Filter {
    private static final Logger log = LoggerFactory.getLogger(LogInFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String path = ((HttpServletRequest) servletRequest).getRequestURI();
        HttpSession session = ((HttpServletRequest) servletRequest).getSession();
        User user = (User) session.getAttribute("loggedUser");
        if (path.contains("/login") || path.contains("/home")) {
            if (user == null) {
                cleanCache(servletResponse);
                filterChain.doFilter(servletRequest, servletResponse);
            } else
                ((HttpServletResponse) servletResponse).sendRedirect("/BudddyCircle/posts");
        } else {
            if (user == null) {
                ((HttpServletResponse) servletResponse).sendRedirect("/BuddyCircle/home");
            } else {
                cleanCache(servletResponse);
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }
        if(path.contains("/style.css"))
        {
            HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
            httpResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            httpResponse.setHeader("Pragma", "no-cache");
            httpResponse.setDateHeader("Expires", 0);
            filterChain.doFilter(servletRequest, servletResponse);


        }

    }

    public void cleanCache(ServletResponse servletResponse) {
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        httpResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        httpResponse.setHeader("Pragma", "no-cache");
        httpResponse.setDateHeader("Expires", 0);

    }

    @Override
    public void destroy() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
