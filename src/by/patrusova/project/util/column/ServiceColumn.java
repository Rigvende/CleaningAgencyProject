package by.patrusova.project.util.column;

public enum ServiceColumn {

    ID_SERVICE("id_service"),
    SERVICE("service"),
    COST("cost"),
    SALES("sales");

    private String value;

    ServiceColumn(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}