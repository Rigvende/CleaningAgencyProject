<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<fmt:setBundle basename="message"/>

<html>
<head>
    <title><fmt:message key="title.changeloc"/></title>
</head>

<body>
<jsp:include page="/WEB-INF/view/header.jsp"/>
<br/>

<form name="changeBurialForm" method="POST" action="${pageContext.request.contextPath}/controller">
    <fmt:message key="text.change1"/>
    <hr/><input type="hidden" name="command" value="changeburial" />

    <br/><fmt:message key="field.location"/><br/>
    <label>
        <input type="text" name="location" value="${client.location}"/>
    </label>

    <br/><fmt:message key="field.relative"/><br/>
    <label>
        <input type="text" name="relative" value="${client.relative}"/>
    </label>

    <div style="color: crimson">${errorChangeMessage}</div>
    ${wrongAction}
    ${nullPage}
    <br/>    <br/>

    <input type="submit" value="<fmt:message key="button.changeloc"/>"/>
</form>

<jsp:include page="/WEB-INF/view/backToMain.jsp"/>
<br/><br/><br/>

<jsp:include page="/WEB-INF/view/footer.jsp"/>
</body>
</html>