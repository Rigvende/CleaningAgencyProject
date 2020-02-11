package by.patrusova.project.entity;

public enum Role {

    ADMIN("admin"),
    CLIENT("client"),
    CLEANER("cleaner"),
    GUEST("guest");

    private String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}