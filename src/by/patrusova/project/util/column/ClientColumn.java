package by.patrusova.project.util.column;

public enum ClientColumn {

    ID_CLIENT("id_client"),
    ID_USER("id_user"),
    DISCOUNT("discount"),
    LOCATION("location"),
    RELATIVE("relative"),
    CLIENT_NOTES("client_notes"),
    NOTES("notes");

    private String value;

    ClientColumn(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}