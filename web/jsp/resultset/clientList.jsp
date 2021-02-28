<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/removeattr.tld" %>
<fmt:setBundle basename="message"/>

<html>
<head>
    <title><fmt:message key="title.clientlist"/></title>
    <link rel="icon" href="<c:url value="/data/favicon.ico"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/styles.css"/>"/>
</head>
<body>
<c:if test="${empty user}">
    <jsp:forward page="/jsp/login.jsp"/>
</c:if>
<jsp:include page="/WEB-INF/view/header.jsp"/><br/>
<c:set var="clients" scope="session" value="${clientList}"/>
<c:set var="totalCount" scope="session" value="${clientList.size()}"/>
<c:set var="perPage" scope="session" value="${10}"/>
<c:set var="pageStart" value="${param.start}"/>
<c:if test="${empty pageStart or pageStart < 0}">
    <c:set var="pageStart" value="0"/>
</c:if>
<c:if test="${totalCount < pageStart}">
    <c:set var="pageStart" value="${pageStart - perPage}"/>
</c:if>

<div class="little-float">
    <jsp:include page="/WEB-INF/view/backToMain.jsp"/>
</div>

<form name="changeClientForm" method="post" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="changeclientredirect" />
    <b class="field-mail"><fmt:message key="field.id"/></b>
    <label>
        <input type="text" name="id" value=""/>
    </label>
    <input class="menu-action-submit" type="submit" value="<fmt:message key="button.changeclient"/>"/>
    <div class="little-error menu-margin">${errorChangeClientIdMessage}</div>
    ${wrongAction}
    ${nullPage}
    <ctg:remove-attr/>
</form>

<form name="deleteClientForm" method="post" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="deleteentity" />
    <input type="hidden" name="entitytype" value="client" />
    <b class="field-mail"><fmt:message key="field.id"/></b>
    <label>
        <input type="text" name="id" value=""
               required oninvalid="this.setCustomValidity('<fmt:message key="message.required"/>')"
               oninput="setCustomValidity('')"/>
    </label>
    <input class="menu-action-submit" type="submit" value="<fmt:message key="button.deleteclient"/>"/>
</form>

<div class="little-center">
    <h5><u class="positions"><fmt:message key="text.clients"/></u></h5>
</div>

<div class="little-center">
    <table border="1" cellpadding="5" cellspacing="5" align="center">
        <tr class="little-back">
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
    <c:forEach var="client" items="${clientList}" begin="${pageStart}" end="${pageStart + perPage - 1}">
        <tr>
            <td><c:out value="${client.user.id}" /></td>
            <td><c:out value="${client.user.name}" /></td>
            <td><c:out value="${client.user.lastname}" /></td>
            <td><c:out value="${client.user.phone}" /></td>
            <td><c:out value="${client.user.email}" /></td>
            <td><c:out value="${client.client.discount}" /></td>
            <td><c:out value="${client.client.location}" /></td>
            <td><c:out value="${client.client.relative}" /></td>
            <td><c:out value="${client.client.notes}" /></td>
            <td><c:out value="${client.client.id}" /></td>
        </tr>
    </c:forEach>
    </table><br/>
    <a href="?start=${pageStart - perPage}"><<</a>
    ${pageStart + 1} - ${pageStart + perPage}
    <a href="?start=${pageStart + perPage}">>></a>
</div><br/>
<jsp:include page="/WEB-INF/view/footer.jsp"/>
</body>
</html>