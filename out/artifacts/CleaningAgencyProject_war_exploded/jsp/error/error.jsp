<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle basename="message"/>

<html>
<head>
    <title><fmt:message key="title.error"/></title>
    <link rel="icon" href="<c:url value="/data/favicon.ico"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/styles.css"/>"/>
</head>
<body>
<c:if test="${empty user}">
    <jsp:forward page="/jsp/login.jsp"/>
</c:if>
<jsp:include page="/WEB-INF/view/header.jsp"/>
<jsp:include page="/WEB-INF/view/backToMain.jsp"/>

<div class="action-header"><b><br/>
    <b><fmt:message key="text.error1"/></b> ${pageContext.errorData.requestURI} <fmt:message key="text.error2"/> <br/>
    <b><fmt:message key="text.error3"/></b> ${pageContext.errorData.servletName} <br/>
    <b><fmt:message key="text.error4"/></b> ${pageContext.errorData.statusCode} <br/>
    <b><fmt:message key="text.error5"/></b> ${pageContext.exception} <hr/><br/>
</b></div>

<jsp:include page="/WEB-INF/view/footer.jsp"/>
</body>
</html>