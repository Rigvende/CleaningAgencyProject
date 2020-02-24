package by.patrusova.project.util.column;

public enum UserColumn {

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

    UserColumn(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}