package by.patrusova.project.servlet;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.connection.ConnectionPool;
import by.patrusova.project.exception.DaoException;
import by.patrusova.project.command.CommandProvider;
import by.patrusova.project.exception.CommandException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;
import java.io.IOException;

@WebServlet(urlPatterns = "/controller")
public class ControlServlet extends HttpServlet {

    private final static Logger LOGGER = LogManager.getLogger();
    private final static String POST = "post";

    public void init() {
        try {
            ConnectionPool.getInstance();
        } catch (DaoException e) {
            LOGGER.log(Level.FATAL, "Cannot get instance of connection pool.");
            throw new RuntimeException(e);
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

    public void destroy() {
        try {
            ConnectionPool.getInstance().closeAllConnections();
            LOGGER.log(Level.INFO,
                    "All connections has been closed and drivers has been deregistered.");
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, "Cannot close all connections in connection pool.");
            throw new RuntimeException(e);
        }
        super.destroy();
    }
}