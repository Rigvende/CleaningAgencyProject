package by.patrusova.project.service;

import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.exception.ServiceException;
import java.sql.SQLException;
import java.util.Optional;

public interface Serviceable {

    Optional<AbstractEntity> doService(AbstractEntity entity) throws ServiceException, SQLException;
}