<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/removeattr.tld" %>
<fmt:setBundle basename="message"/>

<html>
<head>
    <title><fmt:message key="title.changeloc"/></title>
    <link rel="icon" href="<c:url value="/data/favicon.ico"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/styles.css"/>"/>
</head>
<body>
<c:if test="${empty user}">
    <jsp:forward page="/jsp/login.jsp"/>
</c:if>
<jsp:include page="/WEB-INF/view/header.jsp"/>
<jsp:include page="/WEB-INF/view/backToMain.jsp"/><br/><br/><br/><br/>

<form name="changeBurialForm" method="POST" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="changeburial" />
    <div class="action-header"><b>
    <fmt:message key="text.change1"/><hr/>
    <br/><fmt:message key="field.location"/><br/>
    <label>
        <input class="little-width" type="text" name="location" value="${client.location}"/>
    </label><br/>

    <br/><fmt:message key="field.relative"/><br/>
    <label>
        <input class="little-width" type="text" name="relative" value="${client.relative}"/>
    </label>
    </b>

    <div class="little-error">${errorChangeBurialMessage}</div>
    ${wrongAction}
    ${nullPage}
    <br/><br/><ctg:remove-attr/>
    <input class="action-submit" type="submit" value="<fmt:message key="button.changeloc"/>"/>
    </div><br/>
</form>

<jsp:include page="/WEB-INF/view/footer.jsp"/>
</body>
</html>