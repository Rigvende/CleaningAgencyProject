<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/removeattr.tld" %>

<fmt:setBundle basename="message"/>

<html>
<head>
    <title><fmt:message key="title.cleanerlist"/></title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/background.css"/>" />
</head>

<body>
<jsp:include page="/WEB-INF/view/header.jsp"/>
<br/>

<c:set var="cleaners" scope="session" value="${cleanerList}"/>
<c:set var="totalCount" scope="session" value="${cleanerList.size()}"/>
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

<form name="changeCleanerForm" method="post" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="changecleanerredirect" />

    <b style="color: #0c4f5b; font-size: 16px; font-family: 'Palatino Linotype', serif; margin-left: 50px">
    <fmt:message key="field.id"/>
    </b>

    <label>
        <input type="text" name="id" value=""/>
    </label>

    <input style="color: #0c4f5b; font-family: 'Palatino Linotype', sans-serif; width: 150px; font-size: 15px;
            height: 25px; " type="submit" value="<fmt:message key="button.changecleaner"/>"/>

    <div style="color: crimson; margin-left: 80px">${errorChangeCleanerIdMessage}</div>
    ${wrongAction}
    ${nullPage}
    <ctg:remove-attr/>
</form>

<form name="deleteCleanerForm" method="post" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="deleteentity" />
    <input type="hidden" name="entitytype" value="cleaner" />

    <b style="color: #0c4f5b; font-size: 16px; font-family: 'Palatino Linotype', serif; margin-left: 50px">
    <fmt:message key="field.id"/>
    </b>

    <label>
        <input type="text" name="id" value=""/>
    </label>

    <input style="color: #0c4f5b; font-family: 'Palatino Linotype', sans-serif; width: 150px; font-size: 15px;
            height: 25px; " type="submit" value="<fmt:message key="button.deletecleaner"/>"/>
</form>

<div style="text-align: center">
    <h5><u style="color: #0c4f5b; font-size: 16px; font-family: 'Palatino Linotype', serif">
        <fmt:message key="text.cleaners"/>
    </u></h5>
</div>
<br/>

<div style="text-align: center">
    <table border="1" cellpadding="5" cellspacing="5" align="center">
        <tr style="background-color: royalblue">
            <th><fmt:message key="field.id"/></th>
            <th><fmt:message key="field.name1"/></th>
            <th><fmt:message key="field.lastname1"/></th>
            <th><fmt:message key="field.phone1"/></th>
            <th><fmt:message key="field.email1"/></th>
            <th><fmt:message key="field.commission"/></th>
            <th><fmt:message key="field.notes"/></th>
            <th><fmt:message key="field.idcleaner2"/></th>
        </tr>

    <c:forEach var="cleaner" items="${cleanerList}" begin="${pageStart}" end="${pageStart + perPage - 1}">
        <tr>
            <td><c:out value="${cleaner.id}" /></td>
            <td><c:out value="${cleaner.name}" /></td>
            <td><c:out value="${cleaner.lastname}" /></td>
            <td><c:out value="${cleaner.phone}" /></td>
            <td><c:out value="${cleaner.email}" /></td>
            <td><c:out value="${cleaner.cleaner.commission}" /></td>
            <td><c:out value="${cleaner.cleaner.notes}" /></td>
            <td><c:out value="${cleaner.cleaner.id}" /></td>
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