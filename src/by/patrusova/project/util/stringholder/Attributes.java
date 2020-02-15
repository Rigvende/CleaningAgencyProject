package by.patrusova.project.util.stringholder;

public enum Attributes {

    NULLPAGE("nullpage"),
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
    NEW_USER("newuser");

    private String value;

    Attributes(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}