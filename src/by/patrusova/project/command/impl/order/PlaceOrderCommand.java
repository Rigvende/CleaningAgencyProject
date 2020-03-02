package by.patrusova.project.command.impl.order;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.Role;
import by.patrusova.project.entity.impl.Client;
import by.patrusova.project.entity.impl.Order;
import by.patrusova.project.entity.impl.User;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.impl.ClientInfoService;
import by.patrusova.project.service.impl.OrderInfoService;
import by.patrusova.project.service.impl.UserInfoService;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.MessageManager;
import by.patrusova.project.validator.NumberValidator;
import by.patrusova.project.validator.RegistrationDataValidator;
import by.patrusova.project.validator.StringValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.Optional;

/**
 * Class for command to check order's info and place order
 * @autor Marianna Patrusova
 * @version 1.0
 */
public class PlaceOrderCommand implements ActionCommand {

    private final static Logger LOGGER = LogManager.getLogger();
    private final static String DAYS = "days";
    private final static String RELATIVE = "relative";
    private final static String LOCATION = "location";
    private final static String USER = "user";
    private final static String LASTNAME = "lastname";
    private final static String EMAIL = "email";
    private final static String PHONE = "phone";
    private final static String FIRSTNAME = "firstname";
    private final static String ORDER_NEW = "orderNew";
    private final static String ERROR_PLACE_ORDER = "errorPlaceOrder";
    private final static String MESSAGE_ERROR_PLACE_ORDER = "message.placeordererror";
    private final static String PAGE_PLACE_ORDER = "page.placeorder";
    private final static String PAGE_ORDER_CONFIRM = "page.orderconfirm";
    private ClientInfoService clientInfoService = new ClientInfoService();
    private UserInfoService userInfoService = new UserInfoService();
    private OrderInfoService orderInfoService = new OrderInfoService();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        try {
            User user = (User) request.getSession().getAttribute(USER);
            Client client = (Client) request.getSession().getAttribute(Role.CLIENT.getValue());
            Order order = (Order)request.getSession().getAttribute(ORDER_NEW);
            String days = request.getParameter(DAYS);
            String name = request.getParameter(FIRSTNAME);
            String lastname = request.getParameter(LASTNAME);
            String phone = request.getParameter(PHONE);
            String email = request.getParameter(EMAIL);
            String location = request.getParameter(LOCATION);
            String relative = request.getParameter(RELATIVE);
            if (NumberValidator.isValidDays(days)
                    && RegistrationDataValidator.isValidName(name)
                    && RegistrationDataValidator.isValidLastname(lastname)
                    && RegistrationDataValidator.isValidPhone(phone)
                    && RegistrationDataValidator.isValidEmail(email)
                    && StringValidator.isValidStringSize(EMAIL, email)
                    && StringValidator.isValidStringSize(LOCATION, location)
                    && StringValidator.isValidStringSize(RELATIVE, relative)) {
                user.setEmail(email);
                user.setPhone(Long.parseLong(phone));
                user.setName(name);
                user.setLastname(lastname);
                Optional<AbstractEntity> optional1 = userInfoService.doService(user);
                client.setRelative(relative);
                client.setLocation(location);
                Optional<AbstractEntity> optional2 = clientInfoService.doService(client);
                if (optional1.isPresent() && optional2.isPresent()) {
                    order.setOrderStatus(Order.Status.REGISTERED.getValue());
                    order.setOrderTime(LocalDate.now());
                    order.setDeadline(LocalDate.now().plusDays(Integer.parseInt(days)));
                    Optional<AbstractEntity> optional3 = orderInfoService.placeOrder(order);
                    if (optional3.isPresent()) {
                        request.getSession().setAttribute(USER, user);
                        request.getSession().setAttribute(Role.CLIENT.getValue(), client);
                        return ConfigurationManager.getProperty(PAGE_ORDER_CONFIRM);
                    }
                }
            }
            request.getSession().setAttribute(ERROR_PLACE_ORDER,
                    MessageManager.getProperty(MESSAGE_ERROR_PLACE_ORDER));
            return ConfigurationManager.getProperty(PAGE_PLACE_ORDER);
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR,
                    "Exception has occurred while changing client info was processing. ", e);
            throw new CommandException(e);
        }
    }
}