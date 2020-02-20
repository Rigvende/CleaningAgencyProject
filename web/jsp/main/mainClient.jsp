<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<fmt:setBundle basename="message"/>

<html><head>
    <title><fmt:message key="title.mainclient"/></title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/background.css"/>" />
</head>

<body>
<jsp:include page="/WEB-INF/view/header.jsp"/>

<h2 style="color: #0c4f5b; margin-left: 50px">
    <fmt:message key="text.main1"/> ${user.name}!
</h2>
<h3 style="color: #0c4f5b; font-family: 'Palatino Linotype',serif; margin-left: 200px">
    <fmt:message key="text.main2"/>
</h3>

<div style="float: right; " >
    <div style="margin-right: 120px;">
        <b style="color: #0c4f5b; font-family: 'Palatino Linotype',serif; background-color: lightgoldenrodyellow">
            <fmt:message key="text.main4"/>
        </b>
    </div>
</div>
<br/><br/>

<div style="float: right">
    <form name="orderForm" method="post" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="showordersclient" />
        <input style="color: #0c4f5b; font-family: 'Palatino Linotype', sans-serif; width: 250px; font-size: 20px;
            height: 40px; margin-right: 50px" type="submit" value="<fmt:message key="button.showorders"/>"/>
    </form>
</div>

<div style="margin-left: 125px">
    <b style="color: #0c4f5b; font-family: 'Palatino Linotype',serif; background-color: lightgoldenrodyellow">
        <fmt:message key="text.main3"/>
    </b>
</div>
<br/>

<div style="margin-left: 50px">
<jsp:include page="/WEB-INF/view/menu.jsp"/>
</div>

<jsp:include page="/WEB-INF/view/footer.jsp"/>
</body>
</html>