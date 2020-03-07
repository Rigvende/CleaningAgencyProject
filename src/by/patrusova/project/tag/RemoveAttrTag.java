package by.patrusova.project.tag;

import javax.servlet.jsp.tagext.TagSupport;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for customer tag which helps to remove useless attributes from session.
 * @autor Marianna Patrusova
 * @version 1.0
 */
@SuppressWarnings("serial")
public class RemoveAttrTag extends TagSupport {

    private final static String EMPTY_LIST = "emptyList";
    private final static String ERROR_LOGIN = "errorLoginPassMessage";
    private final static String ERROR_REG = "errorRegistrationMessage";
    private final static String ERROR_ADD_SERVICE = "errorAddServiceMessage";
    private final static String ERROR_CHANGE_CLIENT_ID = "errorChangeClientIdMessage";
    private final static String ERROR_CHANGE_CLEANER_ID = "errorChangeCleanerIdMessage";
    private final static String ERROR_CHANGE_GUEST_ID = "errorChangeGuestIdMessage";
    private final static String ERROR_CHANGE_SERVICE_ID = "errorChangeServiceIdMessage";
    private final static String ERROR_CHANGE_ORDER_ID = "errorChangeOrderIdMessage";
    private final static String ERROR_CHANGE_CLIENT = "errorChangeClientMessage";
    private final static String ERROR_CHANGE_CLEANER = "errorChangeCleanerMessage";
    private final static String ERROR_CHANGE_BURIAL = "errorChangeBurialMessage";
    private final static String ERROR_CHANGE_GUEST = "errorChangeGuestMessage";
    private final static String ERROR_CHANGE_ORDER = "errorChangeOrderMessage";
    private final static String ERROR_CHANGE_SERVICE = "errorChangeServiceMessage";
    private final static String ERROR_CHANGE_USER = "errorChangeUserMessage";
    private final static String ERROR_MAIL = "errorMail";
    private final static String ERROR_SELECT= "errorSelect";
    private final static String ERROR_PLACE_ORDER = "errorPlaceOrder";
    private final static String ORDER_DONE = "orderDone";
    private final static String FORMER_GUEST = "formerguest";

    @Override
    public int doStartTag() {
        List<String> list = new ArrayList<>();
        list.add(EMPTY_LIST);
        list.add(ERROR_LOGIN);
        list.add(ERROR_REG);
        list.add(ERROR_ADD_SERVICE);
        list.add(ERROR_CHANGE_CLIENT_ID);
        list.add(ERROR_CHANGE_CLEANER_ID);
        list.add(ERROR_CHANGE_GUEST_ID);
        list.add(ERROR_CHANGE_SERVICE_ID);
        list.add(ERROR_CHANGE_ORDER_ID);
        list.add(ERROR_CHANGE_CLIENT);
        list.add(ERROR_CHANGE_CLEANER);
        list.add(ERROR_CHANGE_BURIAL);
        list.add(ERROR_CHANGE_GUEST);
        list.add(ERROR_CHANGE_ORDER);
        list.add(ERROR_CHANGE_SERVICE);
        list.add(ERROR_CHANGE_USER);
        list.add(ERROR_MAIL);
        list.add(ERROR_SELECT);
        list.add(ERROR_PLACE_ORDER);
        list.add(FORMER_GUEST);
        list.add(ORDER_DONE);
        list.stream()
                .filter(x -> (pageContext.getSession().getAttribute(x) != null))
                .forEach(pageContext.getSession()::removeAttribute);
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() {
        return EVAL_PAGE;
    }
}