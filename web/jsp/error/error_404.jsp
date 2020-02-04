<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
<html>
    <head>
        <title>404</title>
    </head>
    <body>
    <ctg:info-time/>
    <hr/>
    Sorry, page does not found.
    <br/>
    <a href="${pageContext.request.contextPath}/jsp/main.jsp">Return to Main Page</a>|
    </body>
</html>