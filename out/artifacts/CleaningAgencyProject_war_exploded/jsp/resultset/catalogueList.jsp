<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/removeattr.tld" %>
<fmt:setBundle basename="message"/>

<html>
<head>
    <title><fmt:message key="title.cataloguelist"/></title>
    <link rel="icon" href="<c:url value="/data/favicon.ico"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/styles.css"/>"/>
</head>
<body>
<c:if test="${empty user}">
    <jsp:forward page="/jsp/login.jsp"/>
</c:if>
<jsp:include page="/WEB-INF/view/header.jsp"/><br/>
<c:set var="services" scope="session" value="${catalogueList}"/>
<c:set var="totalCount" scope="session" value="${catalogueList.size()}"/>
<c:set var="perPage" scope="session" value="${15}"/>
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

<form name="changeServiceForm" method="post" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="changeserviceredirect" />
    <b class="field-mail"><fmt:message key="field.idservice"/></b>
    <label>
        <input type="text" name="id" value=""/>
    </label>
    <input class="menu-action-submit" type="submit" value="<fmt:message key="button.changeservice"/>"/>

    <div class="little-error menu-margin">${errorChangeServiceIdMessage}</div>
    ${wrongAction}
    ${nullPage}
    <ctg:remove-attr/>
</form>

<form name="deleteServiceForm" method="post" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="deleteentity" />
    <input type="hidden" name="entitytype" value="service" />
    <b class="field-mail"><fmt:message key="field.idservice"/></b>
    <label>
        <input type="text" name="id" value=""
               required oninvalid="this.setCustomValidity('<fmt:message key="message.required"/>')"
               oninput="setCustomValidity('')"/>
    </label>
    <input class="menu-action-submit" type="submit" value="<fmt:message key="button.deletservice"/>"/>
</form>

<form name="addServiceForm" method="post" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="addserviceredirect" />
    <div class="little-align-right">
    <input class="action-submit" style="margin-right: 15px" type="submit" value="<fmt:message key="button.addservice"/>"/>
    </div>
</form>

<div class="little-center">
    <h5><u class="positions"><fmt:message key="text.services"/></u></h5>
</div>

<div class="little-center">
<a href="?start=${pageStart - perPage}"><<</a>
${pageStart + 1} - ${pageStart + perPage}
<a href="?start=${pageStart + perPage}">>></a>
</div><br/>

<div class="little-center">
    <table border="1" cellpadding="5" cellspacing="5" align="center">
        <tr style="background-color: royalblue">
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
            <td><c:out value="${catalogue.sales}" /></td>
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