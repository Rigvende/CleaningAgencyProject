package by.patrusova.project.util.stringholder;

public enum Attribute {

    NULLPAGE("nullpage"),
    EMPTY_LIST("emptyList"),
    WRONG_ACTION("wrongAction"),
    ERROR_LOGIN("errorLoginPassMessage"),
    ROLE("role"),
    ADMIN("admin"),
    CLEANER("cleaner"),
    CLIENT("client"),
    GUEST("guest"),
    USER("user"),
    ORDER("order"),
    ID("id"),
    EN1("EN"),
    EN2("en"),
    RU1("RU"),
    RU2("ru"),
    EN_EN("en_EN"),
    RU_RU("ru_RU"),
    CATALOGUE("catalogue"),
    SERVICE("service"),
    BASKET("basket"),
    ERROR_REG("errorRegistrationMessage"),
    ERROR_ADD_SERVICE("errorAddServiceMessage"),
    ERROR_CHANGE_CLIENT_ID("errorChangeClientIdMessage"),
    ERROR_CHANGE_CLEANER_ID("errorChangeCleanerIdMessage"),
    ERROR_CHANGE_GUEST_ID("errorChangeGuestIdMessage"),
    ERROR_CHANGE_SERVICE_ID("errorChangeServiceIdMessage"),
    ERROR_CHANGE_ORDER_ID("errorChangeOrderIdMessage"),
    ERROR_CHANGE_CLIENT("errorChangeClientMessage"),
    ERROR_CHANGE_CLEANER("errorChangeCleanerMessage"),
    ERROR_CHANGE_BURIAL("errorChangeBurialMessage"),
    ERROR_CHANGE_GUEST("errorChangeGuestMessage"),
    ERROR_CHANGE_ORDER("errorChangeOrderMessage"),
    ERROR_CHANGE_SERVICE("errorChangeServiceMessage"),
    ERROR_CHANGE_USER("errorChangeUserMessage"),
    ERROR_MAIL("errorMail"),
    NEW_USER("newuser"),
    GUEST_LIST("guestList"),
    ADMIN_LIST("adminList"),
    CLIENT_LIST("clientList"),
    CLEANER_LIST("cleanerList"),
    ORDER_LIST("orderList"),
    LOCALE("locale"),
    ERROR_SELECT("errorSelect"),
    FORMERGUEST("formerguest"),
    CATALOGUE_LIST("catalogueList");

    private String value;

    Attribute(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}