package by.patrusova.project.servlet;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.connection.ConnectionPool;
import by.patrusova.project.exception.DaoException;
import by.patrusova.project.command.CommandProvider;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.util.stringholder.Parameters;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/controller")
public class ControlServlet extends HttpServlet {

    private final static Logger LOGGER = LogManager.getLogger();

    public void init() {
        try {
            ConnectionPool.getInstance();
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, "Cannot get instance of connection pool.");
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request,
                                HttpServletResponse response) throws IOException {
        CommandProvider provider = new CommandProvider();
        try {
            ActionCommand command = provider.defineCommand(request);
            String page = command.execute(request);
            if (request.getMethod().toLowerCase().equals(Parameters.POST.getValue())) {
                response.sendRedirect(request.getContextPath() + page);
            } else {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
                dispatcher.forward(request, response);
            }
        } catch (IOException | CommandException | ServletException e) {
            LOGGER.log(Level.ERROR, "Processing request failed.");
            response.sendError(500);
        }
    }

    public void destroy() {
        try { //todo это надо или автоматом?
            ConnectionPool.getInstance().closeAllConnections();
            LOGGER.log(Level.INFO, "All connections has been closed and drivers has been deregistered.");
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, "Cannot close all connections in connection pool.");
            e.printStackTrace();
        }
        super.destroy();
    }
}