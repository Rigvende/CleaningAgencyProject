<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/removeattr.tld" %>
<fmt:setBundle basename="message"/>

<html>
<head>
    <title><fmt:message key="title.cleanerlist"/></title>
    <link rel="icon" href="<c:url value="/data/favicon.ico"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/styles.css"/>"/>
</head>
<body>
<c:if test="${empty user}">
    <jsp:forward page="/jsp/login.jsp"/>
</c:if>
<jsp:include page="/WEB-INF/view/header.jsp"/><br/>
<c:set var="cleaners" scope="session" value="${cleanerList}"/>
<c:set var="totalCount" scope="session" value="${cleanerList.size()}"/>
<c:set var="perPage" scope="session" value="${5}"/>
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

<form name="changeCleanerForm" method="post" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="changecleanerredirect" />
    <b class="field-mail"><fmt:message key="field.id"/></b>
    <label>
        <input type="text" name="id" value=""/>
    </label>
    <input class="menu-action-submit" type="submit" value="<fmt:message key="button.changecleaner"/>"/>
    <div class="little-error menu-margin">${errorChangeCleanerIdMessage}</div>
    ${wrongAction}
    ${nullPage}
    <ctg:remove-attr/>
</form>

<form name="deleteCleanerForm" method="post" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="deleteentity" />
    <input type="hidden" name="entitytype" value="cleaner" />
    <b class="field-mail"><fmt:message key="field.id"/></b>
    <label>
        <input type="text" name="id" value=""
               required oninvalid="this.setCustomValidity('<fmt:message key="message.required"/>')"
               oninput="setCustomValidity('')"/>
    </label>
    <input class="menu-action-submit" type="submit" value="<fmt:message key="button.deletecleaner"/>"/>
</form>

<div class="little-center">
    <h5><u class="positions"><fmt:message key="text.cleaners"/></u></h5>
</div><br/>

<div class="little-center">
    <table border="1" cellpadding="5" cellspacing="5" align="center">
        <tr class="little-back">
            <th><fmt:message key="field.id"/></th>
            <th><fmt:message key="field.name1"/></th>
            <th><fmt:message key="field.lastname1"/></th>
            <th><fmt:message key="field.phone1"/></th>
            <th><fmt:message key="field.email1"/></th>
            <th><fmt:message key="field.commission"/></th>
            <th><fmt:message key="field.notes"/></th>
            <th><fmt:message key="field.idcleaner2"/></th>
        </tr>
    <c:forEach var="cleaner" items="${cleanerList}" begin="${pageStart}" end="${pageStart + perPage - 1}">
        <tr>
            <td><c:out value="${cleaner.user.id}" /></td>
            <td><c:out value="${cleaner.user.name}" /></td>
            <td><c:out value="${cleaner.user.lastname}" /></td>
            <td><c:out value="${cleaner.user.phone}" /></td>
            <td><c:out value="${cleaner.user.email}" /></td>
            <td><c:out value="${cleaner.cleaner.commission}" /></td>
            <td><c:out value="${cleaner.cleaner.notes}" /></td>
            <td><c:out value="${cleaner.cleaner.id}" /></td>
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