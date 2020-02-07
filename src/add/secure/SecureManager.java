package add.secure;

import by.patrusova.project.entity.Role;
import java.util.*;

public class SecureManager {

    private static final Map<String, List<String>> secureConfig = new HashMap<>();

    static {
        init();
    }

    private static void init() {
        List<String> urlPatterns1 = new ArrayList<>();
        urlPatterns1.add("/info");
        urlPatterns1.add("/profileClient");
        urlPatterns1.add("/catalogue");
        urlPatterns1.add("/basket");
        secureConfig.put(String.valueOf(Role.CLIENT), urlPatterns1);
        List<String> urlPatterns2 = new ArrayList<>();
        urlPatterns2.add("/info");
        urlPatterns2.add("/profileCleaner");
        urlPatterns2.add("/catalogue");
        urlPatterns2.add("/basket");
        secureConfig.put(String.valueOf(Role.CLEANER), urlPatterns2);
        List<String> urlPatterns3 = new ArrayList<>();
        urlPatterns3.add("/info");
        urlPatterns3.add("/profileAdmin");
        urlPatterns3.add("/catalogue");
        urlPatterns3.add("/basket");
        secureConfig.put(String.valueOf(Role.ADMIN), urlPatterns3);
    }

    public static Set<String> getAllRoles() {
        return secureConfig.keySet();
    }

    public static List<String> getUrlPatternsForRole(String role) {
        return secureConfig.get(role);
    }
}