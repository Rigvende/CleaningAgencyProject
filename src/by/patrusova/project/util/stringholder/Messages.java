package by.patrusova.project.util.stringholder;

public enum Messages {

    MESSAGE_EMPTY("message.nullpage"),
    MESSAGE_ERROR_REG("message.registrationerror"),
    MESSAGE_ERROR_CHANGE("message.changeerror"),
    MESSAGE_WRONG("message.wrongaction"),
    MESSAGE_ERROR_LOGIN("message.loginerror"),
    MESSAGE_NOT_REG("message.notregistered");

    private String value;

    Messages(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}