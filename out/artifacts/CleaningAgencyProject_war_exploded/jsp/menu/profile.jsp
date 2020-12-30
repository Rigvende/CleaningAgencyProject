<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

    <fmt:setBundle basename="message"/>

<html>
<head>
    <title><fmt:message key="title.profile"/></title>
    <link rel="icon" href="<c:url value="/data/favicon.ico"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/background.css"/>" />
</head>

<body>
    <c:if test="${empty user}">
        <jsp:forward page="/jsp/login.jsp"/>
    </c:if>

    <jsp:include page="/WEB-INF/view/header.jsp"/>

    <jsp:include page="/WEB-INF/view/backToMain.jsp"/>

<br/><br/><br/><br/>
<div style="color: #0c4f5b; font-family: 'Palatino Linotype', serif; font-size: 18px; margin-left: 50px">
    <b><fmt:message key="text.personal"/></b>
    </div>
    <hr/>

    <div style="color: #0c4f5b; font-family: 'Palatino Linotype', serif; font-size: 18px; margin-left: 50px">
<b><fmt:message key="field.role"/></b> ${user.role}
<br/>
<b><fmt:message key="field.name1"/></b> ${user.name}
<br/>
<b><fmt:message key="field.lastname1"/></b> ${user.lastname}
<br/>
<b><fmt:message key="field.phone1"/></b> ${user.phone}
<br/>
<b><fmt:message key="field.email1"/></b> ${user.email}
<br/>
<b><fmt:message key="field.address"/></b> ${user.address}
<br/>
    </div>

<br/>
<form name="changeProfile" method="post" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="changeredirect"/>

    <input style="color: #0c4f5b; font-family: 'Palatino Linotype', sans-serif; width: 200px; font-size: 18px;
    height: 40px; margin-left: 50px" type="submit" value="<fmt:message key="button.change"/>"/>
</form>

<c:if test="${ not empty user and user.role eq 'client' }">

    <div style="color: #0c4f5b; font-family: 'Palatino Linotype', serif; font-size: 18px; margin-left: 50px">
    <b><fmt:message key="field.discount"/></b> ${client.discount}
    <br/><hr/><br/>

    <b><fmt:message key="field.location"/></b> ${client.location}
    <br/>

    <b><fmt:message key="field.relative"/></b> ${client.relative}
    <br/><br/>
    </div>

    <form name="changeBurial" method="post" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="changeburialredirect"/>

        <input style="color: #0c4f5b; font-family: 'Palatino Linotype', sans-serif; width: 200px; font-size: 18px;
    height: 40px; margin-left: 50px" type="submit" value="<fmt:message key="button.changeloc"/>"/>
    </form>
</c:if>

<c:if test="${ not empty user and user.role eq 'cleaner' }">
    <b style="color: #0c4f5b; font-family: 'Palatino Linotype', serif; font-size: 18px; margin-left: 50px">
    <fmt:message key="field.commission"/>
    </b> ${cleaner.commission}
    <br/>
</c:if>
<br/>

<jsp:include page="/WEB-INF/view/footer.jsp"/>
</body>
</html>