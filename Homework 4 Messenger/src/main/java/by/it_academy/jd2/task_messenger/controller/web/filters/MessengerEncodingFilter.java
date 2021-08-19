package by.it_academy.jd2.task_messenger.controller.web.filters;

import javax.servlet.*;
import java.io.IOException;

public class MessengerEncodingFilter implements Filter {
    private String encoding = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter("encoding");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(encoding);
        response.setContentType("text/html; charset=" + encoding);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
