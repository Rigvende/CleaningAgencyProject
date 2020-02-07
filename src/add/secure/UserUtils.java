package add.secure;

import by.patrusova.project.entity.impl.User;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class UserUtils {

    private static int redirects = 0;

    private static final Map<Integer, String> id_uri_map = new HashMap<Integer, String>();
    private static final Map<String, Integer> uri_id_map = new HashMap<String, Integer>();

    // Сохранить информацию пользователя в Session.
    public static void storeLoginedUser(HttpSession session, User user) {
        // На JSP можно получить доступ через ${loginedUser}
        session.setAttribute("user", user);
    }

    // Получить информацию пользователя, сохраненную в Session.
    public static User getLoginedUser(HttpSession session) {
        return (User) session.getAttribute("user");
    }

    public static int storeRedirectAfterLoginUrl(HttpSession session, String requestUri) {
        Integer id = uri_id_map.get(requestUri);
        if (id == null) {
            id = redirects++;
            uri_id_map.put(requestUri, id);
            id_uri_map.put(id, requestUri);
            return id;
        }
        return id;
    }

    public static String getRedirectAfterLoginUrl(HttpSession session, int redirectId) {
        return id_uri_map.get(redirectId);
    }
}