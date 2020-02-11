package by.patrusova.project.service;

import by.patrusova.project.connection.ProxyConnection;
import by.patrusova.project.dao.DaoFactory;
import by.patrusova.project.dao.impl.CleanerDao;
import by.patrusova.project.entity.impl.Cleaner;
import by.patrusova.project.exception.DaoException;
import by.patrusova.project.exception.ServiceException;
import java.sql.SQLException;

public class CleanerService {

    public static Cleaner getCleaner(Cleaner cleaner) throws ServiceException, SQLException {
        DaoFactory factory = new DaoFactory();
        ProxyConnection connection = null;
        try {
            connection = (ProxyConnection) factory.getConnection();
            connection.setAutoCommit(false);
            CleanerDao dao = factory.createCleanerDao(connection);
            cleaner = (Cleaner) dao.findEntityById(cleaner.getIdUser());
            connection.commit();
        } catch (DaoException | SQLException e) {
            if (connection != null) {
                connection.rollback();
            }
            throw new ServiceException(e);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return cleaner;
    }
}