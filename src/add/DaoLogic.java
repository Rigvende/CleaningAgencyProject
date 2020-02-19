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

//    public static ConcurrentHashMap<String, PreparedStatement>
//                                    useStatements(Connection connection) throws DaoException {
//        ConcurrentHashMap<String, PreparedStatement> statements = new ConcurrentHashMap<>();
//        try {
//            PreparedStatement statement1 = connection.prepareStatement(SQL_TOTAL_COST);
//            PreparedStatement statement2 = connection.prepareStatement(SQL_SHOW_CLEANER);
//            PreparedStatement statement3 = connection.prepareStatement(SQL_SHOW_CLIENT);
//            PreparedStatement statement4 = connection.prepareStatement(SQL_SELECT_USER_BY_LOGIN_PASS);
//            PreparedStatement statement5 = connection.prepareStatement(SQL_SELECT_USER_BY_ID);
//            PreparedStatement statement6 = connection.prepareStatement(SQL_ADD_USER);
//            PreparedStatement statement7 = connection.prepareStatement(SQL_DELETE_USER_BY_LOGIN);
//            PreparedStatement statement8 = connection.prepareStatement(SQL_UPDATE_ROLE_BY_ADMIN);
//            PreparedStatement statement9 = connection.prepareStatement(SQL_CREATE_CLIENT_BY_ADMIN);
//            PreparedStatement statement10= connection.prepareStatement(SQL_CREATE_CLEANER_BY_ADMIN);
//            PreparedStatement statement11 = connection.prepareStatement(SQL_UPDATE_USER);
//            PreparedStatement statement12 = connection.prepareStatement(SQL_UPDATE_CLIENT_BY_USER);
//            PreparedStatement statement13 = connection.prepareStatement(SQL_UPDATE_CLEANER_BY_ADMIN);
//            PreparedStatement statement14 = connection.prepareStatement(SQL_UPDATE_CLIENT_BY_ADMIN);
//            PreparedStatement statement15 = connection.prepareStatement(SQL_DELETE_CLEANER);
//            PreparedStatement statement16 = connection.prepareStatement(SQL_DELETE_CLIENT);
//            PreparedStatement statement17 = connection.prepareStatement(SQL_SELECT_CLEANER_BY_ID);
//            PreparedStatement statement18 = connection.prepareStatement(SQL_SELECT_CLIENT_BY_ID);
//            PreparedStatement statement19 = connection.prepareStatement(SQL_SELECT_BASKET_POSITION_BY_ID);
//            PreparedStatement statement20 = connection.prepareStatement(SQL_ADD_POSITION);
//            PreparedStatement statement21 = connection.prepareStatement(SQL_DELETE_POSITION);
//            PreparedStatement statement22 = connection.prepareStatement(SQL_SELECT_SERVICE_BY_ID);
//            PreparedStatement statement23 = connection.prepareStatement(SQL_ADD_SERVICE);
//            PreparedStatement statement24 = connection.prepareStatement(SQL_DELETE_SERVICE);
//            PreparedStatement statement25 = connection.prepareStatement(SQL_UPDATE_SERVICE);
//            PreparedStatement statement26 = connection.prepareStatement(SQL_SELECT_ORDER_BY_ID);
//            PreparedStatement statement27 = connection.prepareStatement(SQL_ADD_ORDER);
//            PreparedStatement statement28 = connection.prepareStatement(SQL_DELETE_USER);
//            PreparedStatement statement29 = connection.prepareStatement(SQL_UPDATE_ORDER);
//            PreparedStatement statement30 = connection.prepareStatement(SQL_SET_MARK);
//            PreparedStatement statement31 = connection.prepareStatement(SQL_FIND_USERS_BY_ROLE);
//            PreparedStatement statement32 = connection.prepareStatement(SQL_FIND_CLEANERS_BY_ROLE);
//            PreparedStatement statement33 = connection.prepareStatement(SQL_FIND_CLIENTS_BY_ROLE);
//            statements.put("total_cost", statement1);
//            statements.put("show_cleaner", statement2);
//            statements.put("show_client", statement3);
//            statements.put("check_login", statement4);
//            statements.put("find_user", statement5);
//            statements.put("add_user", statement6);
//            statements.put("delete_user", statement7);
//            statements.put("update_role", statement8);
//            statements.put("create_client", statement9);
//            statements.put("create_cleaner", statement10);
//            statements.put("update_user", statement11);
//            statements.put("update_client_user", statement12);
//            statements.put("update_cleaner_admin", statement13);
//            statements.put("update_client_admin", statement14);
//            statements.put("delete_cleaner", statement15);
//            statements.put("delete_client", statement16);
//            statements.put("find_cleaner", statement17);
//            statements.put("find_client", statement18);
//            statements.put("select_position", statement19);
//            statements.put("add_position", statement20);
//            statements.put("delete_position", statement21);
//            statements.put("select_service", statement22);
//            statements.put("add_service", statement23);
//            statements.put("delete_service", statement24);
//            statements.put("update_service", statement25);
//            statements.put("find_order", statement26);
//            statements.put("add_order", statement27);
//            statements.put("delete_user_id", statement28);
//            statements.put("update_order", statement29);
//            statements.put("set_mark", statement30);
//            statements.put("find_role", statement31);
//            statements.put("find_role_cleaner", statement32);
//            statements.put("find_role_client", statement33);
//        } catch (SQLException e) {
//            throw new DaoException(e);
//        }
//        return statements;
//    }