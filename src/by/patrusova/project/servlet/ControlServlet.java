package by.patrusova.project.servlet;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.command.CommandProvider;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.MessageManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/controller")
public class ControlServlet extends HttpServlet {

    public void init() {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)  throws IOException{
            processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request,
                                HttpServletResponse response) throws IOException {
        String page;
        CommandProvider provider = new CommandProvider();
        try {
            request.setCharacterEncoding("UTF-8"); //todo  в фильтр
            response.setCharacterEncoding("UTF-8");
//            request.setAttribute("text", new TextMap()); todo как подгрузить мап для интернационализации
            ActionCommand command = provider.defineCommand(request);
            page = command.execute(request);
            if (page != null) {
                RequestDispatcher dispatcher
                        = getServletContext().getRequestDispatcher(page);
                dispatcher.forward(request, response);
            } else {
                page = ConfigurationManager.getProperty("page.index");
                request.getSession().setAttribute("nullpage",
                        MessageManager.getProperty("message.nullpage"));
                response.sendRedirect(request.getContextPath() + page);
            }
        } catch (ServletException | IOException | CommandException  e) {
            response.sendError(500);
        }
    }

    public void destroy() {
        super.destroy();
    }
}