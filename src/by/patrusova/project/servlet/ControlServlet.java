package by.patrusova.project.servlet;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.command.CommandProvider;
import by.patrusova.project.connection.ConnectionPool;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.exception.DaoException;
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

/**
 * Class for server controller.
 * @autor Marianna Patrusova
 * @version 1.0
 */
@WebServlet(urlPatterns = "/controller")
public class ControlServlet extends HttpServlet {

    private final static Logger LOGGER = LogManager.getLogger();
    private final static String POST = "post";

    /**
     * Method: describe actions during data initialization.
     */
    public void init() {
        try {
            ConnectionPool.getInstance();
        } catch (DaoException e) {
            LOGGER.log(Level.FATAL, "Cannot get instance of connection pool.");
            throw new RuntimeException(e);
        }
    }

    /**
     * Method: describe GET action.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        processRequest(request, response);
    }

    /**
     * Method: describe POST action.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        processRequest(request, response);
    }

    //define concrete actions(command) for request according with post/get parameter
    private void processRequest(HttpServletRequest request,
                                HttpServletResponse response) throws IOException {
        CommandProvider provider = new CommandProvider();
        try {
            ActionCommand command = provider.defineCommand(request);
            String page = command.execute(request);
            if (request.getMethod().toLowerCase().equals(POST)) {
                response.sendRedirect(request.getContextPath() + page);
            } else {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
                dispatcher.forward(request, response);
            }
        } catch (IOException e) {
            LOGGER.log(Level.ERROR, "Requested page not found.");
            response.sendError(404);
        } catch (CommandException | ServletException e) {
            LOGGER.log(Level.ERROR, "Processing request failed.");
            response.sendError(500);
        }
    }

    /**
     * Method: describe actions during unloading the application from the container
     */
    public void destroy() {
        try {
            ConnectionPool.getInstance().closeAllConnections();
            LOGGER.log(Level.INFO,
                    "All connections has been closed and drivers has been deregistered.");
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, "Cannot close all connections in connection pool.");
            throw new RuntimeException(e);
        } finally {
            super.destroy();
        }
    }
}