<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Регистрация</title>
</head>
<body>
    <h1>Регистрация</h1>

    <c:if test="${violations != null}">
        <c:forEach items="${violations}" var="violation">
            <p>${violation}.</p>
        </c:forEach>
    </c:if>

    <form action="${pageContext.request.contextPath}/processclient" method="post">
        <label for="client_name">Имя : </label>
        <input type="text" name="client_name" id="client_name" value="${client_name}">
        <label for="lastname">Отчество : </label>
                <input type="text" name="lastname" id="lastname" value="${lastname}">
        <label for="surname">Фамилия:
        <input type="text" name="surname" id="surname" value="${surname}">
        <label for="phone">Телефон: </label>
        <input type="bigint" name="phone" id="phone" value="${phone}">
        <input type="submit" name="signup" value="Sign Up">
    </form>
</body>
</html>