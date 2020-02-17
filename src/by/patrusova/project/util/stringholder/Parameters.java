package by.patrusova.project.util.stringholder;

public enum Parameters {

    COMMAND("command"),
    EMPTY(""),
    LOGIN("login"),
    PASSWORD("password"),
    LOGINREG("loginreg"),
    PASSWORDREG("passwordreg"),
    PASSWORD_AGAIN("passwordagain"),
    NAME("name"),
    FIRSTNAME("firstname"),
    LASTNAME("lastname"),
    PHONE("phone"),
    ADDRESS("address"),
    EMAIL("email"),
    TO("to"),
    SUBJECT("subject"),
    BODY("body"),
    UTF_8("UTF-8"),
    POST("post"),
    ENTITITYPE("entitytype"),
    ID("id"),
    LOCATION("location"),
    RELATIVE("relative");

    private String value;

    Parameters(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}