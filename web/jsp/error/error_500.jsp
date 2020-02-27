<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<fmt:setBundle basename="message"/>

<html>
<head>
    <title>500</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/background.css"/>" />
</head>

<body>
<c:if test="${empty user}">
    <jsp:forward page="/jsp/login.jsp"/>
</c:if>

<jsp:include page="/WEB-INF/view/header.jsp"/>
<br/>

    <div style="text-align: center; color: #0c4f5b; font-family: 'Palatino Linotype', serif; font-size: 18px; margin-left: 50px"><b>
    <fmt:message key="text.error8"/>
    <br/></b></div>

<jsp:include page="/WEB-INF/view/backToMain.jsp"/>
<br/><br/><br/>

<jsp:include page="/WEB-INF/view/footer.jsp"/>
</body>
</html>