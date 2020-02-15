//package by.patrusova.project.filter;
//
//import by.patrusova.project.util.stringholder.Attributes;
//import by.patrusova.project.util.stringholder.Pages;
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//@WebFilter(urlPatterns = { "/controller" }, servletNames = { "ControlServlet" })
//public class SecurityFilter implements Filter {
//
//    public void destroy() {
//    }
//
//    public void doFilter(ServletRequest request, ServletResponse response,
//                         FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest req = (HttpServletRequest) request;
//        HttpServletResponse resp = (HttpServletResponse) response;
//        HttpSession session = req.getSession();
//        String role = (String) session.getAttribute(Attributes.ROLE.getValue());
//        if (role == null) {
//            session.setAttribute("role", "guest");
//            RequestDispatcher dispatcher = request.getServletContext()
//                    .getRequestDispatcher(Pages.PAGE_INDEX.getValue());
//            dispatcher.forward(req, resp);
//            return;
//        }
//        chain.doFilter(request, response);
//    }
//
//    public void init(FilterConfig fConfig) {
//    }
//}