<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<fmt:setBundle basename="message"/>

<html>
<head>
    <title><fmt:message key="title.adminlist"/></title>
</head>

<body>
<jsp:include page="/WEB-INF/view/header.jsp"/>
<br/>

<div style="float: left">
    <fmt:message key="text.admins"/>
</div>
<br/>
<br/>

<div style="float: left">
    <c:forEach var="admin" items="${adminList}">
        <tr>
            <td><c:out value="${admin.id}" /></td>
            <td><c:out value="${admin.name}" /></td>
            <td><c:out value="${admin.lastname}" /></td>
            <td><c:out value="${admin.phone}" /></td>
            <td><c:out value="${admin.email}" /></td>
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