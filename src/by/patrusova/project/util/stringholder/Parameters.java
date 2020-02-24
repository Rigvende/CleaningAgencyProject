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
    DISCOUNT("discount"),
    STATUS("status"),
    TO("to"),
    SUBJECT("subject"),
    BODY("body"),
    UTF_8("UTF-8"),
    POST("post"),
    ENTITITYPE("entitytype"),
    ID("id"),
    COMMISSION("commission"),
    NOTES("notes"),
    COST("cost"),
    ID_CLEANER("id_cleaner"),
    ID_CLIENT("id_client"),
    ID_SERVICE("id_service"),
    MARK("mark"),
    SERVICE("service"),
    SERVICECHANGE("servicechange"),
    POSITION("position"),
    CHOICE("choice"),
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