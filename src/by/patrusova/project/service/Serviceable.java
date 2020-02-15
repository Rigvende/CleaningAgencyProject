package by.patrusova.project.service;

import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.exception.ServiceException;
import java.sql.SQLException;

public interface Serviceable {

    AbstractEntity doService(AbstractEntity entity) throws ServiceException, SQLException;
}