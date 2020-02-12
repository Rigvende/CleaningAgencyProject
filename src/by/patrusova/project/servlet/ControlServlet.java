package by.patrusova.project.servlet;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.util.AttributesEnum;
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
            processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request,
                                HttpServletResponse response) throws IOException {
        String page;
        CommandProvider provider = new CommandProvider();
        try {
            ActionCommand command = provider.defineCommand(request);
            page = command.execute(request);
            if (page != null) {
//                response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
//                response.addHeader("Location", request.getContextPath() + page); //todo что сработает как защита от f5?
//                response.sendRedirect(request.getContextPath() + page);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
                dispatcher.forward(request, response);
            } else {
                page = ConfigurationManager.getProperty(AttributesEnum.PAGE_INDEX.getValue());
                request.getSession().setAttribute(AttributesEnum.NULLPAGE.getValue(),
                        MessageManager.getProperty(AttributesEnum.MESSAGE_EMPTY.getValue()));
                response.sendRedirect(request.getContextPath() + page);
            }
        } catch (IOException | CommandException | ServletException e) {
            response.sendError(500);
        }
    }

    public void destroy() {
        super.destroy();
    }
}