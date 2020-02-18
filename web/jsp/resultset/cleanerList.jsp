<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<fmt:setBundle basename="message"/>

<html>
<head>
    <title><fmt:message key="title.cleanerlist"/></title>
</head>

<body>
<jsp:include page="/WEB-INF/view/header.jsp"/>
<br/>

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

<div style="float: right">
    <jsp:include page="/WEB-INF/view/backToMain.jsp"/>
</div>

<form name="changeCleanerForm" method="post" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="changecleanerredirect" />

    <fmt:message key="field.id"/>

    <label>
        <input type="number" name="id" value=""/>
    </label>

    <input type="submit" value="<fmt:message key="button.changecleaner"/>"/>
</form>

<form name="deleteCleanerForm" method="post" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="deleteentity" />
    <input type="hidden" name="entitytype" value="cleaner" />

    <fmt:message key="field.id"/>
    <label>
        <input type="number" name="id" value=""/>
    </label>

    <input type="submit" value="<fmt:message key="button.deletecleaner"/>"/>
</form>

<div style="float: left">
    <h5><u><fmt:message key="text.cleaners"/></u></h5>
</div>
<br/><br/><br/><br/>

<div style="float: left">
    <table border="1" cellpadding="5" cellspacing="5">
        <tr>
            <th><fmt:message key="field.id"/></th>
            <th><fmt:message key="field.name1"/></th>
            <th><fmt:message key="field.lastname1"/></th>
            <th><fmt:message key="field.phone1"/></th>
            <th><fmt:message key="field.email1"/></th>
            <th><fmt:message key="field.commission"/></th>
            <th><fmt:message key="field.notes"/></th>
            <th><fmt:message key="field.idcleaner"/></th>
        </tr>

    <c:forEach var="clean" items="${cleanerList}" begin="${pageStart}" end="${pageStart + perPage - 1}">
        <tr>
            <td><c:out value="${clean.id}" /></td>
            <td><c:out value="${clean.name}" /></td>
            <td><c:out value="${clean.lastname}" /></td>
            <td><c:out value="${clean.phone}" /></td>
            <td><c:out value="${clean.email}" /></td>
            <td><c:out value="${clean.cleaner.commission}" /></td>
            <td><c:out value="${clean.cleaner.notes}" /></td>
            <td><c:out value="${clean.cleaner.id}" /></td>
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