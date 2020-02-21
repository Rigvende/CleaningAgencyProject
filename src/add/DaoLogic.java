//package by.epam.project.connection;
//

//import by.epam.project.pool.ConnectionPool;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//
//public class DaoLogic {
//    public void doLogic(int id) throws SQLException {

//        Connection conn  = ConnectionPool.getConnection();

//        conn.setAutoCommit(false);

//        AbonentDao abonentDAO = new AbonentDao(conn);
//        PaymentDao paymentDAO = new PaymentDao(conn);

//        abonentDAO.findAll();
//        paymentDAO.findEntityById(id);
//        paymentDAO.delete(id);

//        conn.commit();

//        ConnectionPool.close(conn);
//    }

//public void closePreparedStatement(PreparedStatement statement){
//        if(statement!=null){
//        try{
//        statement.close();
//        }catch(SQLException e){
//        LOGGER.log(Level.ERROR,"Prepared statement closing failed.");
//        e.printStackTrace();
//        }finally{
//        if(connection!=null){
//        ConnectionPool.releaseConnection(connection);
//        }else{
//        LOGGER.log(Level.ERROR,"Connection is null. ");
//        }
//        }
//        }
//        }
//}
//
//валидация от приема пинга в запросе
//protected static final String PING_MARKER = "/* ping */";
//...
//        if (sql.charAt(0) == '/') {
//        if (sql.startsWith(PING_MARKER)) {
//        doPingInstead();
//        ...


//private static final String SQL_SELECT_CLEANER_BY_COMMISSION =
//        "SELECT id_cleaner, id_user, commission FROM cleaners WHERE commission=?";
//public List<Cleaner> findCleanerByCommission(double commission) {
//        Cleaner cleaner = new Cleaner();
//        List<Cleaner> list = new ArrayList<>();
//        Connection connection;
//        PreparedStatement statement = null;
//        try {
//        connection = ConnectionPool.getConnection();
//        statement = connection.prepareStatement(SQL_SELECT_CLEANER_BY_COMMISSION);
//        statement.setDouble(1, commission);
//        ResultSet resultSet = statement.executeQuery();
//        while (resultSet.next()) {
//        cleaner.setCommission(commission);
//        cleaner.setId(resultSet.getLong(String.valueOf(CleanerColumns.ID_CLEANER)));
//        cleaner.setIdUser(resultSet.getLong(String.valueOf(CleanerColumns.ID_USER)));
//        list.add(cleaner);
//        }
//        } catch (SQLException | InterruptedException e) {
//        LOGGER.log(Level.ERROR, "SQL exception (request or table failed): ", e);
//        } finally {
//        closeStatement(statement);
//        returnConnectionInPool();
//        }
//        return list;
//        }


//protected void doPost(HttpServletRequest request, HttpServletResponse response)
//        throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        session.setMaxInactiveInterval(-1);
//        User user = (User) session.getAttribute("user");
//        if (session.isNew() || user == null) {
//        user = new User();
//        session.setAttribute("user", user);
//        processRequest(request, response);
//        }
//        }

// if(IdValidator.isValidId(id) && NumberValidator.isValidPhone(phone)
//         && StringValidator.isValidRole(role)
//         && StringValidator.isValidStringSize("login", login)
//         && StringValidator.isValidStringSize("password", password)
//         && StringValidator.isValidStringSize("name", name)
//         && StringValidator.isValidStringSize("lastname", lastname)
//         && StringValidator.isValidStringSize("address", address)
//         && StringValidator.isValidStringSize("email", email)) {

//if(IdValidator.isValidId(id)
//        && StringValidator.isValidStringSize("service", service)
//        && NumberValidator.isValidDecimal("cost", cost)
//        && NumberValidator.isValidDecimal("discount", discount)) {

//if (IdValidator.isValidId(id) && IdValidator.isValidId(idClient)
//        && StringValidator.isValidStatus(orderStatus)
//        && IdValidator.isValidId(idCleaner) && NumberValidator.isValidMark(mark)
//        && DateValidator.isValidOrderDate(orderTime)
//        && DateValidator.isValidDeadlineDate(deadline)) {

//     if(IdValidator.isValidId(id) && IdValidator.isValidId(idUser)
//             && NumberValidator.isValidDecimal("discount", discount)
//             && StringValidator.isValidStringSize("location", location)
//             && StringValidator.isValidStringSize("relative", relative)
//             && StringValidator.isValidStringSize("notes", notes)) {

//if (IdValidator.isValidId(id) && IdValidator.isValidId(idUser)
//        && NumberValidator.isValidDecimal("commission", commission)
//        && StringValidator.isValidStringSize("notes", notes)) {

//if (IdValidator.isValidId(id) && IdValidator.isValidId(idOrder)
//        &&IdValidator.isValidId(idService)) {


//    public static Optional<ActionCommand> defineCommand(String commandName)
//            throws CommandException {
//        Optional<ActionCommand> current;
//        if (commandName == null || commandName.isBlank()) {
//            return Optional.empty();
//        }
//        try {
//            CommandEnum type = CommandEnum.valueOf(commandName.toUpperCase());
//            current = Optional.of(type.getCurrentCommand());
//        } catch (IllegalArgumentException e) {
//            LOGGER.log(Level.ERROR, "Cannot find command.");
//            throw new CommandException(e);
//        }
//        return current;
//    }
//
//    public static Optional<ActionCommand> takeCommand(String commandName) {
//        return Arrays.stream(CommandEnum.values())
//                .filter(o -> o.name().equalsIgnoreCase(commandName))
//                .map(CommandEnum::getCurrentCommand)
//                .findAny();
//    }

//                response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
//                response.addHeader("Location", request.getContextPath() + page);

//package by.patrusova.project.command.impl.change;
//
//import by.patrusova.project.command.ActionCommand;
//import by.patrusova.project.entity.AbstractEntity;
//import by.patrusova.project.entity.Role;
//import by.patrusova.project.entity.impl.User;
//import by.patrusova.project.exception.CommandException;
//import by.patrusova.project.exception.DaoException;
//import by.patrusova.project.exception.ServiceException;
//import by.patrusova.project.service.impl.RoleService;
//import by.patrusova.project.util.ConfigurationManager;
//import by.patrusova.project.util.MessageManager;
//import by.patrusova.project.util.stringholder.Attributes;
//import by.patrusova.project.util.stringholder.Messages;
//import by.patrusova.project.util.stringholder.Pages;
//import by.patrusova.project.util.stringholder.Parameters;
//import by.patrusova.project.validator.NumberValidator;
//import org.apache.logging.log4j.Level;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//import javax.servlet.http.HttpServletRequest;
//import java.sql.SQLException;
//
//public class ChangeGuestCommand implements ActionCommand {
//
//    private final static Logger LOGGER = LogManager.getLogger();
//
//    @Override
//    public String execute(HttpServletRequest request) throws CommandException {
//        AbstractEntity entity = null;
//        String Id = request.getParameter(Parameters.ID.getValue());
//        try {
//            if (!NumberValidator.isValidUserID(Id)) {
//                request.getSession().setAttribute(Attributes.ERROR_CHANGE_GUEST.getValue(),
//                        MessageManager.getProperty(Messages.MESSAGE_ERROR_CHANGE_GUEST.getValue()));
//                return ConfigurationManager.getProperty(Pages.PAGE_GUESTLIST.getValue());
//            }
//            RoleService roleService = new RoleService();
//            long id = Long.parseLong(Id);
//            String role = request.getParameter(Attributes.ROLE.getValue());
//            for (Role role1 : Role.values()) {
//                if (role.equals(role1.getValue())) {
//                    entity = roleService.doService(id, role);
//                } else {
//                    entity = null;
//                }
//            }
//        } catch (ServiceException | DaoException | SQLException e) {
//            LOGGER.log(Level.ERROR, "Exception has occurred while changing role was processing. ", e);
//            throw new CommandException(e);
//        }
//        if (entity != null) {
//            User user = (User) entity;
//            request.getSession().setAttribute("formerguest", user);
//            return ConfigurationManager.getProperty(Pages.PAGE_MAIL.getValue());
//        } else {
//            request.getSession().setAttribute(Attributes.ERROR_CHANGE_GUEST.getValue(),
//                    MessageManager.getProperty(Messages.MESSAGE_ERROR_CHANGE_GUEST.getValue()));
//            return ConfigurationManager.getProperty(Pages.PAGE_GUESTLIST.getValue());
//        }
//    }
//}

//package by.patrusova.project.service.impl;
//
//import by.patrusova.project.connection.ProxyConnection;
//import by.patrusova.project.dao.DaoFactory;
//import by.patrusova.project.dao.impl.CleanerDao;
//import by.patrusova.project.dao.impl.ClientDao;
//import by.patrusova.project.dao.impl.UserDao;
//import by.patrusova.project.entity.AbstractEntity;
//import by.patrusova.project.entity.impl.Cleaner;
//import by.patrusova.project.entity.impl.Client;
//import by.patrusova.project.entity.impl.User;
//import by.patrusova.project.exception.DaoException;
//import by.patrusova.project.exception.ServiceException;
//import by.patrusova.project.service.Serviceable;
//import by.patrusova.project.util.MessageManager;
//import by.patrusova.project.util.stringholder.Attributes;
//import by.patrusova.project.util.stringholder.Messages;
//import org.apache.logging.log4j.Level;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//import java.sql.SQLException;
//
//public class RoleService implements Serviceable {
//
//    private final static Logger LOGGER = LogManager.getLogger();
//
//    @Override
//    public AbstractEntity doService(AbstractEntity entity) throws ServiceException {
//        return entity;
//    }
//
//    public AbstractEntity doService(long id, String role) throws ServiceException {
//        DaoFactory factory = new DaoFactory();
//        User user;
//        try {
//            UserDao userDao = factory.createUserDao();
//            user = (User) userDao.findEntityById(id);
//            if (user.getRole().equals(Attributes.GUEST.getValue())) {
//                user.setRole(role);
//                if (!userDao.update(user)) {
//                    switch (role) {
//                        case "admin":
//                            return user;
//                        case "client":
//                            ClientDao clientDao = factory.createClientDao();
//                            Client client = new Client();
//                            client.setIdUser(id);
//                            return clientDao.create(client) ? null : user;
//                        case "cleaner":
//                            CleanerDao cleanerDao = factory.createCleanerDao();
//                            Cleaner cleaner = new Cleaner();
//                            cleaner.setIdUser(id);
//                            return cleanerDao.create(cleaner) ? null : user;
//                        default:
//                            return null;
//                    }
//                }
//            } else {
//                user = null;
//            }
//        } catch (DaoException | SQLException e) {
//            LOGGER.log(Level.ERROR, "Exception while setting role has occurred. ", e);
//            throw new ServiceException(e);
//        }
//        return user;
//    }
//}
