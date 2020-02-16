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
<br/>

<div style="float: left">
<fmt:message key="text.guests"/>
</div>
<br/>
<br/>

<div style="float: left">
<c:forEach var="guest" items="${guestList}">
    <tr>
        <td><c:out value="${guest.id}" /></td>
        <td><c:out value="${guest.name}" /></td>
        <td><c:out value="${guest.lastname}" /></td>
        <td><c:out value="${guest.phone}" /></td>
        <td><c:out value="${guest.email}" /></td>
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