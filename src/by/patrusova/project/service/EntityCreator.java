package by.patrusova.project.service;

import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.exception.ServiceException;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * Auxiliary interface for part of services.
 * @autor Marianna Patrusova
 * @version 1.0
 */
public interface EntityCreator {

    /**
     * Create instance of {@link AbstractEntity} depending on request's parameters.
     * @param request is incoming request
     * @return Optional of {@link AbstractEntity} (empty or not depending on service logic)
     */
    Optional<AbstractEntity> createEntity(HttpServletRequest request) throws ServiceException;
}