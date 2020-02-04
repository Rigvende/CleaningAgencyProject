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
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@WebServlet(urlPatterns = "/controller")
public class ControlServlet extends HttpServlet {

    public void init() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            processRequest(request, response);
        } catch (CommandException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            processRequest(request, response);
        } catch (CommandException e) {
            e.printStackTrace();
        }
    }

    private void processRequest(HttpServletRequest request,
                                HttpServletResponse response) throws CommandException {
        String page;
        CommandProvider client = new CommandProvider();
        try {
            ActionCommand command = client.defineCommand(request);
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
        } catch (ServletException | IOException e) {
            throw new CommandException(e);
        }
    }

    public void destroy() {
        super.destroy();
    }
}