<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<fmt:setBundle basename="message"/>

<html>
<head>
    <title><fmt:message key="title.cataloguelist"/></title>
</head>

<body>
<jsp:include page="/WEB-INF/view/header.jsp"/>
<br/>

<form name="changeServiceForm" method="post" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="changeservice" />
    <fmt:message key="field.id"/>
    <br/>
    <label>
        <input type="number" name="id" value=""/>
    </label>
    <br/>
    <fmt:message key="field.service"/>
    <br/>
    <label>
        <input type="text" name="service" value=""/>
    </label>
    <br/>
    <fmt:message key="field.cost"/>
    <br/>
    <label>
        <input type="text" name="cost" value=""/>
    </label>
    <br/>
    <fmt:message key="field.discount2"/>
    <br/>
    <label>
        <input type="text" name="discount" value=""/>
    </label>
    <input type="submit" value="<fmt:message key="button.changeservice"/>"/>
</form>
<br/>

<form name="deleteServiceForm" method="post" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="deleteentity" />
    <fmt:message key="field.id"/>
    <br/>
    <label>
        <input type="number" name="id" value=""/>
    </label>
    <input type="submit" value="<fmt:message key="button.deletservice"/>"/>
</form>
<br/>

<form name="addServiceForm" method="post" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="addentity" />
    <fmt:message key="field.id"/>
    <br/>
    <label>
        <input type="text" name="addservice" value=""/>
    </label>
    <br/>
    <label>
        <input type="text" name="addcost" value=""/>
    </label>
    <br/>
    <label>
        <input type="text" name="adddiscount" value=""/>
    </label>
    <input type="submit" value="<fmt:message key="button.addservice"/>"/>
</form>
<br/>

<div style="float: left">
    <fmt:message key="text.services"/>
</div>
<br/>
<br/>

<div style="float: left">
    <c:forEach var="catalogue" items="${catalogueList}">
        <tr>
            <td><c:out value="${catalogue.id}" /></td>
            <td><c:out value="${catalogue.service}" /></td>
            <td><c:out value="${catalogue.cost}" /></td>
            <td><c:out value="${catalogue.discount}" /></td>
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