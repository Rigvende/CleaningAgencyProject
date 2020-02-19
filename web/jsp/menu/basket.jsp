<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

    <fmt:setBundle basename="message"/>

<html>
    <head>
        <title><fmt:message key="title.basket"/></title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/background.css"/>" />
    </head>

    <body>
    <jsp:include page="/WEB-INF/view/header.jsp"/>

    <div style="float: right">
    <jsp:include page="/WEB-INF/view/backToMain.jsp"/>
    </div>
    <br/><br/>

Ваша корзина пуста :(

    <jsp:include page="/WEB-INF/view/footer.jsp"/>
    </body>
</html>