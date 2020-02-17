package by.patrusova.project.util.column;

public enum UserColumns {

    ID_USER("id_user"),
    LOGIN("login"),
    PASSWORD("password"),
    ROLE("role"),
    NAME("name"),
    LASTNAME("lastname"),
    PHONE("phone"),
    ADDRESS("address"),
    EMAIL("email");

    private String value;

    UserColumns(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}