<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<fmt:setBundle basename="message"/>

<html>
<head>
    <title><fmt:message key="title.confirm"/></title>
    <link rel="icon" href="<c:url value="/data/favicon.ico"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/styles.css"/>"/>
</head>
<body>

    <div class="blr header-text">
        <u class="big-text"><fmt:message key="text.company"/></u><br/>
        <b class="subheader-text"><fmt:message key="text.company2"/></b><br/><br/>
        <div class="sub-text">
            <fmt:message key="text.company3"/>
        </div>
    </div><br/><br/><br/><br/><br/>

    <div class="little-center">
    <p class="little-font"><fmt:message key="text.order"/> ${orderNew.id}</p>
    <p class="little-font"><fmt:message key="text.order2"/></p>
    <p class="little-font"><fmt:message key="text.order3"/></p>
    <p class="little-font"><fmt:message key="text.order4"/></p>
    <p class="little-font"><fmt:message key="text.order5"/></p>

    <form name="addOrderForm" method="post" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="addorder" />
        <div class="little-align-right">
            <input class="action-submit" type="submit" value="<fmt:message key="button.ok"/>"/>
        </div>
    </form>
    </div>

<jsp:include page="/WEB-INF/view/footer.jsp"/>
</body>
</html>