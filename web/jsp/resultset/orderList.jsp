<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<fmt:setBundle basename="message"/>

<html>
<head>
    <title><fmt:message key="title.orderlist"/></title>
</head>

<body>
<jsp:include page="/WEB-INF/view/header.jsp"/>
<br/>

<form name="changeOrderForm" method="post" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="changeorder" />
    <fmt:message key="field.id"/>
    <br/>
    <label>
        <input type="number" name="id" value=""/>
    </label>
    <br/>
    <fmt:message key="field.status"/>
    <br/>
    <label>
        <input type="text" name="status" value=""/>
    </label>
    <input type="submit" value="<fmt:message key="button.changeorder"/>"/>
</form>
<br/>

<div style="float: left">
    <fmt:message key="text.orders"/>
</div>
<br/>
<br/>

<div style="float: left">
    <c:forEach var="order" items="${orderList}">
        <tr>
            <td><c:out value="${order.id}" /></td>
            <td><c:out value="${order.orderTime}" /></td>
            <td><c:out value="${order.deadline}" /></td>
            <td><c:out value="${order.orderStatus}" /></td>
            <td><c:out value="${order.mark}" /></td>
            <td><c:out value="${order.idClient}" /></td>
            <td><c:out value="${order.idCleaner}" /></td>
        </tr>
        <br/>
    </c:forEach>
</div>

<div style="float: right">
    <jsp:include page="/WEB-INF/view/backToMain.jsp"/>
</div>
<br/>
<br/>
<br/>

<jsp:include page="/WEB-INF/view/footer.jsp"/>
</body>
</html>