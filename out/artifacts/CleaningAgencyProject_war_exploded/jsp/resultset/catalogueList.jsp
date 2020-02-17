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

<c:set var="services" scope="session" value="${catalogueList}"/>
<c:set var="totalCount" scope="session" value="${catalogueList.size()}"/>
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
    <h5><u><fmt:message key="text.services"/></u></h5>
</div>
<br/>
<br/>
<br/>
<br/>

<div style="float: left">
    <table border="1" cellpadding="5" cellspacing="5">
        <tr>
            <th><fmt:message key="field.id"/></th>
            <th><fmt:message key="field.service"/></th>
            <th><fmt:message key="field.cost"/></th>
            <th><fmt:message key="field.discount2"/></th>
        </tr>

    <c:forEach var="catalogue" items="${catalogueList}" begin="${pageStart}" end="${pageStart + perPage - 1}">
        <tr>
            <td><c:out value="${catalogue.id}" /></td>
            <td><c:out value="${catalogue.service}" /></td>
            <td><c:out value="${catalogue.cost}" /></td>
            <td><c:out value="${catalogue.discount}" /></td>
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