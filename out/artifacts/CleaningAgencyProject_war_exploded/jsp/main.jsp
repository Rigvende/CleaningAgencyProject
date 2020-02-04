<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
<html><head><title>Welcome</title></head>
<body>
    <h3>Welcome</h3>
    <hr/>
        <ctg:info-time/>
    <hr/>
        Hello! What do you want to do now?
    <hr/>
    <a href="controller?command=profile">Go to Profile</a>
    <a href="${pageContext.request.contextPath}/jsp/catalogue.jsp">Look into Catalogue</a>
    <a href="${pageContext.request.contextPath}/jsp/basket.jsp">See Info</a>
    <hr/>
    <a href="${pageContext.request.contextPath}/jsp/logout/logoutConfirm.jsp">Logout</a>
</body>
</html>