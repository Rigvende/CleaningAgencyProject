package add;

public class XssValidator {

    private final static String L = "<";
    private final static String R = ">";
    private final static String LT = "&lt;";
    private final static String RT = "&rt;";


    public static String protect(String xssString) {
        return xssString.replaceAll(L, LT).replaceAll(R, RT); //todo на каких данных и где валидировать?
    }
}