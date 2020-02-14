package by.patrusova.project.service.impl;

import by.patrusova.project.dao.DaoFactory;
import by.patrusova.project.dao.impl.CleanerDao;
import by.patrusova.project.entity.impl.Cleaner;
import by.patrusova.project.exception.DaoException;
import by.patrusova.project.exception.ServiceException;
import java.sql.SQLException;

public class CleanerService {

    public static Cleaner getCleaner(Cleaner cleaner) throws ServiceException, SQLException {
        DaoFactory factory = new DaoFactory();
        try {
            CleanerDao dao = factory.createCleanerDao();
            cleaner = (Cleaner) dao.findEntityById(cleaner.getIdUser());
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
        return cleaner;
    }
}