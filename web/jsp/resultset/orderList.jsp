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

<c:set var="orders" scope="session" value="${orderList}"/>
<c:set var="totalCount" scope="session" value="${orderList.size()}"/>
<c:set var="perPage" scope="session" value="${5}"/>
<c:set var="pageStart" value="${param.start}"/>

<c:if test="${empty pageStart or pageStart < 0}">
    <c:set var="pageStart" value="0"/>
</c:if>

<c:if test="${totalCount < pageStart}">
    <c:set var="pageStart" value="${pageStart - perPage}"/>
</c:if>

<div style="float: right">
    <jsp:include page="/WEB-INF/view/backToMain.jsp"/>
</div>

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

    <div style="color: crimson">${errorChangeMessage}</div>
    ${wrongAction}
    ${nullPage}
    <br/><br/>

    <input type="submit" value="<fmt:message key="button.changeorder"/>"/>
</form>
<br/>

<div style="float: left">
    <h5><u><fmt:message key="text.orders"/></u></h5>
</div>
<br/><br/><br/><br/>

<div style="float: left">
    <table border="1" cellpadding="5" cellspacing="5">
        <tr>
            <th><fmt:message key="field.id"/></th>
            <th><fmt:message key="field.ordertime"/></th>
            <th><fmt:message key="field.deadline"/></th>
            <th><fmt:message key="field.status"/></th>
            <th><fmt:message key="field.mark"/></th>
            <th><fmt:message key="field.isclient"/></th>
            <th><fmt:message key="field.idcleaner"/></th>
        </tr>

    <c:forEach var="order" items="${orderList}" begin="${pageStart}" end="${pageStart + perPage - 1}">
        <tr>
            <td><c:out value="${order.id}" /></td>
            <td><c:out value="${order.orderTime}" /></td>
            <td><c:out value="${order.deadline}" /></td>
            <td><c:out value="${order.orderStatus}" /></td>
            <td><c:out value="${order.mark}" /></td>
            <td><c:out value="${order.idClient}" /></td>
            <td><c:out value="${order.idCleaner}" /></td>
        </tr>
    </c:forEach>
    </table>
    <br/>

    <a href="?start=${pageStart - perPage}"><<</a>
    ${pageStart + 1} - ${pageStart + perPage}
    <a href="?start=${pageStart + perPage}">>></a>
</div>
<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>

<jsp:include page="/WEB-INF/view/footer.jsp"/>
</body>
</html>