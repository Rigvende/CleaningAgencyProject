package by.patrusova.project.util.column;

public enum  ServiceColumns {

    ID_SERVICE("id_service"),
    SERVICE("service"),
    COST("cost"),
    SALES("sales");

    private String value;

    ServiceColumns(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}