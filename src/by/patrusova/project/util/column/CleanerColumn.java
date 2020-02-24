package by.patrusova.project.util.column;

public enum CleanerColumn {

    ID_CLEANER("id_cleaner"),
    ID_USER("id_user"),
    COMMISSION("commission"),
    CLEANER_NOTES("cleaner_notes"),
    NOTES("notes");

    private String value;

    CleanerColumn(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}