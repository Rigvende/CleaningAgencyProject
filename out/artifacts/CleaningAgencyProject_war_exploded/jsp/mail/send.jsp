<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<fmt:setBundle basename="message"/>

<html>
<head>
    <title><fmt:message key="title.send"/></title>
</head>


<body>
<jsp:include page="/WEB-INF/view/header.jsp"/>

<div style="float: right">
    <jsp:include page="/WEB-INF/view/backToMain.jsp"/>
</div>
<br/><br/>

<div style="color: olivedrab;"><h3> <fmt:message key="text.send"/></h3></div>
<br/><br/>


<jsp:include page="/WEB-INF/view/footer.jsp"/>
</body>
</html>