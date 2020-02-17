package by.patrusova.project.util.column;

public enum BasketColumns {

    ID_BASKET("id_basket"),
    ID_ORDER("id_order"),
    ID_SERVICE("id_service");

    private String value;

    BasketColumns(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}