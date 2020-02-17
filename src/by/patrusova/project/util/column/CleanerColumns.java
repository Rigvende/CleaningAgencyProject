package by.patrusova.project.util.column;

public enum CleanerColumns {

    ID_CLEANER("id_cleaner"),
    ID_USER("id_user"),
    COMMISSION("commission"),
    NOTES("notes");

    private String value;

    CleanerColumns(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}