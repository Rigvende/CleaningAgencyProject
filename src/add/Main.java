package add;

import by.patrusova.project.dao.DaoFactory;
import by.patrusova.project.dao.impl.ClientDao;
import by.patrusova.project.dao.impl.UserDao;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.Role;
import by.patrusova.project.entity.impl.Client;
import by.patrusova.project.entity.impl.User;
import by.patrusova.project.exception.DaoException;
import by.patrusova.project.service.impl.ShowGuestsService;
import by.patrusova.project.util.stringholder.Attributes;
import by.patrusova.project.util.stringholder.Parameters;
import by.patrusova.project.validator.RegistrationDataValidator;
import by.patrusova.project.validator.StringValidator;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.sql.SQLException;
import java.util.*;

public class Main {

    public User createEntity(Map<String, Boolean> map) {
        User newUser = new User();
        if (!map.containsValue(false)) {
            newUser.setId(0);
            newUser.setLogin("ghhhhhhhh");
            newUser.setPassword("qwerty");
            newUser.setRole(String.valueOf(Role.GUEST)); //пока админ не подтвердит регистрацию
            newUser.setName("qwerty");
            newUser.setLastname("qwerty");
            newUser.setPhone(Long.parseLong("1111111111"));
            newUser.setEmail("ivanov@tut.by");
            newUser.setAddress("");
            return newUser;
        } else {
            return null;
        }
    }

    public static void main(String[] args) throws IOException, SQLException, DaoException {
//        ShowGuestsService service = new ShowGuestsService();
//        List<User> list = service.doService();
//        for (User user : list) {
//            System.out.println(user.getId() + " " + user.getName() + " " + user.getLastname());
//        }

        Map<String, Boolean> validationMap = new HashMap<>();
        String login = "ghhhhhhhh";
        String password = "qwerty";
        String passwordRepeated = "qwerty";
        String name = "qwerty";
        String lastname = "qwerty";
        String phone = "1111111111";
        String email = "ivanov@tut.by";
        String address = "";
        validationMap.put(Parameters.LOGINREG.getValue(),
                RegistrationDataValidator.isValidLogin(login));
        validationMap.put(Parameters.PASSWORDREG.getValue(),
                (RegistrationDataValidator.isValidPassword(password)
                        && RegistrationDataValidator.isPasswordRepeated(password, passwordRepeated)));
        validationMap.put(Parameters.FIRSTNAME.getValue(),
                StringValidator.isValidStringSize(Parameters.NAME.getValue(), name));
        validationMap.put(Parameters.LASTNAME.getValue(),
                StringValidator.isValidStringSize(Parameters.LASTNAME.getValue(), lastname));
        validationMap.put(Parameters.PHONE.getValue(),
                RegistrationDataValidator.isValidPhone(phone));
        validationMap.put(Parameters.EMAIL.getValue(),
                RegistrationDataValidator.isValidEmail(email)
                        && StringValidator.isValidStringSize(Parameters.EMAIL.getValue(), email));
        validationMap.put(Parameters.ADDRESS.getValue(),
                StringValidator.isValidStringSize(Parameters.ADDRESS.getValue(), address));
        System.out.println(new Main().createEntity(validationMap));


//        System.out.println(Locale.getDefault());
//        Locale locale = new Locale("en", "EN");
//        Locale.setDefault(locale);
//        System.out.println(Locale.getDefault());
//        String s = "Hello";
//        System.out.println(s.substring(3));


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