<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/removeattr.tld" %>

<fmt:setBundle basename="message"/>

<html>
<head>
    <title><fmt:message key="title.guestlist"/></title>
    <link rel="icon" href="<c:url value="/data/favicon.ico"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/background.css"/>" />
</head>

<body>
<c:if test="${empty user}">
    <jsp:forward page="/jsp/login.jsp"/>
</c:if>

<jsp:include page="/WEB-INF/view/header.jsp"/>
<br/>

<c:set var="guests" scope="session" value="${guestList}"/>
<c:set var="totalCount" scope="session" value="${guestList.size()}"/>
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

<form name="changeGuestForm" method="post" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="changeguest" />

    <b style="color: #0c4f5b; font-size: 16px; font-family: 'Palatino Linotype', serif; margin-left: 50px">
        <fmt:message key="field.id"/>
    </b>
    <br/>
    <label>
        <input style="margin-left: 50px" type="text" name="id" value=""
               required oninvalid="this.setCustomValidity('<fmt:message key="message.required"/>')"
               oninput="setCustomValidity('')"/>
    </label>
    <br/>

    <b style="color: #0c4f5b; font-size: 16px; font-family: 'Palatino Linotype', serif; margin-left: 50px">
        <fmt:message key="field.guest"/>
    </b>
    <br/>
    <label>
        <input style="margin-left: 50px" type="text" name="role" value="<fmt:message key="text.guest"/>"/>
    </label>

    <div style="color: crimson; margin-left: 50px">${errorChangeGuestMessage}</div>
    ${wrongAction}
    ${nullPage}
    <br/><ctg:remove-attr/>

    <input style="color: #0c4f5b; font-family: 'Palatino Linotype', sans-serif; width: 250px; font-size: 18px;
            height: 30px; margin-left: 50px" type="submit" value="<fmt:message key="button.changeguest"/>"/>
</form>
<br/>

<div style="text-align: center">
    <h5><u style="color: #0c4f5b; font-size: 16px; font-family: 'Palatino Linotype', serif">
        <fmt:message key="text.guests"/>
    </u></h5>
</div>

<div style="text-align: center">
    <table border="1" cellpadding="5" cellspacing="5" align="center">
    <tr style="background-color: royalblue">
        <th><fmt:message key="field.id"/></th>
        <th><fmt:message key="field.name1"/></th>
        <th><fmt:message key="field.lastname1"/></th>
        <th><fmt:message key="field.phone1"/></th>
        <th><fmt:message key="field.email1"/></th>
    </tr>

    <c:forEach var="guest" items="${guestList}" begin="${pageStart}" end="${pageStart + perPage - 1}">

    <tr>
        <td><c:out value="${guest.id}" /></td>
        <td><c:out value="${guest.name}" /></td>
        <td><c:out value="${guest.lastname}" /></td>
        <td><c:out value="${guest.phone}" /></td>
        <td><c:out value="${guest.email}" /></td>
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