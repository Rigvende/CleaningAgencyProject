<jsp:useBean id="userName" scope="request" type="java.lang.String"/>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
<html><head>
    <title>Welcome</title>
    <link href="${pageContext.request.contextPath}/css/h1.css" rel="stylesheet">
</head>
<body>
<h1>Welcome, ${userName}!</h1>
<ctg:info-time/>
<h3>Hello! What do you want to do now?</h3>
<br/>
<a href="${pageContext.request.contextPath}/jsp/profile/cleaner/profileCleaner.jsp">Go to Profile</a>
<br/>
<a href="${pageContext.request.contextPath}/jsp/catalogue.jsp">Look into Catalogue</a>
<br/>
<a href="${pageContext.request.contextPath}/jsp/basket.jsp">See Info</a>
<hr/>
<a href="${pageContext.request.contextPath}/jsp/logout/logoutConfirm.jsp">Logout</a>
</body>
</html>
