package add;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public enum CommandAccess {

    GET_BET_INFO("admin", "client", "cleaner"),
    ADD_USER("admin"),
    GET_RACE_RESULT_GENERATION_PAGE("admin"),
    GET_RACE_MANAGE_PAGE("admin"),
    GENERATE_RACE_RESULT("admin"),
    INVALIDATE_BET("admin"),
    ADD_BALANCE("client"),
    GET_ALL_USERS("admin"),
    DELETE_USER("admin"),
    GET_ADD_USER_PAGE("admin"),
    GET_SET_BOOKMAKER_PAGE("admin"),
    GET_FACTOR_PAGE("cleaner"),
    GET_BOOKMAKER_PAGE("cleaner"),

    SET_BOOKMAKER("admin"),

    SET_NEW_FACTORS("cleaner"),

    SET_BET("client"),

    GET_SUCCESSFUL_BET_PAGE("client"),

    GET_NEW_RACE_PAGE("admin"),

    SET_NEW_RACE("admin"),

    SET_NEW_PARTICIPANT("admin"),

    GET_RACE_SELECT_TO_CHANGE_PAGE("admin"),

    GET_RACE_CHANGE_PAGE("admin"),

    LOG_IN("admin", "client", "cleaner"),

    GET_REGAIN_INFO("admin", "client", "cleaner"),

    GET_LOGIN_PAGE("admin", "client", "cleaner"),

    GET_REGISTER_PAGE("admin", "client", "cleaner"),

    GET_EMAIL_ENTER_PAGE("admin", "client", "cleaner"),

    GET_NEW_PASS_PAGE("admin", "client", "cleaner"),

    LOG_OUT("admin", "client", "cleaner"),

    SIGN_UP("admin", "client", "cleaner"),

    SHOW_RACE_INFO("admin", "client", "cleaner"),

    GET_ALL_RACES("admin", "client", "cleaner"),

    GET_USER_BALANCE("client"),

    GET_ALL_BETS("client"),

    REGAIN_PASS("admin", "client", "cleaner"),

    NEW_PASS("admin", "client", "cleaner"),

    GET_HOME_PAGE_DATA("admin", "client", "cleaner");

    private List<String> roles;

    CommandAccess(String... roles) {
        this.roles = List.of(roles);
    }


    public static Optional<CommandAccess> fromString(String type) {

        return Stream.of(CommandAccess.values())
                .filter(e -> e.name().equalsIgnoreCase(type))
                .findFirst();
    }

    public boolean isValidRole(String role) {
        return roles.stream().anyMatch(r -> r.equalsIgnoreCase(role));
    }
}
