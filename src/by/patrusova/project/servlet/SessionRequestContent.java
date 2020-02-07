package by.patrusova.project.servlet;

import java.util.Enumeration;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;

public class SessionRequestContent {
    private HashMap<String, Object> requestAttributes;
    private HashMap<String, String[]> requestParameters;
    private HashMap<String, Object> sessionAttributes;
    // конструкторы
// метод извлечения информации из запроса
    public Enumeration<String> extractValues(HttpServletRequest request) {
        return request.getParameterNames();
    }
    // метод добавления в запрос данных для передачи в jsp
    public void insertAttributes(HttpServletRequest request) {
// реализация
    }
// some methods
}

