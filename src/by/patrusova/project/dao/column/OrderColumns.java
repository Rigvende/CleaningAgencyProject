package by.patrusova.project.dao.column;

public enum OrderColumns {

    ID_ORDER("id_order"),
    ORDER_TIME("order_time"),
    DEADLINE("deadline"),
    ORDER_STATUS("order_status"),
    MARK("mark"),
    ID_CLIENT("id_client"),
    ID_CLEANER("id_cleaner");

    private String value;

    OrderColumns(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}