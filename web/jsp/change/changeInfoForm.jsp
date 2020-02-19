<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<fmt:setBundle basename="message"/>

<html>
<head>
    <title><fmt:message key="title.change"/></title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/background.css"/>" />
</head>

<body>
<jsp:include page="/WEB-INF/view/header.jsp"/>

<br/>
<form name="changeForm" method="POST" action="${pageContext.request.contextPath}/controller">
    <fmt:message key="text.change1"/>
    <hr/><input type="hidden" name="command" value="changeuser" />

    <br/><fmt:message key="field.name1"/><br/>
    <label>
        <input type="text" name="firstname" value="${user.name}"/>
    </label>

    <br/><fmt:message key="field.lastname1"/><br/>
    <label>
        <input type="text" name="lastname" value="${user.lastname}"/>
    </label>

    <br/><fmt:message key="field.phone1"/><br/>
    <label>
        <input type="number" name="phone" value="${user.phone}"/>
    </label>

    <br/><fmt:message key="field.email1"/><br/>
    <label>
        <input type="text" name="email" value="${user.email}"/>
    </label>

    <br/><fmt:message key="field.address"/><br/>
    <label>
        <input type="text" name="address" value="${user.address}"/>
    </label>

    <div style="color: crimson">${errorChangeUserMessage}</div>
    ${wrongAction}
    ${nullPage}
    <br/>    <br/>

    <input type="submit" value="<fmt:message key="button.change"/>"/>
</form>

<jsp:include page="/WEB-INF/view/backToMain.jsp"/>
<br/><br/><br/>

<jsp:include page="/WEB-INF/view/footer.jsp"/>
</body>
</html>