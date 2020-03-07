package by.patrusova.project.listener;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Class for watching users sessions.
 * @autor Marianna Patrusova
 * @version 1.0
 */
@WebListener("/*")
public class UserSessionListener implements HttpSessionListener {

    private final static Logger LOGGER = LogManager.getLogger();

    public void sessionCreated(HttpSessionEvent sessionEvent) {
        LOGGER.log(Level.INFO, "Session created: ID=" + sessionEvent.getSession().getId());
    }

    public void sessionDestroyed(HttpSessionEvent sessionEvent) {
        LOGGER.log(Level.INFO, "Session invalided: ID=" + sessionEvent.getSession().getId());
    }
}