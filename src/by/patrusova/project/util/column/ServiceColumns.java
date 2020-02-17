package by.patrusova.project.util.column;

public enum  ServiceColumns {

    ID_SERVICE("is_service"),
    SERVICE("service"),
    COST("cost"),
    DISCOUNT("discount");

    private String value;

    ServiceColumns(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}