<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<fmt:setBundle basename="message"/>

<html><head>
    <title><fmt:message key="title.mainadmin"/></title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/background.css"/>" />
</head>

<body>
<jsp:include page="/WEB-INF/view/header.jsp"/>

<h2 style="color: olivedrab"><fmt:message key="text.main1"/> ${user.name}!</h2>
<h3><fmt:message key="text.main2"/></h3>

</div>
<div style="float: right" >
    <div style="margin-right: 45px">
    <b><fmt:message key="text.main4"/></b>
    </div>
</div>
<br/><br/>

<div style="float: right">
    <form name="guestForm" method="post" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="showguests" />
        <input type="submit" value="<fmt:message key="button.showguests"/>"/>
    </form>

    <form name="adminForm" method="post" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="showadmins" />
        <input type="submit" value="<fmt:message key="button.showadmines"/>"/>
    </form>

    <form name="clientForm" method="post" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="showclients" />
        <input type="submit" value="<fmt:message key="button.showclients"/>"/>
    </form>

    <form name="cleanerForm" method="post" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="showcleaners" />
        <input type="submit" value="<fmt:message key="button.showcleaners"/>"/>
    </form>

    <form name="orderForm" method="post" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="showorders" />
        <input type="submit" value="<fmt:message key="button.showorders"/>"/>
    </form>

    <form name="serviceForm" method="post" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="showcatalogue" />
        <input type="submit" value="<fmt:message key="button.showservices"/>"/>
    </form>
</div>

<div style="margin-left: 77px">
    <b><fmt:message key="text.main3"/></b>
</div>
<br/>

<jsp:include page="/WEB-INF/view/menu.jsp"/>
<jsp:include page="/WEB-INF/view/footer.jsp"/>
</body>
</html>