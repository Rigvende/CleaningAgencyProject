package by.patrusova.project.dao.column;

public enum  ClientColumns {

    ID_CLIENT("id_client"),
    ID_USER("id_user"),
    DISCOUNT("discount"),
    LOCATION("location"),
    RELATIVE("relative"),
    NOTES("notes");

    private String value;

    ClientColumns(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}