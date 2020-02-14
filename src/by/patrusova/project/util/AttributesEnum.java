package by.patrusova.project.util;

public enum AttributesEnum {

    PAGE_INDEX("page.index"),
    PAGE_INFO("page.info"),
    NULLPAGE("nullpage"),
    PAGE_MAIN_ADMIN("page.mainadmin"),
    PAGE_MAIN_CLIENT("page.mainclient"),
    PAGE_MAIN_CLEANER("page.maincleaner"),
    PAGE_LOGIN("page.login"),
    PAGE_CHANGE("page.changeform"),
    PAGE_CHANGE_BURIAL("page.changeburialform"),
    PAGE_REG("page.registrationform"),
    PAGE_REG_TRUE("page.registrationtrue"),
    PAGE_PROFILE("page.profile"),
    PAGE_MAIL("page.sendmail"),

    WRONG_ACTION("wrongAction"),
    ERROR_LOGIN("errorLoginPassMessage"),
    ROLE("role"),
    ADMIN("admin"),
    CLEANER("cleaner"),
    CLIENT("client"),
    GUEST("guest"),
    USER("user"),
    ERROR_REG("errorRegistrationMessage"),
    ERROR_CHANGE("errorChangeMessage"),
    MAIL("mail"),
    NEW_USER("newuser"),
    POST("post"),

    MESSAGE_EMPTY("message.nullpage"),
    MESSAGE_ERROR_REG("message.registrationerror"),
    MESSAGE_ERROR_CHANGE("message.changeerror"),
    MESSAGE_WRONG("message.wrongaction"),
    MESSAGE_ERROR_LOGIN("message.loginerror"),
    MESSAGE_NOT_REG("message.notregistered");

    private String value;

    AttributesEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}