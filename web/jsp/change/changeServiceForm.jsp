<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<fmt:setBundle basename="message"/>

<html>
<head>
    <title><fmt:message key="title.change"/></title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/background.css"/>" />
</head>

<body>
<jsp:include page="/WEB-INF/view/header.jsp"/>

<form name="changeServiceForm" method="post" action="${pageContext.request.contextPath}/controller">
    <h3><fmt:message key="text.change1"/></h3>
    <input type="hidden" name="command" value="changeservice" />

    <fmt:message key="field.service"/>
    <br/>
    <label>
        <input type="text" name="servicechange" value="${not empty service.service ? service.service : ''}"/>
    </label>
    <br/>

    <fmt:message key="field.cost"/>
    <br/>
    <label>
        <input type="text" name="cost" value="${not empty service.cost ? service.cost : '0.00'}"/>
    </label>
    <br/>

    <fmt:message key="field.discount2"/>
    <br/>
    <label>
        <input type="text" name="discount" value="${not empty service.discount ? service.discount : '0.00'}"/>
    </label>

    <div style="color: crimson">${errorChangeServiceMessage}</div>
    ${wrongAction}
    ${nullPage}
    <br/><br/>

    <input type="submit" value="<fmt:message key="button.changeservice"/>"/>
</form>
<br/>

<jsp:include page="/WEB-INF/view/backToMain.jsp"/>
<br/><br/><br/>

<jsp:include page="/WEB-INF/view/footer.jsp"/>
</body>
</html>