package by.patrusova.project.exception;

import by.patrusova.project.dao.DaoFactory;
import by.patrusova.project.dao.impl.ClientDao;
import by.patrusova.project.entity.impl.Client;
import org.testng.annotations.Test;

public class DaoExceptionTest {

    @Test(expectedExceptions = DaoException.class)
    public void TestDaoException() throws DaoException {
        Client client = new Client();
        client.setIdUser(1);
        ClientDao dao = DaoFactory.createClientDao();
        dao.create(client);
    }
}