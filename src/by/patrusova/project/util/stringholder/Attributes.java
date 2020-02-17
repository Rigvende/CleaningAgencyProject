package by.patrusova.project.util.stringholder;

public enum Attributes {

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
    ERROR_REG("errorRegistrationMessage"),
    ERROR_CHANGE("errorChangeMessage"),
    ERROR_MAIL("errorMail"),
    ERROR_DELETE("errorDelete"),
    NEW_USER("newuser"),
    GUEST_LIST("guestList"),
    ADMIN_LIST("adminList"),
    CLIENT_LIST("clientList"),
    CLEANER_LIST("cleanerList"),
    ORDER_LIST("orderList"),
    LOCALE("locale"),
    CATALOGUE_LIST("catalogueList");

    private String value;

    Attributes(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}