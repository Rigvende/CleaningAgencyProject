package by.patrusova.project.service;

import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.exception.ServiceException;
import java.util.Optional;

/**
 * Basic interface for all services.
 * @autor Marianna Patrusova
 * @version 1.0
 */
public interface Serviceable {

    /**
     * Method: do some business logic for incoming command depending on entity type from the command.
     * @param entity is instance of {@link AbstractEntity} subclasses
     * @return Optional of {@link AbstractEntity} (empty or not depending on service logic)
     */
    Optional<AbstractEntity> doService(AbstractEntity entity) throws ServiceException;
}