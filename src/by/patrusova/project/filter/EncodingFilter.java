package by.patrusova.project.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Class for filtering data's encoding during request/response data sending.
 * @autor Marianna Patrusova
 * @version 1.0
 */
@WebFilter("/*")
public class EncodingFilter implements Filter {

    private final static String UTF_8 = "UTF-8";

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(UTF_8);
        servletResponse.setCharacterEncoding(UTF_8);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}