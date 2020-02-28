<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<fmt:setBundle basename="message"/>

<html>
    <head><title><fmt:message key="title.index"/></title></head>

    <body>
        <jsp:forward page="/jsp/login.jsp"/>
    </body>

</html>