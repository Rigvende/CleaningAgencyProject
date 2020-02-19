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

<form name="changeOrderForm" method="post" action="${pageContext.request.contextPath}/controller">
    <h3><fmt:message key="text.change1"/></h3>
    <input type="hidden" name="command" value="changeorder" />

    <fmt:message key="field.status"/>
    <br/>
    <label>
        <input type="text" name="status" value="${not empty order.orderStatus ? order.orderStatus : ''}"/>
    </label>
    <br/>

    <fmt:message key="field.idcleaner"/>
    <br/>
    <label>
        <input type="text" name="id_cleaner" value="${not empty order.idCleaner ? order.idCleaner : ''}"/>
    </label>
    <br/>

    <div style="color: crimson">${errorChangeOrderMessage}</div>
    ${wrongAction}
    ${nullPage}
    <br/><br/>

    <input type="submit" value="<fmt:message key="button.changeorder"/>"/>
</form>
<br/>

<jsp:include page="/WEB-INF/view/backToMain.jsp"/>
<br/><br/><br/>

<jsp:include page="/WEB-INF/view/footer.jsp"/>
</body>
</html>