<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

    <fmt:setBundle basename="message"/>

<html>
    <head>
        <title><fmt:message key="title.catalogue"/></title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/background.css"/>" />
    </head>

    <body>
    <jsp:include page="/WEB-INF/view/header.jsp"/>

    <div style="float: right">
    <jsp:include page="/WEB-INF/view/backToMain.jsp"/>
    </div>

    <br/>
    <div style="margin-left: 50px">
    <form name="basketForm" method="post" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="basket" />
    <input style="color: sienna; font-family: 'Book Antiqua',Serif; width: 200px; font-size: 18px;
    height: 30px" type="submit" value="<fmt:message key="button.makeorder"/>"/>
    </form>
    </div>

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

    <div style="text-align: center">
    <h5><u style="color: #0c4f5b; font-size: 16px; font-family: 'Palatino Linotype', serif">
    <fmt:message key="text.catalogue"/>
    </u></h5>
    </div>

    <div style="color: #0c4f5b; font-size: 16px; font-family: 'Palatino Linotype', serif; margin-left: 50px">
    <fmt:message key="text.catalogue2"/>
    </div>
    <div style="color: #0c4f5b; font-size: 16px; font-family: 'Palatino Linotype', serif; margin-left: 50px">
    <fmt:message key="text.catalogue3"/>
    </div>
    <br/>

    <div style="text-align: center">
    <a href="?start=${pageStart - perPage}"><<</a>
    ${pageStart + 1} - ${pageStart + perPage}
    <a href="?start=${pageStart + perPage}">>></a>
    </div>
    <br/>

    <div style="text-align: center">
    <table border="1" cellpadding="5" cellspacing="5" align="center">
    <tr style="background-color: royalblue">
    <th ><fmt:message key="field.idservice"/></th>
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

   <jsp:include page="/WEB-INF/view/footer.jsp"/>
    </body>
</html>