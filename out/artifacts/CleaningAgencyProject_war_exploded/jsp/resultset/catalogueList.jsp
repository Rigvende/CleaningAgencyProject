<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<fmt:setBundle basename="message"/>

<html>
<head>
    <title><fmt:message key="title.cataloguelist"/></title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/background.css"/>" />
</head>

<body>
<jsp:include page="/WEB-INF/view/header.jsp"/>
<br/>

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

<div style="float: right">
    <jsp:include page="/WEB-INF/view/backToMain.jsp"/>
</div>

<form name="changeServiceForm" method="post" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="changeserviceredirect" />

    <b style="color: #0c4f5b; font-size: 16px; font-family: 'Palatino Linotype', serif; margin-left: 50px">
    <fmt:message key="field.idservice"/>
    </b>
    <label>
        <input type="text" name="id" value=""/>
    </label>

    <input style="color: #0c4f5b; font-family: 'Palatino Linotype', sans-serif; width: 150px; font-size: 15px;
            height: 25px; " type="submit" value="<fmt:message key="button.changeservice"/>"/>

    <div style="color: crimson; margin-left: 100px">${errorChangeServiceIdMessage}</div>
    ${wrongAction}
    ${nullPage}
    <%request.getSession(true); session.removeAttribute("errorChangeServiceIdMessage");%>
</form>

<form name="deleteServiceForm" method="post" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="deleteentity" />
    <input type="hidden" name="entitytype" value="service" />

    <b style="color: #0c4f5b; font-size: 16px; font-family: 'Palatino Linotype', serif; margin-left: 50px">
    <fmt:message key="field.idservice"/>
    </b>
    <label>
        <input type="text" name="id" value=""/>
    </label>

    <input style="color: #0c4f5b; font-family: 'Palatino Linotype', sans-serif; width: 150px; font-size: 15px;
            height: 25px; " type="submit" value="<fmt:message key="button.deletservice"/>"/>
</form>

<form name="addServiceForm" method="post" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="addserviceredirect" />

    <div style="text-align: right">
    <input style="color: #0c4f5b; font-family: 'Palatino Linotype', sans-serif; width: 200px; font-size: 18px;
            height: 40px; margin-right: 15px" type="submit" value="<fmt:message key="button.addservice"/>"/>
    </div>
</form>

<div style="text-align: center">
    <h5><u style="color: #0c4f5b; font-size: 16px; font-family: 'Palatino Linotype', serif">
        <fmt:message key="text.services"/>
    </u></h5>
</div>

<div style="text-align: center">
<a href="?start=${pageStart - perPage}"><<</a>
${pageStart + 1} - ${pageStart + perPage}
<a href="?start=${pageStart + perPage}">>></a>
</div>
<br/>

<div style="text-align: center">
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