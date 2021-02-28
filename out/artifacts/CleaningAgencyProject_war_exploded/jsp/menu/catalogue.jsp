<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@taglib prefix="ctg" uri="/WEB-INF/tld/removeattr.tld" %>
    <fmt:setBundle basename="message"/>

<html>
    <head>
        <title><fmt:message key="title.catalogue"/></title>
        <link rel="icon" href="<c:url value="/data/favicon.ico"/>"/>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/styles.css"/>"/>
    </head>

    <body>
    <c:if test="${not empty user and user.role eq 'admin'}">
        <jsp:forward page="/jsp/resultset/catalogueList.jsp"/>
    </c:if>
    <jsp:include page="/WEB-INF/view/header.jsp"/>

    <div class="little-float">
    <jsp:include page="/WEB-INF/view/backToMain.jsp"/>
    </div>

    <c:if test="${ not empty user and user.role eq 'client' }">
    <br/>
    <div class="little-margin-left">
    <form name="basketForm" method="get" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="basket" />
    <input class="add-basket" type="submit" value="<fmt:message key="button.tobasket"/>"/>
    </form>
    </div>
    </c:if>

    <c:set var="services" scope="session" value="${catalogueList}"/>
    <c:set var="totalCount" scope="session" value="${catalogueList.size()}"/>
    <c:set var="perPage" scope="session" value="${15}"/>
    <c:set var="pageStart" value="${param.start}"/>
    <c:if test="${empty pageStart or pageStart < 0}">
        <c:set var="pageStart" value="0"/>
    </c:if>
    <c:if test="${totalCount < pageStart}">
        <c:set var="pageStart" value="${pageStart - perPage}"/>
    </c:if><br/><br/><br/>

    <div class="little-center">
    <h5><u class="positions"><fmt:message key="text.catalogue"/></u></h5>
    </div>

    <c:if test="${ not empty user and user.role eq 'client' }">
    <div class="positions little-margin-left">
    <fmt:message key="text.catalogue2"/>
    </div>
    <div class="positions little-margin-left">
    <fmt:message key="text.catalogue3"/>
    </div><br/>
    </c:if>

    <c:if test="${ not empty user and user.role eq 'client' or user.role eq 'cleaner'}">
    <div class="little-center">
    <a href="?start=${pageStart - perPage}"><<</a>
    ${pageStart + 1} - ${pageStart + perPage}
    <a href="?start=${pageStart + perPage}">>></a>
    </div>
    </c:if><br/>

    <div class="little-error little-margin-left">${errorSelect}</div>
    ${wrongAction}
    ${nullPage}
    <br/><ctg:remove-attr/>

    <div class="little-center">
    <table border="1" cellpadding="5" cellspacing="5" align="center">
    <tr class="little-back">
    <th ><fmt:message key="field.idservice"/></th>
    <th><fmt:message key="field.service"/></th>
    <th><fmt:message key="field.cost"/></th>
    <th><fmt:message key="field.discount2"/></th>
    <c:if test="${ not empty user and user.role eq 'client' }">
    <th><fmt:message key="field.check"/></th>
    </c:if>
    </tr>

    <c:forEach var="catalogue" items="${catalogueList}" begin="${pageStart}" end="${pageStart + perPage - 1}">
        <tr>
        <td><c:out value="${catalogue.id}" /></td>
        <td><c:out value="${catalogue.service}" /></td>
        <td><c:out value="${catalogue.cost}" /></td>
        <td><c:out value="${catalogue.sales}" /></td>
        <c:if test="${ not empty user and user.role eq 'client' }">
        <td>
        <form name="positionForm" method="post" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="select" />
        <input type="hidden" name="position" value="${catalogue.id}" />
        <input class="choice" type="submit" value="<fmt:message key="button.choice"/>" />
        </form>
        </td>
        </c:if>
        </tr>
    </c:forEach>
    </table><br/>

    <c:if test="${ not empty user and user.role eq 'client' or user.role eq 'cleaner'}">
    <a href="?start=${pageStart - perPage}"><<</a>
    ${pageStart + 1} - ${pageStart + perPage}
    <a href="?start=${pageStart + perPage}">>></a>
    </div>
    </c:if><br/>

   <jsp:include page="/WEB-INF/view/footer.jsp"/>
    </body>
</html>