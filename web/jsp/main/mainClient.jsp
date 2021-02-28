<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<fmt:setBundle basename="message"/>

<html><head>
    <title><fmt:message key="title.mainclient"/></title>
    <link rel="icon" href="<c:url value="/data/favicon.ico"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/styles.css"/>"/>
</head>
<body>
<c:if test="${empty user}">
    <jsp:forward page="/jsp/login.jsp"/>
</c:if>
<jsp:include page="/WEB-INF/view/header.jsp"/>

<h2 class="welcome">
    <fmt:message key="text.main1"/> ${user.name}!
</h2>
<h3 class="welcome little-font">
    <fmt:message key="text.main2"/>
</h3>

<div class="little-float">
    <div class="margin-right-again">
        <b class="menu-header"><fmt:message key="text.main4"/></b>
    </div>
</div><br/><br/>

<div class="little-float little-right">
    <form name="orderForm" method="post" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="showordersclient" />
        <input class="menu-submit" type="submit" value="<fmt:message key="button.showorders"/>"/>
    </form>
</div>

<div class="menu-margin">
    <b class="menu-header"><fmt:message key="text.main3"/></b>
</div><br/>

<div class="little-margin-left">
<jsp:include page="/WEB-INF/view/menu.jsp"/>
</div>
<jsp:include page="/WEB-INF/view/footer.jsp"/>
</body>
</html>