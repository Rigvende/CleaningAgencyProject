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