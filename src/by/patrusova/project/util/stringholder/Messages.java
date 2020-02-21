package by.patrusova.project.util.stringholder;

public enum Messages {

    MESSAGE_EMPTY("message.nullpage"),
    MESSAGE_ERROR_REG("message.registrationerror"),
    MESSAGE_ERROR_ADD_SERVICE("message.adderror"),
    MESSAGE_ERROR_CHANGE_CLIENT("message.changeerror"),
    MESSAGE_ERROR_CHANGE_CLEANER("message.changeerror"),
    MESSAGE_ERROR_CHANGE_GUEST("message.changeerror"),
    MESSAGE_ERROR_CHANGE_SERVICE("message.changeerror"),
    MESSAGE_ERROR_CHANGE_ORDER("message.changeerror"),
    MESSAGE_ERROR_CHANGE_USER("message.changeerror"),
    MESSAGE_ERROR_CHANGE_CLIENT_ID("message.changeerrorid"),
    MESSAGE_ERROR_CHANGE_CLEANER_ID("message.changeerrorid"),
    MESSAGE_ERROR_CHANGE_SERVICE_ID("message.changeerrorid"),
    MESSAGE_ERROR_CHANGE_ORDER_ID("message.changeerrorid"),
    MESSAGE_ERROR_CHANGE_BURIAL("message.changeerror"),
    MESSAGE_WRONG("message.wrongaction"),
    MESSAGE_ERROR_LOGIN("message.loginerror"),
    MESSAGE_ERROR_LIST("message.listerror"),
    MESSAGE_ERROR_MAIL("message.mailerror"),
    MESSAGE_NOT_REG("message.notregistered");

    private String value;

    Messages(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}