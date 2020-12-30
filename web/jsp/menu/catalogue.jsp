<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@taglib prefix="ctg" uri="/WEB-INF/tld/removeattr.tld" %>

    <fmt:setBundle basename="message"/>

<html>
    <head>
        <title><fmt:message key="title.catalogue"/></title>
        <link rel="icon" href="<c:url value="/data/favicon.ico"/>"/>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/background.css"/>" />
    </head>

    <body>
    <c:if test="${not empty user and user.role eq 'admin'}">
        <jsp:forward page="/jsp/resultset/catalogueList.jsp"/>
    </c:if>

    <jsp:include page="/WEB-INF/view/header.jsp"/>

    <div style="float: right">
    <jsp:include page="/WEB-INF/view/backToMain.jsp"/>
    </div>

    <c:if test="${ not empty user and user.role eq 'client' }">
    <br/>
    <div style="margin-left: 50px">
    <form name="basketForm" method="get" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="basket" />
    <input style="color: sienna; font-family: 'Book Antiqua', serif; width: 250px; font-size: 22px;
    height: 40px" type="submit" value="<fmt:message key="button.tobasket"/>"/>
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
    </c:if>

    <br/><br/><br/>
    <div style="text-align: center">
    <h5><u style="color: #0c4f5b; font-size: 16px; font-family: 'Palatino Linotype', serif">
    <fmt:message key="text.catalogue"/>
    </u></h5>
    </div>

    <c:if test="${ not empty user and user.role eq 'client' }">
    <div style="color: #0c4f5b; font-size: 16px; font-family: 'Palatino Linotype', serif; margin-left: 50px">
    <fmt:message key="text.catalogue2"/>
    </div>
    <div style="color: #0c4f5b; font-size: 16px; font-family: 'Palatino Linotype', serif; margin-left: 50px">
    <fmt:message key="text.catalogue3"/>
    </div>
    <br/>
    </c:if>

    <c:if test="${ not empty user and user.role eq 'client' or user.role eq 'cleaner'}">
    <div style="text-align: center">
    <a href="?start=${pageStart - perPage}"><<</a>
    ${pageStart + 1} - ${pageStart + perPage}
    <a href="?start=${pageStart + perPage}">>></a>
    </div>
    </c:if>
    <br/>

    <div style="color: crimson; margin-left: 50px">${errorSelect}</div>
    ${wrongAction}
    ${nullPage}
    <br/><ctg:remove-attr/>

    <div style="text-align: center">
    <table border="1" cellpadding="5" cellspacing="5" align="center">
    <tr style="background-color: royalblue">
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
        <input style="color: darkblue; background-color: lightgoldenrodyellow;
            font-family: 'Palatino Linotype',serif; width: 150px; font-size: 16px;
            height: 30px" type="submit" value="<fmt:message key="button.choice"/>" />
        </form>
        </td>
        </c:if>
        </tr>
    </c:forEach>
    </table>
    <br/>

    <c:if test="${ not empty user and user.role eq 'client' or user.role eq 'cleaner'}">
    <a href="?start=${pageStart - perPage}"><<</a>
    ${pageStart + 1} - ${pageStart + perPage}
    <a href="?start=${pageStart + perPage}">>></a>
    </div>
    </c:if>
    <br/>

   <jsp:include page="/WEB-INF/view/footer.jsp"/>
    </body>
</html>