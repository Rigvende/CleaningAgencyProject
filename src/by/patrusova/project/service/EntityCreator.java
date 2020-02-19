package by.patrusova.project.service;

import by.patrusova.project.dao.AbstractDao;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.exception.ServiceException;
import javax.servlet.http.HttpServletRequest;

public interface EntityCreator {

    AbstractEntity createEntity(HttpServletRequest request) throws ServiceException;
}