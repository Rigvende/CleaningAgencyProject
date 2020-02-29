package by.patrusova.project.entity;

import by.patrusova.project.command.CommandEnum;

/**
 * Enum for storing user's roles
 * @autor Marianna Patrusova
 * @version 1.0
 */
public enum Role {

    ADMIN("admin"),
    CLIENT("client"),
    CLEANER("cleaner"),
    GUEST("guest");

    private String value;

    /**
     * Constructor: create instance of Role
     */
    Role(String value) {
        this.value = value;
    }

    /**
     * Method: get role
     * @return String value of Role's instance
     * */
    public String getValue() {
        return value;
    }
}