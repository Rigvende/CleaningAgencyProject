package add.secure;

import by.patrusova.project.entity.impl.User;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class SecureFilter implements Filter {

    public SecureFilter() {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String servletPath = request.getServletPath();
        // Информация пользователя сохранена в Session
        // (После успешного входа в систему).
        User user = UserUtils.getLoginedUser(request.getSession());
        if (servletPath.equals("/login")) {
            chain.doFilter(request, response);
            return;
        }
        HttpServletRequest wrapRequest = request;
        if (user != null) {
            // User Name
            String userName = user.getName();
            // Роли (Role).
            String role = user.getRole();
            // Старый пакет request с помощью нового Request с информацией userName и Roles.
            wrapRequest = new UserRoleRequestWrapper(userName, role, request);
        }
        // Страницы требующие входа в систему.
        if (SecureChecking.isSecurityPage(request)) {
            // Если пользователь еще не вошел в систему,
            // Redirect (перенаправить) к странице логина.
            if (user == null) {
                String requestUri = request.getRequestURI();
                // Сохранить текущую страницу для перенаправления (redirect) после успешного входа в систему.
                int redirectId = UserUtils.storeRedirectAfterLoginUrl(request.getSession(), requestUri);
                response.sendRedirect(wrapRequest.getContextPath() + "/login?redirectId=" + redirectId);
                return;
            }
            // Проверить пользователь имеет действительную роль или нет?
            boolean hasPermission = SecureChecking.hasPermission(wrapRequest);
            if (!hasPermission) {
                RequestDispatcher dispatcher //
                        = request.getServletContext().getRequestDispatcher("/jsp/accessDenied.jsp");
                dispatcher.forward(request, response);
                return;
            }
        }
        chain.doFilter(wrapRequest, response);
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
    }
}
