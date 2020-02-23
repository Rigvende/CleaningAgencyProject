package by.patrusova.project.service;

import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.exception.ServiceException;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public interface EntityCreator {

    Optional<AbstractEntity> createEntity(HttpServletRequest request) throws ServiceException;
}