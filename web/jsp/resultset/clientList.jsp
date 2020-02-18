<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<fmt:setBundle basename="message"/>

<html>
<head>
    <title><fmt:message key="title.clientlist"/></title>
</head>

<body>
<jsp:include page="/WEB-INF/view/header.jsp"/>
<br/>

<c:set var="clients" scope="session" value="${clientList}"/>
<c:set var="totalCount" scope="session" value="${clientList.size()}"/>
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

<form name="changeClientForm" method="post" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="changeclientredirect" />

    <fmt:message key="field.id"/>
    <label>
        <input type="number" name="id" value=""/>
    </label>

    <input type="submit" value="<fmt:message key="button.changeclient"/>"/>
</form>

<form name="deleteClientForm" method="post" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="deleteentity" />
    <input type="hidden" name="entitytype" value="client" />

    <fmt:message key="field.id"/>
    <label>
        <input type="number" name="id" value=""/>
    </label>

    <input type="submit" value="<fmt:message key="button.deleteclient"/>"/>
</form>

<div style="float: left">
    <h5><u><fmt:message key="text.clients"/></u></h5>
</div>
<br/><br/>

<div style="float: left">
    <table border="1" cellpadding="5" cellspacing="5">
        <tr>
            <th><fmt:message key="field.id"/></th>
            <th><fmt:message key="field.name1"/></th>
            <th><fmt:message key="field.lastname1"/></th>
            <th><fmt:message key="field.phone1"/></th>
            <th><fmt:message key="field.email1"/></th>
            <th><fmt:message key="field.discount"/></th>
            <th><fmt:message key="field.location"/></th>
            <th><fmt:message key="field.relative"/></th>
            <th><fmt:message key="field.notes"/></th>
            <th><fmt:message key="field.isclient"/></th>
        </tr>

    <c:forEach var="clie" items="${clientList}" begin="${pageStart}" end="${pageStart + perPage - 1}">
        <tr>
            <td><c:out value="${clie.id}" /></td>
            <td><c:out value="${clie.name}" /></td>
            <td><c:out value="${clie.lastname}" /></td>
            <td><c:out value="${clie.phone}" /></td>
            <td><c:out value="${clie.email}" /></td>
            <td><c:out value="${clie.client.discount}" /></td>
            <td><c:out value="${clie.client.location}" /></td>
            <td><c:out value="${clie.client.relative}" /></td>
            <td><c:out value="${clie.client.notes}" /></td>
            <td><c:out value="${clie.client.id}" /></td>
        </tr>
    </c:forEach>
    </table>
    <br/>

    <a href="?start=${pageStart - perPage}"><<</a>
    ${pageStart + 1} - ${pageStart + perPage}
    <a href="?start=${pageStart + perPage}">>></a>
</div>
<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>

<jsp:include page="/WEB-INF/view/footer.jsp"/>
</body>
</html>