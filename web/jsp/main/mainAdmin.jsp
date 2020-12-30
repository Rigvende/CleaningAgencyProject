<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/removeattr.tld" %>

<fmt:setBundle basename="message"/>

<html><head>
    <title><fmt:message key="title.mainadmin"/></title>
    <link rel="icon" href="<c:url value="/data/favicon.ico"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/background.css"/>" />
</head>

<body>
<c:if test="${empty user}">
    <jsp:forward page="/index.jsp"/>
</c:if>

<jsp:include page="/WEB-INF/view/header.jsp"/>

<h2 style="color: #0c4f5b; text-align: center">
    <fmt:message key="text.main1"/> ${user.name}!
</h2>
<h3 style="color: #0c4f5b; font-family: 'Palatino Linotype',serif; text-align: center">
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

<div style="float: right; margin-right: 50px">
    <form name="guestForm" method="post" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="showguests" />
        <input style="color: #0c4f5b; font-family: 'Palatino Linotype', sans-serif; width: 250px; font-size: 20px;
            height: 40px" type="submit" value="<fmt:message key="button.showguests"/>"/>
    </form>

    <form name="adminForm" method="post" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="showadmins" />
        <input style="color: #0c4f5b; font-family: 'Palatino Linotype', sans-serif; width: 250px; font-size: 20px;
            height: 40px" type="submit" type="submit" value="<fmt:message key="button.showadmines"/>"/>
    </form>

    <form name="clientForm" method="post" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="showclients" />
        <input style="color: #0c4f5b; font-family: 'Palatino Linotype', sans-serif; width: 250px; font-size: 20px;
            height: 40px" type="submit" type="submit" value="<fmt:message key="button.showclients"/>"/>
    </form>

    <form name="cleanerForm" method="post" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="showcleaners" />
        <input style="color: #0c4f5b; font-family: 'Palatino Linotype', sans-serif; width: 250px; font-size: 20px;
            height: 40px" type="submit" type="submit" value="<fmt:message key="button.showcleaners"/>"/>
    </form>

    <form name="orderForm" method="post" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="showorders" />
        <input style="color: #0c4f5b; font-family: 'Palatino Linotype', sans-serif; width: 250px; font-size: 20px;
            height: 40px" type="submit" type="submit" value="<fmt:message key="button.showorders"/>"/>
    </form>

    <form name="serviceForm" method="post" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="showcatalogue" />
        <input style="color: #0c4f5b; font-family: 'Palatino Linotype', sans-serif; width: 250px; font-size: 20px;
            height: 40px" type="submit" type="submit" value="<fmt:message key="button.showservices"/>"/>
    </form>

    <div style="color: crimson; margin-left: 50px">${emptyList}</div>
    ${wrongAction}
    ${nullPage}
    <br/><ctg:remove-attr/>
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
<br/><br/><br/><br/>

<jsp:include page="/WEB-INF/view/footer.jsp"/>
</body>
</html>