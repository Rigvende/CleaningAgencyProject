<<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
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

<form name="changeClientForm" method="post" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="changeclient" />
    <fmt:message key="field.id"/>
    <br/>
    <label>
        <input type="number" name="id" value=""/>
    </label>
    <br/>
    <fmt:message key="field.discount"/>
    <br/>
    <label>
        <input type="text" name="discount" value=""/>
    </label>
    <fmt:message key="field.notes"/>
    <br/>
    <label>
        <input type="text" name="notes" value=""/>
    </label>
    <input type="submit" value="<fmt:message key="button.changeclient"/>"/>
</form>
<br/>

<form name="deleteClientForm" method="post" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="deleteentity" />
    <fmt:message key="field.id"/>
    <br/>
    <label>
        <input type="number" name="id" value=""/>
    </label>
    <input type="submit" value="<fmt:message key="button.deleteclient"/>"/>
</form>
<br/>

<div style="float: left">
    <fmt:message key="text.clients"/>
</div>
<br/>
<br/>

<div style="float: left">
    <c:forEach var="client" items="${clientList}">
        <tr>
            <td><c:out value="${client.id}" /></td>
            <td><c:out value="${client.discount}" /></td>
            <td><c:out value="${client.location}" /></td>
            <td><c:out value="${client.relative}" /></td>
            <td><c:out value="${client.notes}" /></td>
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