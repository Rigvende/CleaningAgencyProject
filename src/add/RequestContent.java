package add;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class RequestContent {

    private HashMap<String, String[]> requestParameters;

    public Map<String, String[]> extractValues(HttpServletRequest request) {
        requestParameters = (HashMap<String, String[]>) request.getParameterMap();
        return requestParameters;
    }
}