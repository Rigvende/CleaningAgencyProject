package by.patrusova.project.service.impl;

import by.patrusova.project.dao.DaoFactory;
import by.patrusova.project.dao.impl.UserDao;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.impl.Client;
import by.patrusova.project.entity.impl.User;
import by.patrusova.project.exception.DaoException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.Serviceable;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

/**
 * Class for implementation of service logic concerning sending mails
 * using operations with {@link UserDao}
 * @autor Marianna Patrusova
 * @version 1.0
 */
public class MailService implements Serviceable {

    private final static Logger LOGGER = LogManager.getLogger();
    private static final String PATH_CONFIG = "mail.properties";
    private static final String USER = "mail.user";
    private static final String PASSWORD = "mail.password";

    public boolean doService(String sendTo, String subject, String messageToSend) throws ServiceException {
        Properties properties = loadProperties();
        final String user = properties.getProperty(USER);
        final String password = properties.getProperty(PASSWORD);
        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(sendTo));
            message.setSubject(subject);
            message.setText(messageToSend);
            Transport.send(message);
        } catch (MessagingException e) {
            LOGGER.log(Level.ERROR, "Error while sending e-mail has occurred. ", e);
            throw new ServiceException(e);
        }
        return true;
    }

    private Properties loadProperties(){
        Properties properties = new Properties();
        try (InputStream inputStream = getClass().getClassLoader()
                                       .getResourceAsStream(MailService.PATH_CONFIG)) {
            assert inputStream != null;
            properties.load(inputStream);
        } catch (IOException e) {
            LOGGER.log(Level.FATAL, "Error while reading properties for e-mail has occurred. ", e);
            throw new RuntimeException(e);
        }
        return properties;
    }

    //get data for e-mailing confirmation
    @Override
    public Optional<AbstractEntity> doService(AbstractEntity entity) throws ServiceException {
        Client client = (Client) entity;
        User user;
        try {
            UserDao dao = DaoFactory.createUserDao();
            user = (User) dao.findUserByIdClient(client.getId());
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, "Error while sending work confirmation by e-mail has occurred. ", e);
            throw new ServiceException(e);
        }
        return user != null ? Optional.of(user) : Optional.empty();
    }
}