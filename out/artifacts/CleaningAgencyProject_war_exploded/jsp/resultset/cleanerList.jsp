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

<form name="changeCleanerForm" method="post" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="changecleaner" />
    <fmt:message key="field.id"/>
    <br/>
    <label>
        <input type="number" name="id" value=""/>
    </label>
    <br/>
    <fmt:message key="field.commission"/>
    <br/>
    <label>
        <input type="text" name="commission" value=""/>
    </label>
    <input type="submit" value="<fmt:message key="button.changecleaner"/>"/>
</form>
<br/>

<form name="deleteCleanerForm" method="post" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="deleteentity" />
    <fmt:message key="field.id"/>
    <br/>
    <label>
        <input type="number" name="id" value=""/>
    </label>
    <input type="submit" value="<fmt:message key="button.deletecleaner"/>"/>
</form>
<br/>

<div style="float: left">
    <fmt:message key="text.cleaners"/>
</div>
<br/>
<br/>

<div style="float: left">
    <c:forEach var="cleaner" items="${cleanerList}">
        <tr>
            <td><c:out value="${cleaner.id}" /></td>
            <td><c:out value="${cleaner.commission}" /></td>
            <td><c:out value="${cleaner.notes}" /></td>
            <td><c:out value="${cleaner.idUser}" /></td>
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