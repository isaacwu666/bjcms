package tomweb.xyz.bjcms;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.logging.LogRecord;
@WebFilter(filterName = "AdminFilter", urlPatterns = "/adminApi/*")
@Component
public class AdminFilter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        if("OPTIONS".equals(req.getMethod().toUpperCase())) {
            filterChain.doFilter(req, servletResponse);
        }
        String url=req.getRequestURI();
        if (!url.startsWith("/adminApi")){
            filterChain.doFilter(req, servletResponse);
        }
        String token = req.getHeader("token");
        if (token != null) {
            //    :TODO check token

            filterChain.doFilter(req, servletResponse);
        } else {

        }

    }
}
