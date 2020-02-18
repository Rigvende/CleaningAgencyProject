<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<fmt:setBundle basename="message"/>

<html>
<head>
    <title><fmt:message key="title.guestlist"/></title>
</head>

<body>
<jsp:include page="/WEB-INF/view/header.jsp"/>
<br/>

<c:set var="guests" scope="session" value="${guestList}"/>
<c:set var="totalCount" scope="session" value="${guestList.size()}"/>
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

<form name="changeGuestForm" method="post" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="changeguest" />

    <fmt:message key="field.id"/>
    <br/>
    <label>
        <input type="number" name="id" value=""/>
    </label>
    <br/>

    <fmt:message key="field.role"/>
    <br/>
    <label>
        <input type="text" name="role" value=""/>
    </label>

    <input type="submit" value="<fmt:message key="button.changeguest"/>"/>
</form>

<div style="float: left">
    <h5><u><fmt:message key="text.guests"/></u></h5>
</div>
<br/>
<br/>
<br/>
<br/>

<div style="float: left">
    <table border="1" cellpadding="5" cellspacing="5">
    <tr>
        <th><fmt:message key="field.id"/></th>
        <th><fmt:message key="field.name1"/></th>
        <th><fmt:message key="field.lastname1"/></th>
        <th><fmt:message key="field.phone1"/></th>
        <th><fmt:message key="field.email1"/></th>
    </tr>

    <c:forEach var="guest" items="${guestList}" begin="${pageStart}" end="${pageStart + perPage - 1}">

    <tr>
        <td><c:out value="${guest.id}" /></td>
        <td><c:out value="${guest.name}" /></td>
        <td><c:out value="${guest.lastname}" /></td>
        <td><c:out value="${guest.phone}" /></td>
        <td><c:out value="${guest.email}" /></td>
    </tr>
    </c:forEach>
    </table>
    <br/>

    <a href="?start=${pageStart - perPage}"><<</a>
    ${pageStart + 1} - ${pageStart + perPage}
    <a href="?start=${pageStart + perPage}">>></a>
</div>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>

<jsp:include page="/WEB-INF/view/footer.jsp"/>
</body>
</html>