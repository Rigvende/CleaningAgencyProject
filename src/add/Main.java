package add;

import by.patrusova.project.dao.DaoFactory;
import by.patrusova.project.dao.impl.OrderDao;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.impl.BasketPosition;
import by.patrusova.project.entity.impl.Order;
import by.patrusova.project.entity.impl.Service;
import by.patrusova.project.exception.DaoException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.impl.*;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class Main {

//    public User createEntity(Map<String, Boolean> map) {
//        User newUser = new User();
//        if (!map.containsValue(false)) {
//            newUser.setId(0);
//            newUser.setLogin("ghhhhhhhh");
//            newUser.setPassword("qwerty");
//            newUser.setRole(String.valueOf(Role.GUEST)); //пока админ не подтвердит регистрацию
//            newUser.setName("qwerty");
//            newUser.setLastname("qwerty");
//            newUser.setPhone(Long.parseLong("1111111111"));
//            newUser.setEmail("ivanov@tut.by");
//            newUser.setAddress("");
//            return newUser;
//        } else {
//            return null;
//        }
//    }

    public static void main(String[] args) throws IOException, SQLException, DaoException, ServiceException {


//        Order order = new Order();
//        order.setIdClient(2);
//        order.setOrderStatus("new");
//        order.setId(20);
//        DeleteEntityService service = new DeleteEntityService();
//        System.out.println(service.doService(order));

//        DaoFactory factory = new DaoFactory();
//        OrderDao dao = factory.createOrderDao();
//        Order order = new Order();
//        order.setIdClient(2);
//        order.setOrderStatus("new");
//        System.out.println(  dao.findNew(order));


       BasketService service = new BasketService();
        BasketPosition position = new BasketPosition();
        position.setIdOrder(22);
        position.setIdService(70);
        System.out.println(service.doService(position));
//        BasketPosition position2 = new BasketPosition();
//        position2.setIdService(85);
//        System.out.println(service.doService(position2));


//        DaoFactory factory = new DaoFactory();
//        OrderDao dao = factory.createOrderDao();
//        Order order = new Order();
//        order.setOrderStatus("new");
//        order.setIdClient(2);
//        System.out.println(dao.createNew(order));


//ClientInfoService service = new ClientInfoService();
//        System.out.println(service.doService(7, 3, "ok"));



//        CancelOrderService service = new CancelOrderService();
//        Order order1 = new Order();
//        order1.setId(6);
//        order1.setIdClient(8);
//        System.out.println(service.doService(order1));
//        Order order2 = new Order();
//        order2.setId(6);
//        order2.setIdClient(8);
//        System.out.println(service.doService(order2));
//        Order order3 = new Order();
//        order3.setId(7);
//        order3.setIdClient(8);
//        System.out.println(service.doService(order3));
//        Order order4 = new Order();
//        order4.setId(78);
//        order4.setIdClient(8);
//        System.out.println(service.doService(order4));


//        Client client = new Client();
//        client.setIdUser(86);
//        ClientInfoService service = new ClientInfoService();
//        System.out.println(service.getClient(client));

//        DeleteEntityService service = new DeleteEntityService();
//        User user = new User();
//        user.setId(68);
//        user.setRole("admin");
//        System.out.println(service.doService(user) == null);
//        User user2 = new User();
//        user2.setId(80);
//        user2.setRole("admin");
//        System.out.println(service.doService(user2) == null);
//        User user3 = new User();
//        user3.setId(83);
//        user3.setRole("admin");
//        System.out.println(service.doService(user3) == null);

//        DeleteEntityService service = new DeleteEntityService();
//        Cleaner cleaner = new Cleaner();
//        cleaner.setIdUser(52);
//        System.out.println(service.doService(cleaner) == null);
//        Cleaner cleaner2 = new Cleaner();
//        cleaner2.setIdUser(150);
//        System.out.println(service.doService(cleaner2) == null);
//        Cleaner cleaner3 = new Cleaner();
//        cleaner3.setIdUser(59);
//        System.out.println(service.doService(cleaner3) == null);

//        System.out.println(StringValidator.isValidRole("admin"));

//        RoleService service = new RoleService();
//        System.out.println(service.doService(74, "client"));


//        DaoFactory factory = new DaoFactory();
//        UserDao dao = factory.createUserDao();
//        boolean check = false;
//        if(NumberValidator.isValidID("") ) {
//            check = dao.findId(Long.parseLong(""));
//        }
//        System.out.println(check);



//        List<AbstractEntity> users = new ArrayList<>();
//        PreparedStatement preparedStatement = null;
//        ProxyConnection conn = ProxyConnection.createProxyConnection();
//        preparedStatement = conn.prepareStatement
//                (Statement.SQL_FIND_CLEANERS_BY_ROLE.getValue());
//        preparedStatement.setString(1, "cleaner");
//        ResultSet resultSet1 = preparedStatement.executeQuery();
//        while (resultSet1.next()) {
//            User user = new User(resultSet1.getLong(UserColumn.ID_USER.getValue()),
//                    resultSet1.getString(UserColumn.LOGIN.getValue()),
//                    resultSet1.getString(UserColumn.PASSWORD.getValue()),
//                    resultSet1.getString(UserColumn.ROLE.getValue()),
//                    resultSet1.getString(UserColumn.NAME.getValue()),
//                    resultSet1.getString(UserColumn.LASTNAME.getValue()),
//                    resultSet1.getLong(UserColumn.PHONE.getValue()),
//                    resultSet1.getString(UserColumn.ADDRESS.getValue()),
//                    resultSet1.getString(UserColumn.EMAIL.getValue()),
//                    new Cleaner(resultSet1.getLong(CleanerColumn.ID_CLEANER.getValue()),
//                            resultSet1.getBigDecimal(CleanerColumn.COMMISSION.getValue()),
//                            resultSet1.getString(CleanerColumn.NOTES.getValue()),
//                            resultSet1.getLong(CleanerColumn.ID_USER.getValue())));
//            users.add(user);
//        }
//        System.out.println(users);


//        Map<String, Boolean> validationMap = new HashMap<>();
//        String id = "0";
//        String status = "done";
//        validationMap.put(Parameter.ID_CLEANER.getValue(), NumberValidator.isValidExistedID(id));
//        validationMap.put(Parameter.STATUS.getValue(), StringValidator.isValidStatus(status));
//        System.out.println(validationMap);


//        DaoFactory factory = new DaoFactory();
//        CleanerDao dao = factory.createCleanerDao();
//        System.out.println(dao.findId(0));
//        System.out.println(NumberValidator.isValidExistedID("0"));

//        Map<String, Boolean> validationMap = new HashMap<>();
//        double discount = 0.05;
//        String notes = "";
//        validationMap.put(Parameter.DISCOUNT.getValue(),
//                NumberValidator.isValidDecimal(Parameter.DISCOUNT.getValue(), discount));
//        validationMap.put(Parameter.NOTES.getValue(),
//                StringValidator.isValidStringSize(Parameter.NOTES.getValue(), notes));
//        System.out.println(validationMap);
//
//        CleanerInfoService cleanerInfoService = new CleanerInfoService();
//        Cleaner cleaner = new Cleaner();
//        cleaner.setIdUser(50);
//        cleaner = cleanerInfoService.getCleaner(cleaner);
//        System.out.println(cleaner);
//        DaoFactory factory = new DaoFactory();
//        cleaner.setCommission(BigDecimal.valueOf(Double.parseDouble("0.05")));
//        cleaner.setNotes("");
//            CleanerDao dao = factory.createCleanerDao();
//            if (dao.update(cleaner)) {
//                cleaner = null;
//            }
//            System.out.println(cleaner);

//        DaoFactory factory = new DaoFactory();
//
//        UserDao userDao = factory.createUserDao();
//        User user = (User) userDao.findEntityById(32);
//        user.setRole("client");
//        System.out.println(user);
//        System.out.println(userDao.update(user));

//        ShowGuestsService service = new ShowGuestsService();
//        List<User> list = service.doService();
//        for (User user : list) {
//            System.out.println(user.getId() + " " + user.getName() + " " + user.getLastname());
//        }

//        Map<String, Boolean> validationMap = new HashMap<>();
//        String login = "ghhhhhhhh";
//        String password = "qwerty";
//        String passwordRepeated = "qwerty";
//        String name = "qwerty";
//        String lastname = "qwerty";
//        String phone = "1111111111";
//        String email = "ivanov@tut.by";
//        String address = "";
//        validationMap.put(Parameter.LOGINREG.getValue(),
//                RegistrationDataValidator.isValidLogin(login));
//        validationMap.put(Parameter.PASSWORDREG.getValue(),
//                (RegistrationDataValidator.isValidPassword(password)
//                        && RegistrationDataValidator.isPasswordRepeated(password, passwordRepeated)));
//        validationMap.put(Parameter.FIRSTNAME.getValue(),
//                StringValidator.isValidStringSize(Parameter.NAME.getValue(), name));
//        validationMap.put(Parameter.LASTNAME.getValue(),
//                StringValidator.isValidStringSize(Parameter.LASTNAME.getValue(), lastname));
//        validationMap.put(Parameter.PHONE.getValue(),
//                RegistrationDataValidator.isValidPhone(phone));
//        validationMap.put(Parameter.EMAIL.getValue(),
//                RegistrationDataValidator.isValidEmail(email)
//                        && StringValidator.isValidStringSize(Parameter.EMAIL.getValue(), email));
//        validationMap.put(Parameter.ADDRESS.getValue(),
//                StringValidator.isValidStringSize(Parameter.ADDRESS.getValue(), address));
//        System.out.println(new Main().createEntity(validationMap));
//
//List<User> list = new ArrayList<>();
//for (User user : list){
//    System.out.println(user);
//}


//        System.out.println(Locale.getDefault());
//        Locale locale = new Locale("en", "EN");
//        Locale.setDefault(locale);
//        System.out.println(locale.toString());
//        System.out.println(locale.toString());
//        System.out.println(locale.toString());


//        File f = new File("src/resources/connectionDB.properties");
//        System.out.println(f.getAbsolutePath());
//        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
//        try (ProxyConnection conn = ProxyConnection.createProxyConnection()) {
//            UserDao dao = new UserDao(conn);
//            User user = new User();
//            user.setLogin("123");
//            user.setPassword("123");
//            user = LoginService.checkLogin(user);
//            System.out.println(user);
//            LogoutCommand command = new LogoutCommand();
//            HttpServletRequest request = new HttpServletRequest() {
//                @Override
//                public String getAuthType() {
//                    return null;
//                }
//
//                @Override
//                public Cookie[] getCookies() {
//                    return new Cookie[0];
//                }
//
//                @Override
//                public long getDateHeader(String s) {
//                    return 0;
//                }
//
//                @Override
//                public String getHeader(String s) {
//                    return null;
//                }
//
//                @Override
//                public Enumeration<String> getHeaders(String s) {
//                    return null;
//                }
//
//                @Override
//                public Enumeration<String> getHeaderNames() {
//                    return null;
//                }
//
//                @Override
//                public int getIntHeader(String s) {
//                    return 0;
//                }
//
//                @Override
//                public String getMethod() {
//                    return null;
//                }
//
//                @Override
//                public String getPathInfo() {
//                    return null;
//                }
//
//                @Override
//                public String getPathTranslated() {
//                    return null;
//                }
//
//                @Override
//                public String getContextPath() {
//                    return null;
//                }
//
//                @Override
//                public String getQueryString() {
//                    return null;
//                }
//
//                @Override
//                public String getRemoteUser() {
//                    return null;
//                }
//
//                @Override
//                public boolean isUserInRole(String s) {
//                    return false;
//                }
//
//                @Override
//                public Principal getUserPrincipal() {
//                    return null;
//                }
//
//                @Override
//                public String getRequestedSessionId() {
//                    return null;
//                }
//
//                @Override
//                public String getRequestURI() {
//                    return null;
//                }
//
//                @Override
//                public StringBuffer getRequestURL() {
//                    return null;
//                }
//
//                @Override
//                public String getServletPath() {
//                    return null;
//                }
//
//                @Override
//                public HttpSession getSession(boolean b) {
//                    return null;
//                }
//
//                @Override
//                public HttpSession getSession() {
//                    return null;
//                }
//
//                @Override
//                public String changeSessionId() {
//                    return null;
//                }
//
//                @Override
//                public boolean isRequestedSessionIdValid() {
//                    return false;
//                }
//
//                @Override
//                public boolean isRequestedSessionIdFromCookie() {
//                    return false;
//                }
//
//                @Override
//                public boolean isRequestedSessionIdFromURL() {
//                    return false;
//                }
//
//                @Override
//                public boolean isRequestedSessionIdFromUrl() {
//                    return false;
//                }
//
//                @Override
//                public boolean authenticate(HttpServletResponse httpServletResponse) throws IOException, ServletException {
//                    return false;
//                }
//
//                @Override
//                public void login(String s, String s1) throws ServletException {
//
//                }
//
//                @Override
//                public void logout() throws ServletException {
//
//                }
//
//                @Override
//                public Collection<Part> getParts() throws IOException, ServletException {
//                    return null;
//                }
//
//                @Override
//                public Part getPart(String s) throws IOException, ServletException {
//                    return null;
//                }
//
//                @Override
//                public <T extends HttpUpgradeHandler> T upgrade(Class<T> aClass) throws IOException, ServletException {
//                    return null;
//                }
//
//                @Override
//                public Object getAttribute(String s) {
//                    return null;
//                }
//
//                @Override
//                public Enumeration<String> getAttributeNames() {
//                    return null;
//                }
//
//                @Override
//                public String getCharacterEncoding() {
//                    return null;
//                }
//
//                @Override
//                public void setCharacterEncoding(String s) throws UnsupportedEncodingException {
//
//                }
//
//                @Override
//                public int getContentLength() {
//                    return 0;
//                }
//
//                @Override
//                public long getContentLengthLong() {
//                    return 0;
//                }
//
//                @Override
//                public String getContentType() {
//                    return null;
//                }
//
//                @Override
//                public ServletInputStream getInputStream() throws IOException {
//                    return null;
//                }
//
//                @Override
//                public String getParameter(String s) {
//                    return null;
//                }
//
//                @Override
//                public Enumeration<String> getParameterNames() {
//                    return null;
//                }
//
//                @Override
//                public String[] getParameterValues(String s) {
//                    return new String[0];
//                }
//
//                @Override
//                public Map<String, String[]> getParameterMap() {
//                    return null;
//                }
//
//                @Override
//                public String getProtocol() {
//                    return null;
//                }
//
//                @Override
//                public String getScheme() {
//                    return null;
//                }
//
//                @Override
//                public String getServerName() {
//                    return null;
//                }
//
//                @Override
//                public int getServerPort() {
//                    return 0;
//                }
//
//                @Override
//                public BufferedReader getReader() throws IOException {
//                    return null;
//                }
//
//                @Override
//                public String getRemoteAddr() {
//                    return null;
//                }
//
//                @Override
//                public String getRemoteHost() {
//                    return null;
//                }
//
//                @Override
//                public void setAttribute(String s, Object o) {
//
//                }
//
//                @Override
//                public void removeAttribute(String s) {
//
//                }
//
//                @Override
//                public Locale getLocale() {
//                    return null;
//                }
//
//                @Override
//                public Enumeration<Locale> getLocales() {
//                    return null;
//                }
//
//                @Override
//                public boolean isSecure() {
//                    return false;
//                }
//
//                @Override
//                public RequestDispatcher getRequestDispatcher(String s) {
//                    return null;
//                }
//
//                @Override
//                public String getRealPath(String s) {
//                    return null;
//                }
//
//                @Override
//                public int getRemotePort() {
//                    return 0;
//                }
//
//                @Override
//                public String getLocalName() {
//                    return null;
//                }
//
//                @Override
//                public String getLocalAddr() {
//                    return null;
//                }
//
//                @Override
//                public int getLocalPort() {
//                    return 0;
//                }
//
//                @Override
//                public ServletContext getServletContext() {
//                    return null;
//                }
//
//                @Override
//                public AsyncContext startAsync() throws IllegalStateException {
//                    return null;
//                }
//
//                @Override
//                public AsyncContext startAsync(ServletRequest servletRequest, ServletResponse servletResponse) throws IllegalStateException {
//                    return null;
//                }
//
//                @Override
//                public boolean isAsyncStarted() {
//                    return false;
//                }
//
//                @Override
//                public boolean isAsyncSupported() {
//                    return false;
//                }
//
//                @Override
//                public AsyncContext getAsyncContext() {
//                    return null;
//                }
//
//                @Override
//                public DispatcherType getDispatcherType() {
//                    return null;
//                }
//            };
//            String page = command.execute(request);
//            System.out.println(page);
//            List<AbstractEntity> list = dao.findAll();
//            for (AbstractEntity entity : list) {
//                System.out.println(entity);
//            }
//        } catch (Exception ex) {
//            System.out.println("Connection failed...");
//        }
    }
}

